<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Timeline</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            .timeline {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin: 20px 0;
                padding: 0;
                list-style-type: none;
            }

            .timeline li {
                flex: 1;
                position: relative;
                text-align: center;
            }


            .timeline li::after {
                content: '';
                position: absolute;
                top: 50%;
                right: -50%;
                height: 4px;
                width: 100%;
                background-color: #ccc; /* Màu xám cho tất cả các bước */
                z-index: -1;
            }

            .timeline li.active::after {
                background-color: #4CAF50; /* Màu xanh cho các bước active và các bước trước đó */
            }

            .timeline .icon {
                background-color: #4CAF50;
                color: white;
                border-radius: 50%;
                padding: 10px;
                display: inline-block;
                margin-bottom: 30px;
            }

            .timeline .icon.active {
                background-color: #4CAF50;
            }

            .timeline .icon.inactive {
                background-color: #ccc;
            }

            .timeline .step-title {
                font-weight: bold;
            }

            .timeline .step-date {
                color: #666;
            }

            .timeline .step {
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            .timeline li.inactive::after {
                content: '';
                position: absolute;
                top: 50%;
                right: -50%;
                height: 4px;
                width: 100%;
                background-color: #ccc; /* Màu xám nhạt cho các bước chưa hoàn thành */
                z-index: -1;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="container">
                <div class="donhang">
                    <div class="border-start border-5 border-primary ps-5 mb-5 mt-5">
                        <h6 class="text-primary text-uppercase">Đơn hàng</h6>
                        <h1 class="display-5 text-uppercase mb-0">Kiểm tra đơn hàng </h1>
                    </div>
                    <div class="d-flex justify-content-center">
                        <h2 class="text-primary m-1 p-0">Tình trạng đơn hàng : </h2>
                        <h2 class="text-danger m-1 p-0">${orderStatus}</h2>
                    </div>
                    <ul class="timeline">
                        <c:if test="${orderStatus == 'Đang xử lý-COD'}">
                            <li >
                                <div class="step">
                                    <span class="icon avtive">
                                        <i class="fas fa-receipt"></i>
                                    </span>
                                    <span class="step-title">Đơn Hàng Đã Đặt</span>

                                </div>
                            </li>
                            <li>
                                <div class="step">
                                    <span class="icon inactive">
                                        <i class="fas fa-dollar-sign"></i>
                                    </span>
                                    <span class="step-title">Đã Xác Nhận Thông Tin Thanh Toán</span>

                                </div>
                            </li>

                            <li>
                                <div class="step">
                                    <span class="icon inactive">
                                        <i class="fas fa-box-open"></i>
                                    </span>
                                    <span class="step-title">Đã Nhận Được Hàng</span>

                                </div>
                            </li>
                            <li>
                                <div class="step">
                                    <span class="icon inactive"><i class="fas fa-star"></i></span>
                                    <span class="step-title">Đánh Giá</span>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${orderStatus == 'Đã thanh toán'}">
                            <li class="active">
                                <div class="step">
                                    <span class="icon avtive">
                                        <i class="fas fa-receipt"></i>
                                    </span>
                                    <span class="step-title">Đơn Hàng Đã Đặt</span>

                                </div>
                            </li>
                            <li >
                                <div class="step">
                                    <span class="icon avtive">
                                        <i class="fas fa-dollar-sign"></i>
                                    </span>
                                    <span class="step-title">Đã Xác Nhận Thông Tin Thanh Toán</span>

                                </div>
                            </li>

                            <li>
                                <div class="step">
                                    <span class="icon inactive">
                                        <i class="fas fa-box-open"></i>
                                    </span>
                                    <span class="step-title">Đã Nhận Được Hàng</span>

                                </div>
                            </li>
                            <li>
                                <div class="step">
                                    <span class="icon inactive "><i class="fas fa-star"></i></span>
                                    <span class="step-title">Đánh Giá</span>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${orderStatus == 'Đã hoàn thành'}">
                            <li class="active">
                                <div class="step">
                                    <span class="icon avtive">
                                        <i class="fas fa-receipt"></i>
                                    </span>
                                    <span class="step-title">Đơn Hàng Đã Đặt</span>

                                </div>
                            </li>
                            <li class="active">
                                <div class="step">
                                    <span class="icon avtive">
                                        <i class="fas fa-dollar-sign"></i>
                                    </span>
                                    <span class="step-title">Đã Xác Nhận Thông Tin Thanh Toán</span>

                                </div>
                            </li>

                            <li >
                                <div class="step">
                                    <span class="icon avtive">
                                        <i class="fas fa-box-open"></i>
                                    </span>
                                    <span class="step-title">Đã Nhận Được Hàng</span>

                                </div>
                            </li>
                            <li>
                                <div class="step">
                                    <span class="icon inactive"><i class="fas fa-star"></i></span>
                                    <span class="step-title">Đánh Giá</span>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${orderStatus == 'Đã đánh giá'}">
                            <li class="active">
                                <div class="step">
                                    <span class="icon avtive">
                                        <i class="fas fa-receipt"></i>
                                    </span>
                                    <span class="step-title">Đơn Hàng Đã Đặt</span>

                                </div>
                            </li>
                            <li class="active">
                                <div class="step">
                                    <span class="icon avtive">
                                        <i class="fas fa-dollar-sign"></i>
                                    </span>
                                    <span class="step-title">Đã Xác Nhận Thông Tin Thanh Toán</span>

                                </div>
                            </li>

                            <li class="active">
                                <div class="step">
                                    <span class="icon avtive">
                                        <i class="fas fa-box-open"></i>
                                    </span>
                                    <span class="step-title">Đã Nhận Được Hàng</span>

                                </div>
                            </li>
                            <li>
                                <div class="step">
                                    <span class="icon avtive"><i class="fas fa-star"></i></span>
                                    <span class="step-title">Đánh Giá</span>
                                </div>
                            </li>
                        </c:if>

                    </ul>
                    <c:if test="${orderStatus != 'Đã hủy'}">
                        <div class="mt-5 text-center">
                            <p>Khách vui lòng đồng kiểm với bưu tá khi nhận hàng và quay video để đảm bảo nhận hàng đầy đủ.</p>

                        </div>
                    </c:if>
                    <div class="border-bottom">
                        <h3>Thông tin khách hàng</h3>
                        <div>
                            <p>Tên: ${sessionScope.user.firstname} ${sessionScope.user.lastname}</p>
                            <p>Số điện thoại: ${sessionScope.user.phone}</p>
                            <p>Địa chỉ: ${sessionScope.user.address}</p>
                        </div>
                    </div>
                    <div class="border-bottom mt-3">
                        <h3>Thông tin đơn hàng: ${orderId}</h3>
                        <div class="d-flex justify-content-around">
                            <div class="border-top pt-3 col-md-7 kt-left">
                                <div class="border-bottom">
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
                                <span>Tổng vorcher giảm giá:</span>
                                <strong style="font-size: larger;">- ${priceWithDiscount}</strong>
                            </div>
                        </c:if>
             
                        <div class="w-50 border-top pt-2 mt-3 d-flex justify-content-between">
                            <span>Tổng cộng:</span>
                            <c:if test="${discountCode == null}">
                                <strong style="font-size: x-large;">${totalPriceAll}</strong>
                            </c:if>
                            <c:if test="${not empty discountCode}">
                                <strong style="font-size: x-large;">
                                    <!--<del>${totalPriceAll}</del>-->
                                    ${totalPriceAfterDiscount}
                                </strong>
                            </c:if>
                        </div>
                    </div>
<!--                    <div class="border-top pt-3 col-md-4 kt-right">
                        <div>
                            <c:if test="${orderStatus.equals('Đang xử lý-CK')}">
                                <img width="90%" src="https://img.vietqr.io/image/BIDV-5660479367-print.png?amount=${totalPriceAll}&addInfo=Thanh toan hoa don ${orderId}&accountName=Đặng Phương Nam" alt="">
                            </c:if>
                        </div>
                    </div>-->
                    <p>Nếu bạn có bất cứ câu hỏi nào, đừng ngần ngại liên lạc với chúng tôi qua Hotline: 0795632854 (8h - 19h)</p>
                </div>
            </div>
        </div>
    </body>
</html>
