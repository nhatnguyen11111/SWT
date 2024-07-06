<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<div class="container-fluid">
    <div class="container">
        <div class="donhang">
            <div class="border-start border-5 border-primary ps-5 mb-5 mt-5">
                <h6 class="text-primary text-uppercase">Đơn hàng</h6>
                <h1 class="display-5 text-uppercase mb-0">Kiểm tra đơn hàng của bạn</h1>
            </div>
            <div class="d-flex justify-content-center">
                <h2 class="text-primary m-1 p-0">Tình trạng đơn hàng của bạn: </h2>
                <h2 class="text-danger m-1 p-0"> ${orderStatus}</h2>
            </div>
            <div class="mt-5 text-center">
                <p class="">Khách vui lòng đồng kiểm với bưu tá khi nhận hàng và quay video để đảm bảo nhận hàng đầy đủ.</p>
                <p>Đơn hàng của quý khách sẽ được gửi cho hãng vận chuyển Viettel Post</p>
            </div>
            <div class="border-bottom">
                <h3>Thông tin khách hàng</h3>
                <div>
                    <p>${sessionScope.user.firstname} ${sessionScope.user.lastname}</p>
                    <p>Số điện thoại: ${sessionScope.user.phone}</p>
                    <p>Địa chỉ: ${sessionScope.user.address}</p>
                </div>
            </div>
            <c:forEach var="orderId" items="${orderIds}">
                <div class="border-bottom mt-3">
                    <h3>Thông tin đơn hàng: ${orderId}</h3>
                    <div class="d-flex justify-content-around">
                        <div class="border-top pt-3 col-md-7 kt-left">
                            <div class="border-bottom">
                                <c:set var="orderItems" value="${orderItemsMap[orderId]}" />
                                <c:forEach items="${orderItems}" var="item">
                                    <div class="border-bottom mb-3">
                                        <div class="d-flex">
                                            <div class="p-1">                       
                                                <img width="60px" height="60px" src="img/${item.product.img}" alt="">
                                            </div>
                                            <div class="p-1">
                                                <strong>${item.product.productName}</strong>
                                            </div>
                                        </div>
                                        <div style="text-align: right; font-size: larger;" class="p-1">
                                            <strong>X ${item.getAmount()}</strong>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="mt-3">
                <div class="w-50 d-flex justify-content-between">
                    <span>Tổng giá trị sản phẩm:</span>
                    <strong style="font-size: larger;">${totalPrice}</strong>
                </div>
                <div class="w-50 d-flex justify-content-between">
                    <span>Phí vận chuyển:</span>
                    <strong style="font-size: larger;">${totalShippingFee}</strong>
                </div>
          
            
                <c:if test="${not empty discountCode}">
                    <div class="w-50 d-flex justify-content-between">
                        <span>Voucher giảm giá:</span>
                        <strong style="font-size: larger;">- ${priceWithDiscount}</strong>
                    </div>
                </c:if>
                <div class="w-50 border-top pt-2 mt-3 d-flex justify-content-between">
                    <span>Tổng cộng:</span>
                    <c:if test="${discountCode == null}">
                        <strong style="font-size: x-large;">${totalPriceAll}</strong>
                    </c:if>
                    <c:if test="${discountCode != null}">
                        <strong style="font-size: x-large;">${totalPriceAllWithDiscount}</strong>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
