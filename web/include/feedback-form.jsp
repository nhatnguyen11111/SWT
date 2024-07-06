<!DOCTYPE html>
<html lang="en">
<head>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <title>Customer Testimonial</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            width: 60%;
            margin: auto;
            padding: 20px;
            background-color: #f9f9f9;
        }
        h1, p {
            text-align: center;
        }
        .form-group {
            margin-bottom: 1.5em;
        }
        .form-group label {
            display: block;
            margin-bottom: 0.5em;
            font-weight: bold;
        }
        .form-group input[type="text"],
        .form-group input[type="date"],
        .form-group textarea {
            width: 100%;
            padding: 0.8em;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-group textarea {
            height: 120px;
            resize: vertical;
        }
        .satisfaction-level {
            display: flex;
            justify-content: space-between;
            width: 80%;
            margin: auto;
        }
        .satisfaction-level input[type="radio"] {
            display: none;
        }
        .satisfaction-level label {
            width: 60px;
            height: 60px;
            background-color: lightgray;
            border-radius: 50%;
            text-align: center;
            line-height: 60px;
            cursor: pointer;
            transition: background-color 0.3s;
            display: inline-block;
            background-size: cover;
        }
        .satisfaction-level input[type="radio"]:checked + label {
            background-color: #4CAF50;
        }
        .satisfaction-level label img {
            width: 100%;
            height: 100%;
            border-radius: 50%;
        }
        .form-group button {
            display: block;
            width: 100%;
            padding: 1em;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 1em;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .form-group button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Đánh giá dịch vụ</h1>
    <p>We would love to hear about your experience with our service!</p>
    <form action="FeedBackServlet" method="post">
        <div class="form-group">
            <label for="customerID">Customer ID</label>
            <input type="text" id="customerID" name="CustomerID" value="${CustomerID}" required readonly>
        </div>
        <div class="form-group">
            <label for="customerName">Customer Name</label>
            <input type="text" id="customerName" name="name" value="${name}" required>
        </div>
        <div class="form-group">
            <label for="serviceID">ID service</label>
            <input type="hidden" id="serviceID" name="ServiceID" value="${ServiceID}" required>
            <h3>${ServiceID}</h3>
        </div>
        <div class="form-group">
            <label for="testimonial">Đánh giá của bạn</label>
            <textarea id="testimonial" name="testimonial" placeholder="Share your experience with us..." required></textarea>
        </div>
        <div class="form-group">
            <label for="experienceDate">Ngày/tháng/năm</label>
            <input type="date" id="experienceDate" name="experienceDate" required>
        </div>
        <div class="form-group">
            <label for="satisfactionLevel">Mức độ hài lòng</label>
            <div class="satisfaction-level">
                <input type="radio" id="satisfaction1" name="satisfactionLevel" value="1" required>
                <label for="satisfaction1"><img src="path/to/sad.png" alt="Sad"></label>
                <input type="radio" id="satisfaction2" name="satisfactionLevel" value="2" required>
                <label for="satisfaction2"><img src="path/to/neutral.png" alt="Neutral"></label>
                <input type="radio" id="satisfaction3" name="satisfactionLevel" value="3" required>
                <label for="satisfaction3"><img src="path/to/happy.png" alt="Happy"></label>
            </div>
        </div>
        <div class="form-group">
            <button type="submit">Submit</button>
        </div>
    </form>
</body>
</html>
