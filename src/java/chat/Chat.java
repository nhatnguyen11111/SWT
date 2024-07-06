package chat;

import config.DBConnect;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.HandshakeResponse;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import entity.User;
import org.json.JSONException;
import service.AESUtil;

@ServerEndpoint(value = "/chat", configurator = Chat.Configurator.class)
public class Chat {

    private static final Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        if (httpSession == null || httpSession.getAttribute("user") == null) {
            System.out.println("User not found in session. Closing connection.");
            session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, "User not authenticated."));
            return;
        }

        clients.add(session);
        System.out.println("New connection with client: " + session.getId());

        // Do not send message history here, only when requested by the client
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
        System.out.println("Client disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        clients.remove(session);
        throwable.printStackTrace();
    }

    private void saveMessageToDatabase(String fromId, String toId, String messageText) {
        try {
            String encryptedMessage = AESUtil.encrypt(messageText);

            System.out.println("Encrypted Message (before saving): " + encryptedMessage);

            String query = "INSERT INTO Message (fromId, toId, messageText, timestamp) VALUES (?, ?, ?, ?)";
            try (Connection conn = DBConnect.getConnection(); 
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, fromId);
                stmt.setString(2, toId);
                stmt.setString(3, encryptedMessage);
                stmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis())); // Lưu thời gian hiện tại

                stmt.executeUpdate();

                System.out.println("Message saved to database successfully.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendExistingMessages(Session session, String fromId, String toId) throws SQLException, ClassNotFoundException {
        String query = "SELECT fromId, toId, messageText FROM Message "
                + "WHERE (fromId = ? AND toId = ?) OR (fromId = ? AND toId = ?)";

        try (Connection conn = DBConnect.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fromId);
            stmt.setString(2, toId);
            stmt.setString(3, toId);
            stmt.setString(4, fromId);

            try (ResultSet rs = stmt.executeQuery()) {
                JSONArray messages = new JSONArray();
                while (rs.next()) {
                    String rsFromId = rs.getString("fromId");
                    String rsToId = rs.getString("toId");
                    String encryptedMessage = rs.getString("messageText");

                    System.out.println("Encrypted Message (from DB): " + encryptedMessage);

                    // Decrypt message
                    String decryptedMessage = AESUtil.decrypt(encryptedMessage);

                    System.out.println("Decrypted Message: " + decryptedMessage);

                    JSONObject messageObj = new JSONObject();
                    messageObj.put("fromId", rsFromId);
                    messageObj.put("toId", rsToId);
                    messageObj.put("messageText", decryptedMessage);
                    messages.put(messageObj);
                }

                JSONObject response = new JSONObject();
                response.put("type", "loadMessages");
                response.put("messages", messages);

                session.getBasicRemote().sendText(response.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws SQLException, ClassNotFoundException, JSONException {
        System.out.println("Received message: " + message);

        JSONObject jsonMessage = new JSONObject(message);
        String type = jsonMessage.getString("type");

        if (type.equals("chat")) {
            String toId = jsonMessage.getString("toId");
            String fromId = jsonMessage.getString("fromId");
            String fromUsername = jsonMessage.getString("fromUsername");
            String messageText = jsonMessage.getString("messageText");

            // Save message to database
            saveMessageToDatabase(fromId, toId, messageText);

            // Broadcast message to all clients
            broadcastMessage(fromId, fromUsername, toId, messageText);
        } else if (type.equals("loadMessages")) {
            String toId = jsonMessage.getString("toId");
            String fromId = getUserId(session);

            // Send existing messages to the client
            sendExistingMessages(session, fromId, toId);
        } else {
            System.out.println("Invalid message type received: " + type);
        }
    }

    private void broadcastMessage(String fromId, String fromUsername, String toId, String messageText) throws JSONException {
        JSONObject messageObj = new JSONObject();
        messageObj.put("type", "chat");
        messageObj.put("fromId", fromId);
        messageObj.put("fromUsername", fromUsername);
        messageObj.put("toId", toId);
        messageObj.put("messageText", messageText);
        String message = messageObj.toString();

        synchronized (clients) {
            for (Session client : clients) {
                try {
                    client.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getUserId(Session session) {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        User user = (User) httpSession.getAttribute("user");
        return user.getUserId();
    }

    public static class Configurator extends ServerEndpointConfig.Configurator {

        @Override
        public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
            HttpSession httpSession = (HttpSession) request.getHttpSession();
            config.getUserProperties().put(HttpSession.class.getName(), httpSession);
        }
    }
}
