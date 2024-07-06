<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat Room</title>
    <!-- CSS styles, if any -->
    <style>
        /* Define your styles here */
    </style>
</head>
<body>
    <h1>Chat Room</h1>
    
    <!-- Container for displaying messages -->
    <div id="messages">
        <!-- Messages will be displayed here dynamically -->
    </div>
    
    <!-- Form to send messages -->
    <form onsubmit="handleFormSubmit(event)">
        <input type="text" id="toId" value="${CTVID}" /> 
        <input type="text" id="fromId" value="${fromId}" /> 
        <input type="text" id="messageText" placeholder="Type your message..." required />
        <button type="submit">Send</button>
    </form>

    <!-- JavaScript for WebSocket -->
    <script type="text/javascript">

        let socket;

        function connectWebSocket() {
            // Open a WebSocket connection
            socket = new WebSocket("ws://localhost:8045/BabyCare3/chat");

            // When the connection is open
            socket.onopen = function(event) {
                console.log("WebSocket connected.");
                // Example: Send a welcome message to the server
                sendMessage({ type: "loadMessages", toId: document.getElementById("toId").value });
            };

            // When receiving a message from the server
            socket.onmessage = function(event) {
                console.log("Received message: " + event.data);
                handleMessage(JSON.parse(event.data));
            };

            // When an error occurs
            socket.onerror = function(error) {
                console.error("WebSocket error: " + error);
            };

            // When the connection is closed
            socket.onclose = function(event) {
                console.log("WebSocket closed. Reconnecting...");
                connectWebSocket(); // Automatically reconnect
            };
        }

        function sendMessage(message) {
            try {
                if (socket.readyState === WebSocket.OPEN) {
                    socket.send(JSON.stringify(message));
                } else {
                    console.error("WebSocket not connected.");
                }
            } catch (error) {
                console.error("Error sending message: " + error.message);
            }
        }

        function handleMessage(message) {
            switch (message.type) {
                case "system":
                    // Handle system messages
                    console.log("System message: " + message.messageText);
                    break;
                case "chat":
                    // Handle chat messages
                    console.log("Chat message from " + message.fromId + ": " + message.messageText);
                    displayMessage(message);
                    break;
                case "loadMessages":
                    // Handle loading messages
                    console.log("Received existing messages: ", message.messages);
                    message.messages.forEach(displayMessage);
                    break;
                default:
                    console.log("Unknown message type: " + message.type);
            }
        }

        function displayMessage(message) {
            // Function to display messages in the UI
            let messageDiv = document.createElement("div");
            messageDiv.textContent = message.fromId + ": " + message.messageText;
            document.getElementById("messages").appendChild(messageDiv);
        }

        function handleFormSubmit(event) {
            event.preventDefault(); // Prevent form submission
            let messageText = document.getElementById("messageText").value;
            let toId = document.getElementById("toId").value;
            let fromId = document.getElementById("fromId").value;
             console.log("fromId:", fromId); // Check if fromId is correctly retrieved
            console.log("messageText", messageText); // Check if fromId is correctly retrieved
            sendMessage({ type: "chat", fromId: fromId, toId: toId, messageText: messageText });
            document.getElementById("messageText").value = "";
        }

        // Connect to WebSocket when page loads 
        window.onload = function() {
            connectWebSocket();
        };

    </script>
</body>
</html>
