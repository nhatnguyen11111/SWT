<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đánh giá sản phẩm</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 90%;
            margin: 10px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 8px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .product-item {
            display: flex;
            align-items: center;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        .product-item img {
            margin-right: 20px;
        }

        .product-item strong {
            font-size: 1.2em;
        }

        .rating {
            display: flex;
            direction: row-reverse;
            justify-content: center;
        }

        .rating input {
            display: none;
        }

        .rating label {
            font-size: 2em;
            color: #ccc;
            cursor: pointer;
            transition: color 0.2s;
        }

        .rating input:checked ~ label {
            color: #f5b301;
        }

        .rating label:hover,
        .rating label:hover ~ label {
            color: #f5b301;
        }

        form {
            margin-top: 10px;
            display: flex;
            flex-direction: column;
        }

        label {
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input[type="number"],
        textarea,
        input[type="file"] {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 100%;
        }

        button {
            margin-top: 15px;
            padding: 10px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

        .error-message {
            color: red;
            font-weight: bold;
            text-align: center;
            margin-bottom: 15px;
        }

        .comment-message {
            text-align: center;
            margin-top: 15px;
            font-weight: bold;
            color: green;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Đánh giá sản phẩm</h2>
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <c:forEach items="${orderItems}" var="item">
            <div class="product-item">
                <img width="60px" height="60px" src="img/${item.product.img}" alt="${item.product.productName}">
                <strong>${item.product.productName}</strong>
            </div> 
             <input type="hidden" class="form-control" id="serviceID" name="CTVID" value="${CTVID}" aria-describedby="inputGroupPrepend" required>
             <strong>${CTVID}</strong>
             <strong>${orderId}</strong>
            <c:if test="${item.commentExists}">
                <div class="comment-message">Bạn đã hoàn thành đánh giá sản phẩm này.</div>
            </c:if>
            <c:if test="${not item.commentExists}">
                <form class="needs-validation" novalidate action="commentproduct" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="orderId" value="${orderId}" />
                    <input type="hidden" class="form-control" id="serviceID" name="productID" value="${item.product.productId}" aria-describedby="inputGroupPrepend" required>
                     <input type="hidden" class="form-control" id="serviceID" name="CTVID" value="${CTVID}" aria-describedby="inputGroupPrepend" required>
                    <strong>${item.product.productId}</strong>
                    <div>
                        <label>Đánh giá (từ 1 đến 5 sao):</label>
                        <div class="rating">
                            <input type="radio" id="star5-${item.product.productId}" name="rating" value="5" /><label for="star5-${item.product.productId}" title="5 stars"><i class="fa fa-star"></i></label>
                            <input type="radio" id="star4-${item.product.productId}" name="rating" value="4" /><label for="star4-${item.product.productId}" title="4 stars"><i class="fa fa-star"></i></label>
                            <input type="radio" id="star3-${item.product.productId}" name="rating" value="3" /><label for="star3-${item.product.productId}" title="3 stars"><i class="fa fa-star"></i></label>
                            <input type="radio" id="star2-${item.product.productId}" name="rating" value="2" /><label for="star2-${item.product.productId}" title="2 stars"><i class="fa fa-star"></i></label>
                            <input type="radio" id="star1-${item.product.productId}" name="rating" value="1" /><label for="star1-${item.product.productId}" title="1 star"><i class="fa fa-star"></i></label>
                        </div>
                    </div>
                    <div>
                        <label>Bình luận:</label>
                        <textarea name="comment" rows="4" required></textarea>
                    </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <label for="commentImg">Ảnh sản phẩm(nếu có)</label>
                                <div class="input-group">
                                    <input id="avatar" type="file" class="form-control" name="commentImg" value="" required>
                                </div>
                            </div>
                        </div>
                    <button type="submit">Gửi đánh giá</button>
                </form>
            </c:if>
        </c:forEach>
    </div>
</body>
</html>
