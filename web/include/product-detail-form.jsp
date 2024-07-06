<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">-->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f4;
                color: #333;
                line-height: 1.6;
                padding: 20px;
            }

            .product-container, .store-info, .product-details {
                border: 1px solid #ddd;
                background-color: #fff;
                padding: 20px;
                margin-top: 20px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
                border-radius: 8px;
            }

            .product-image img, .product-details img {
                width: 100%;
                height: auto;
                border-radius: 4px;
            }

            .store-info img {
                width: 60px;
                height: 60px;
                border-radius: 50%;
            }

            .button {
                padding: 10px 20px;
                border: none;
                background-color: #FF4880;
                color: white;
                cursor: pointer;
                font-size: 16px;
                border-radius: 4px;
                margin: 5px 0;
                transition: background-color 0.3s;
            }

            .button:hover {
                background-color: #45a049;
            }

            .stats, h2, h3 {
                margin-bottom: 10px;
                font-family: Helvetica Neue;
            }
            h1{
                font-family: Helvetica Neue;
            }

            h2, h3 {
                color: #444;
            }

            .row {
                display: flex;
                align-items: center;
                justify-content: space-between;
                margin-bottom: 20px;
            }

            .col-md-6, .col-md-9, .col-md-1, .col-md-2 {
                flex: 1;
            }

            .price {
                font-weight: bold;
                color: #E63946;
            }

            .product-rating {
                color: #2a9d8f;
            }

            .store-info h2 {
                font-size: 24px;
                color: #333;
                margin: 0;
                padding-right: 20px;
            }

            .store-info .stats {
                margin: 5px 10px;
                color: #666;
                font-size: 14px;
            }

            .store-info img {
                width: 180px;
                height: 180px;
                border-radius: 50%; 
            }

            .shopee-input-quantity {
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: inline-flex;
                -webkit-box-align: center;
                -webkit-align-items: center;
                -moz-box-align: center;
                -ms-flex-align: center;
                align-items: center;
            }

            @media (max-width: 768px) {
                .row {
                    flex-direction: column;
                }
            }

            .comment-section, .comment-list {
                border: 1px solid #ddd;
                background-color: #fff;
                padding: 20px;
                margin-top: 20px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
                border-radius: 8px;
            }

            .comment-item {
                margin-bottom: 20px;
            }

            .comment-item p {
                margin: 5px 0;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-group label {
                font-weight: bold;
            }

            .form-group input[type="text"], .form-group textarea {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }

            .form-group input[type="submit"] {
                background-color: #FF4880;
                color: white;
                border: none;
                padding: 10px 20px;
                cursor: pointer;
                border-radius: 4px;
            }

            .form-group input[type="submit"]:hover {
                background-color: #45a049;
            }
            /* CSS cho đánh giá sao */
            .rating {
                display: flex;
                flex-direction: row-reverse;
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

            .comment-rating {
                display: flex;
                justify-content: flex-start;
                margin-top: 10px;
            }
            .avatar {
                width: 50px; /* Kích thước của ảnh avatar */
                height: 50px;
                border-radius: 50%;
                margin-right: 15px;
                object-fit: cover; /* Đảm bảo ảnh không bị méo */
            }.comment-item img {
                width: 6%;
                height: 6%; /* Đảm bảo chiều cao và chiều rộng bằng nhau */
                border-radius: 50%;
                object-fit: cover; /* Đảm bảo ảnh không bị méo */
            }
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .pagination button {
                margin: 0 5px;
                padding: 10px 20px;
                background-color: #ff4880;
                color: white;
                border: none;
                cursor: pointer;
                border-radius: 4px;
                transition: background-color 0.3s, transform 0.3s;
            }

            .pagination button:hover {
                background-color: #ff4880;
                transform: scale(1.1);
            }

            .pagination .active {
                background-color: #45a049;
                transform: scale(1.1);
            }
          .fa-star {
    color: #f5b301;
}

.fa-star.half::before {
    content: '\f089'; /* Unicode for half star in Font Awesome */
    color: #f5b301;
    position: absolute;
    margin-left: -1em;
    top: 1px;
}

        </style>
    </head>
    <body>
        <div class="container">
            <!-- Thông tin sản phẩm -->
            <div class="product-container">
                <div class="row">
                    <div class="col-md-6 product-image">
                        <img src="img/${product.img}" alt="Sản phẩm">
                    </div>
                    <div class="col-md-6">
                        <h2>${product.productName}</h2>
                        <div style="display: flex; align-items: center;">
                            <div class="comment-rating">
                                <p style="color: red; text-decoration : underline">${TotalRating}</p>
                                <c:forEach var="i" begin="1" end="5">
                                    <i class="fa fa-star" style="position: relative; color: ${i <= TotalRating ? '#f5b301' : (i == Math.ceil(TotalRating) && TotalRating % 1 != 0 ? 'transparent' : '#ccc')};">
                                        <c:if test="${i == Math.ceil(TotalRating) && TotalRating % 1 != 0}">
                                            <i class="fa fa-star half"></i>
                                        </c:if>
                                    </i>
                                </c:forEach>

                            </div>
                            <div style="border-left: 1px solid rgba(0, 0, 0, .14); margin-left: 20px;">
                                <p style="margin-left: 20px;">
                                    <span style="color: red;">${TotalComment}</span> 
                                    <span style="color: black;">đánh giá</span>
                                </p>


                            </div>
                            <div style="border-left: 1px solid rgba(0, 0, 0, .14); margin-left: 20px;">

                                <p style="margin-left: 20px;">
                                    <span style="color: red;">${product.sold}</span> 
                                    <span style="color: black;">Đã bán</span>
                                </p>
                            </div>


                        </div>

                        <p class="price" style="color: red; font-size: 24px;">${product.getPriceString()}</p>

                        <form action="additem">
                            <div class="shopee-input-quantity">
                                <input type="button" class="add-sl shopee-button-outline" title="Bớt"
                                       onclick="var qty_el = document.getElementById('qty');
                                               var qty = qty_el.value;
                                               if (!isNaN(qty) && qty > 1)
                                                   qty_el.value--;
                                               return false;" value="-">
                                <input aria-label="Number" name="amount" id="qty" type="text" size="1"
                                       class="soluong_12 shopee-button-outline shopee-button-outline-mid" value="1"
                                       readonly>
                                <input type="button" class="sub-sl shopee-button-outline" title="Thêm"
                                       onclick="var qty_el = document.getElementById('qty');
                                               var qty = qty_el.value;
                                               if (!isNaN(qty))
                                                   qty_el.value++;
                                               return false;" value="+">
                                <p style="font-size: 16px;margin-bottom: 0px; margin-left: 12px ">${product.productAmount} sản phẩm có sẵn</p>
                            </div>

                            <div class="buttons">
                                <div>
                                    <input name="id" type="text" hidden="" value="${product.productId}">
                                    <c:if test="${sessionScope.user.userId ne product.CTVID}">
                                        <input class="btn btn-primary" type="submit" value="Thêm Vào Giỏ Hàng">
                                        <!--<a href="cart.jsp" class="btn btn-primary" value="Thêm Vào Giỏ Hàng">Mua Ngay</a>-->
                                    </c:if>
                                    <c:if test="${sessionScope.user.userId eq product.CTVID}">
                                        <!--                                        <input class="btn btn-primary" type="submit" value="Thêm Vào Giỏ Hàng">-->
                                        <!--<a href="cart.jsp" class="btn btn-primary" value="Thêm Vào Giỏ Hàng">Mua Ngay</a>-->
                                    </c:if>
                                    <%--<c:if test="${sessionScope.user.userId eq product.CTVID}">--%>
                                    <!--<p>Bạn không thể thêm sản phẩm của mình vào giỏ hàng</p>-->
                                    <%--</c:if>--%>
                                </div>
                                <h3>${message}</h3>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Thông tin gian hàng -->
            <div class="store-info">
                <div class="row">
                    <div class="col-md-1">
                        <img src="img/brand/${brand.brandLogo}" alt="Store Logo">
                    </div>
                    <div class="col-md-9">
                        <h1>${brand.brandName}</h1>
                        <p class="stats">Đánh Giá: 125</p>
                        <p class="stats">Sản Phẩm: 87</p>
                        <p class="stats">Tỉ Lệ Phản Hồi: 100%</p>
                        <p class="stats">Thời Gian Phản Hồi: trong vài giờ</p>
                        <p class="stats">Tham Gia: 48 ngày trước</p>
                        <p class="stats">Người Theo Dõi: 688</p>
                    </div>
                    <div class="col-md-2 text-center">
                        <button class="button">Xem Shop</button>
                        <form action="chat">
                            <input name="CTVID" type="text" hidden="" value="${brand.CTVID}">
                            <button class="button">Nhắn tin</button>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Chi tiết sản phẩm -->
            <div class="product-details">
                <h3>CHI TIẾT SẢN PHẨM</h3>
                <p>Xuất xứ: ${product.origin}</p>
                <p>Kho hàng: ${product.productAmount}</p>
                <p>Gửi từ: ${brand.brandAddess}</p>
            </div>
            <!-- Mô tả sản phẩm -->
            <div class="product-details">
                <h3>MÔ TẢ SẢN PHẨM</h3>
                <p class="auto-line-break">${product.productDescription}</p>
            </div>
            <!-- Thêm bình luận sản phẩm -->

            <!-- Hiển thị bình luận sản phẩm -->
            <div class="comment-list product-details" id="comment-list">
                <h3>ĐÁNH GIÁ SẢN PHẨM</h3>
                <c:forEach var="comment" items="${listComments}">
                    <div class="comment-item">
                        <div class="" style="display: flex;">
                            <img src="img/${comment.user.avatar}" alt="Avatar" class="avatar">
                            <div>
                                <p>${comment.user.firstname} ${comment.user.lastname}</p>
                                <div class="comment-rating">
                                    <c:forEach var="i" begin="1" end="5">
                                        <i class="fa fa-star" style="color: <c:choose>
                                               <c:when test="${i <= comment.rating}">#f5b301</c:when>
                                               <c:otherwise>#ccc</c:otherwise>
                                           </c:choose>;"></i>
                                    </c:forEach>
                                </div>
                                <p class="stats">${comment.createdAt}</p>
                            </div>
                        </div>
                        <p>${comment.comment}</p>
                        <c:if test="${not empty comment.commentImg}">
                            <img style="border-radius: 0%; cursor: pointer;" src="img/commentProduct/${comment.commentImg}" alt="Comment Image" width="200" data-toggle="modal" data-target="#imageModal">
                        </c:if>
                        <hr>
                    </div>
                </c:forEach>
                <div class="pagination" id="pagination"></div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="imageModal" tabindex="-1" role="dialog" aria-labelledby="imageModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                           
                            <button style="margin-left: 445px;" type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body text-center">
                            <img id="modalImage" src="" alt="Comment Image" class="img-fluid">
                        </div>
                    </div>
                </div>
            </div>

            



        </div>
            
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const comments = Array.from(document.querySelectorAll(".comment-item"));
                const commentsPerPage = 5;
                const pagination = document.getElementById("pagination");
                let currentPage = 1;
                const totalPages = Math.ceil(comments.length / commentsPerPage);

                function showPage(page) {
                    comments.forEach((comment, index) => {
                        comment.style.display = (index >= (page - 1) * commentsPerPage && index < page * commentsPerPage) ? "block" : "none";
                    });
                }

                function createPagination() {
                    pagination.innerHTML = "";
                    for (let i = 1; i <= totalPages; i++) {
                        const button = document.createElement("button");
                        button.textContent = i;
                        button.classList.add("page-btn");
                        if (i === currentPage)
                            button.classList.add("active");
                        button.addEventListener("click", function () {
                            currentPage = i;
                            showPage(currentPage);
                            document.querySelector(".pagination .active").classList.remove("active");
                            button.classList.add("active");
                        });
                        pagination.appendChild(button);
                    }
                }

                showPage(currentPage);
                createPagination();
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const paragraphs = document.querySelectorAll('.auto-line-break');
                paragraphs.forEach(p => {
                    p.innerHTML = p.innerHTML.replace(/\./g, '.<br>');
                });
            });
        </script>
        <script>
                $('#imageModal').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    var imageSrc = button.attr('src'); // Extract info from data-* attributes
                    var modal = $(this);
                    modal.find('.modal-body #modalImage').attr('src', imageSrc);
                });
            </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
