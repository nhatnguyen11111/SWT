<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<div class="cart-page">
    <style>
        .cart-page {
            position: relative;
            margin-top: 30px;
        }

        .cart-page .cart-page-inner {
            padding: 30px;
            margin-bottom: 30px;
            background: #ffffff;
        }

        .table-responsive {
            display: block;
            width: 100%;
            overflow-x: auto;
            -webkit-overflow-scrolling: touch;
        }

        .cart-page .table {
            width: 100%;
            text-align: center;
            margin-bottom: 0;
        }

        .table-responsive > .table-bordered {
            border: 0;
        }

        .table-bordered {
            border: 1px solid #dee2e6;
        }

        .cart-page .table td {
            font-size: 16px;
            vertical-align: middle;
        }

        .table-bordered td, .table-bordered th {
            border: 1px solid #dee2e6;
        }

        .table td, .table th {
            padding: 0.75rem;
            vertical-align: top;
            border-top: 1px solid #dee2e6;
        }

        .cart a {
            color: #7AB730;
        }

        .cart-page .table .img {
            display: flex;
            align-items: center;
        }

        .cart-page .table .img img {
            max-width: 60px;
            max-height: 60px;
            margin-right: 15px;
        }

        img {
            vertical-align: middle;
            border-style: none;
        }

        .cart-page .table .qty {
            display: inline-block;
            width: 100px;
            font-size: 0;
        }

        .cart-page .table button.btn-minus {
            border-radius: 4px 0 0 4px;
        }

        .cart-page .table button.btn-plus {
            border-radius: 0 4px 4px 0;
        }

        .cart-page .table button {
            width: 30px;
            height: 30px;
            font-size: 14px;
            text-align: center;
            color: #ffffff;
            background: #7AB730;
            border: none;
            border-radius: 4px;
        }

        .cart-page .table input {
            width: 40px;
            height: 29px;
            font-size: 16px;
            color: #ffffff;
            text-align: center;
            background: #333333;
            border: none;
        }

        .cart-page .cart-page-inner {
            padding: 30px;
            margin-bottom: 30px;
            background: #ffffff;
        }

        .row {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-wrap: wrap;
            flex-wrap: wrap;
            margin-right: -15px;
            margin-left: -15px;
        }

        .cart-page .coupon {
            display: flex;
            position: relative;
            width: 100%;
            margin-bottom: 15px;
            font-size: 0;
        }

        .cart-page .coupon input {
            width: calc(100% - 135px);
            height: 40px;
            padding: 0 15px;
            font-size: 16px;
            color: #999999;
            background: #ffffff;
            border: 1px solid #dddddd;
            border-radius: 4px;
            margin-right: 15px;
            transition: all .3s;
        }

        .cart-page .coupon .button {
            width: 120px;
            height: 40px;
            padding: 2px 0;
            font-size: 16px;
            text-align: center;
            color: #7AB730;
            background: #ffffff;
            border: 1px solid #7AB730;
            border-radius: 4px;
        }

        .cart-page .coupon .button:hover {
            background-color: #7AB730;
            color: white;
        }

        .cart-page .cart-summary {
            position: relative;
            width: 100%;
        }

        .cart-page .cart-summary .cart-content {
            padding: 30px;
            background: #f3f6ff;
        }

        .cart-page .cart-summary .cart-content h1 {
            font-size: 22px;
            margin-bottom: 20px;
        }

        .cart-page .cart-summary .cart-content h2 {
            font-size: 20px;
            font-weight: 600;
            padding-top: 12px;
            border-top: 1px solid #dddddd;
            margin: 0;
        }

        .cart-page .cart-summary .cart-btn button:first-child {
            margin-right: 25px;
            color: #7AB730;
            background: #ffffff;
            border: 1px solid #7AB730;
            transition: 200ms;
        }

        .cart-page .cart-summary .cart-btn button:first-child:hover {
            background-color: #7AB730;
            color: white;
        }

        .cart-page .cart-summary .cart-btn button {
            margin-top: 15px;
            width: calc(50% - 15px);
            height: 50px;
            padding: 2px 10px;
            text-align: center;
            color: #ffffff;
            background: #7AB730;
            border: none;
            border-radius: 4px;
            transition: 200ms;
        }

        .cart-page .cart-summary .cart-btn button:hover {
            color: #7AB730;
            background-color: white;
            border: 1px solid #7AB730;
        }
        .cart-page .table td .checkbox {
            margin: 0 auto;
            display: block;
        }
        .text-right {
            text-align: right;
            display: inline-block;
            width: 100%;
        }
        .flex-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
        }

        .flex-container .text-right {
            text-align: right;
        }
        .flex-container p {
            width: 500px;
            margin-top: 0;
        }
        .flex-container h2 {
            width: 500px;
            margin-top: 0;
            padding-top: 0;
        }
    </style>
    <c:if test="${sessionScope.user == null}">
        <% response.sendRedirect("login.jsp");%>
    </c:if>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-8">
                <div class="cart-page-inner">
                    <div class="table-responsive">

                        <table class="table table-bordered">
                            <thead class="thead-dark">  
                                <tr>
