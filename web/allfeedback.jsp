<%@page import="entity.Feedback"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Feedback</title>
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f5f5f5;
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 0;
            padding: 20px;
        }
        .container {
            background: #fff;
            border: 1px solid #e0e0e0;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 1200px;
        }
        h1 {
            color: #ff5722;
            font-size: 2.5em;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #e0e0e0;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #ff5722;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
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
        <h1>All Feedback</h1>
        <table>
            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Service ID</th>
                    <th>Testimonial</th>
                    <th>Experience Date</th>
                    <th>Satisfaction Level</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="feedback" items="${feedbackList}">
                <tr>
                    <td>${feedback.customerID}</td>
                    <td>${feedback.serviceID}</td>
                    <td>${feedback.testimonial}</td>
                    <td>${feedback.experienceDate}</td>
                    <td>
                        <c:choose>
                            <c:when test="${feedback.satisfactionLevel == 1}">
                                Không hài lòng
                            </c:when>
                            <c:when test="${feedback.satisfactionLevel == 2}">
                                Bình Thường
                            </c:when>
                            <c:when test="${feedback.satisfactionLevel == 3}">
                                Rất hài lòng
                            </c:when>
                            <c:otherwise>
                                Other Status
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="index.jsp" class="back-link">Go back to Home</a>
    </div>
</body>
</html>
