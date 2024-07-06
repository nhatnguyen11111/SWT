<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
    .product-item-link {
        text-decoration: none;
    }
    .product-item-link .product-item {
        border: 1px solid #ddd;
        transition: transform 0.3s ease, border-color 0.3s ease;
        height: 250px; /* Cố định chiều cao của sản phẩm */
        display: flex;
        flex-direction: column;
    }
    .product-item-link:hover .product-item,
    .product-item-link.active .product-item {
        transform: scale(1.05);
        border-color: #ff4880; /* màu viền khi active */
    }
    .product-image, .product-details {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .product-image {
        border-bottom: 1px solid #ddd; /* Đường viền ngăn cách */
    }
    .product-image img {
        width: 100%; /* Đặt chiều rộng của hình ảnh để chiếm hết chiều rộng của khung */
        height: 120px; /* Đặt chiều cao của hình ảnh để chiếm hết chiều cao của khung */
        object-fit: cover; /* Đảm bảo hình ảnh không bị méo */
    }
    .product-details {
        position: relative;
        display: block;
        text-align: center;
        padding: 10px;
    }
    .product-name{
        
    }
    .product-price{
        position: absolute;
        top: 95px;
        font-size: 12px;
    }
</style>
<div class="container-fluid d-none d-lg-block pt-5">
  <div class="container">
    <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
      <h6 class="text-primary text-uppercase">Sản Phẩm</h6>
      <form action="SearchControl?index=1" method="post">
          <input class="searchBox" type="text" name="txtSearch" size="30" required>
          <input class="serachButton" type="submit" name="btnGo" value="Go">
      </form>
    </div>
    <div class="row g-5" id="productContainer">
      <c:forEach var="product" items="${productList}">
          <div class="col-lg-2 col-md-4 col-sm-6" style="margin-top :12px">
          <a href="getProductDetail?id=${product.productId}" class="product-item-link">
            <div class="product-item position-relative bg-white d-flex flex-column text-center">
              <div class="product-image">
                <img class="img-fluid" src="img/${product.img}" alt="">
              </div>
              <div class="product-details">
                  <h6 class="product-name" style="font-size: 12px; text-align: left;">${product.productName}</h6>
                <h5 class="text-primary mb-0 product-price">₫ ${product.getPriceString()}</h5>
              </div>
            </div>
          </a>
        </div>
      </c:forEach>
    </div>
  </div>
</div>

<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#productContainer .col").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });

        // Thêm lớp active khi click vào sản phẩm
        $(".product-item-link").on("click", function () {
            $(".product-item-link").removeClass("active");
            $(this).addClass("active");
        });
    });
</script>