<!--                                    <th>Chọn</th>-->
                                    <th>Shop</th>
                                    <th>Sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Tạm tính</th>
                                    <th>Xóa</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach var="item" items="${itemsList}">
                                    <tr>
<!--                                        <td>
                                            <input type="checkbox" name="selectedItems" value="${item.product.productId}" class="checkbox">
                                        </td>-->
                                        <td>
                                            <p>${item.brandName}</p>
                                        </td>
                                        <td>
                                            <div class="img">
                                                <img src="img/${item.product.img}" alt="Image">
                                                <p>${item.product.productName}</p>
                                            </div>
                                        </td>
                                        <td>${item.product.getPriceString()}</td>
                                        <td>
                                            <div class="qty d-flex">


                                                <form action="changeamount">
                                                    <input name="decrease" value="${item.product.productId}" type="hidden">
                                                    <button type="submit" class="btn-minus"><i class="fa fa-minus"></i></button>
                                                </form>

                                                <input type="text" value="${item.amount}">
                                                <form action="changeamount">
                                                    <input name="increase" value="${item.product.productId}" type="hidden">
                                                    <button class="btn-plus" type="submit"><i class="fa fa-plus"></i></button>
                                                </form>
                                            </div>
                                        </td>
                                        <td>${item.getPriceString()}</td>
                                        <td>
                                            <form action="changeamount">
                                                <input name="delete" value="${item.product.productId}" type="hidden">
                                                <button type="submit"><i class="fa fa-trash"></i></button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="cart-page-inner">
                    <div class="row">
                        <form action="applydiscount">
                            <div class="col-md-12">
                                <div class="coupon">
                                    <input type="text" placeholder="Coupon Code" name="discountCode">
                                    <input class="button" type="submit" value="Apply Code">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <p>${message}</p>
                            </div>
                        </form>
                        <div class="col-md-12">
                            <div class="cart-summary">
                                <div class="cart-content">
                                    <h1>Thanh toán đơn hàng</h1>
                                    <form id="myForm" class="form-inline" action="makeorder" method="get">
                                        <div class="form-group mr-2">
                                            <label for="paymentMethod">Chọn hình thức thanh toán</label>
                                        </div>
                                        <div class="form-group">
                                            <select class="form-control" name="paymentMethod" id="paymentMethod" onchange="changeFormAction(this)">
                                                <option value="0">Thanh toán khi nhận hàng</option>
                                                <option value="1">Chuyển khoản</option>
                                            </select>
                                        </div>
                                        <div class="flex-container">
                                            <p>Tạm tính:</p> 
                                            <span class="text-right">${totalAmount}</span>
                                        </div>
                                        <div class="flex-container">
                                            <p>Tiền ship:</p>
                                            <span class="text-right">${totalShippingFee}</span>
                                        </div>
                                        <c:if test="${discountCode == null}">
                                            <div class="flex-container">
                                                <h2>Tổng cộng:</h2>
                                                <span class="text-right">${calculateWithShip}</span>
                                            </div>
                                        </c:if>
                                        <input type="hidden" name="discountCode" value="${discountCode}" />

                                        <c:if test="${discountCode != null}">
                                            <div class="flex-container">
                                                <p>Discount :</p>
                                                <span class="text-right">-${discountPercent * 100}%</span>
                                            </div>
                                            <div class="flex-container">
                                                <p>Tổng vourcher giảm giá :</p>
                                                <span class="text-right">-${calculatePriceDiscout}</span>
                                            </div>
                                            <div class="flex-container">
                                                <h2 style="padding-top: 0;">Tổng Thanh Toán:</h2>
                                                <span class="text-right" style="color: red;">${totalPriceAllWithDiscount}</span>
                                            </div>
                                        </c:if>
                                        <div class="cart-btn">
                                            <button type="submit">Đặt hàng</button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function changeFormAction(select) {
        var form = document.getElementById("myForm");
        if (select.value == "1") {
            form.action = "makeorderbank";
        } else {
            form.action = "makeorder";
        }
    }

    window.onload = function () {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '${pageContext.request.contextPath}/removediscountcode', true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send();

        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log('Session attributes removed successfully');
            } else {
                console.error('Failed to remove session attributes');
            }
        };
    };
</script>