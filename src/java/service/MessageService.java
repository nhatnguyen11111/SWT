package service;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

    private static final List<String> dummyDatabase = new ArrayList<>();

    public void sendMessage(String fromId, String toId, String messageText) {
        String message = fromId + ": " + messageText;
        dummyDatabase.add(message);
    }

    public List<String> getMessages(String toId) {
        List<String> messages = new ArrayList<>();
        for (String message : dummyDatabase) {
            if (message.startsWith(toId)) {
                messages.add(message);
            }
        }
        return messages;
    }
}
