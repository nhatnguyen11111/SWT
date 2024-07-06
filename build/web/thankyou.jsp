<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thank You!</title>
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f5f5f5;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background: #fff;
            border: 1px solid #e0e0e0;
            padding: 2em;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            text-align: center;
            width: 100%;
            max-width: 600px;
        }
        h1 {
            color: #ff5722;
            font-size: 2.5em;
            margin-bottom: 0.5em;
        }
        p {
            font-size: 1.2em;
            margin-top: 1em;
        }
        .back-link {
            display: inline-block;
            margin-top: 2em;
            padding: 0.75em 2em;
            text-decoration: none;
            color: #fff;
            background-color: #ff5722;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        .back-link:hover {
            background-color: #e64a19;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Thank You!</h1>
        <p>Your feedback is highly appreciated and will help us to improve our service!</p>
        <a href="index.jsp" class="back-link">Go back to Home</a>
        <a href="userfeedback.jsp" class="back-link">View feedback</a>
    </div>
</body>
</html>
c