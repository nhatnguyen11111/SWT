<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Tạo mới đơn hàng</title>
    <link href="assets/bootstrap.min.css" rel="stylesheet"/>
    <link href="assets/jumbotron-narrow.css" rel="stylesheet">      
    <link href="assets/custom.css" rel="stylesheet"/>
    <script src="assets/jquery-1.11.3.min.js"></script>
    
    <style>
        body {
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
            background-color: #f5f5f5;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .header {
            border-bottom: 1px solid #e5e5e5;
            margin-bottom: 20px;
            padding-bottom: 10px;
        }

        .header h3 {
            margin-top: 0;
            color: #333;
        }

        h3 {
            color: #333;
            margin-bottom: 20px;
        }

        .table-responsive {
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="radio"] {
            margin-right: 10px;
        }

        button.btn {
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 4px;
            background-color: #007bff;
            border-color: #007bff;
            color: #fff;
            transition: background-color 0.3s, border-color 0.3s;
        }

        button.btn:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        footer.footer {
            text-align: center;
            padding: 20px 0;
            border-top: 1px solid #e5e5e5;
            margin-top: 20px;
            color: #777;
        }

        footer.footer p {
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header clearfix">
            <h3 class="text-muted">VNPAY DEMO</h3>
        </div>
        <h3>Tạo mới đơn hàng</h3>
        <div class="table-responsive">
            <form action="submitOrderUrl" id="frmCreateOrder" method="post">
                <input type="hidden" name="discountCode" value="${discountCode}" />
<!--                <label for="amount">Mã Code: </label>
                <strong name="discountCode" style="font-size: larger;">${discountCode}</strong>
                <label for="amount">Mã Code: </label>
                <strong name="typePayment" style="font-size: larger;">${typePayment}</strong>-->
                <div class="form-group">
                    <label for="amount">Số tiền</label>
<!--                    <input class="form-control" data-val="true" data-val-number="The field Amount must be a number." data-val-required="The Amount field is required." id="amount" max="100000000" min="1" name="amount" type="number" value="10000" />-->
                    <c:if test="${ sessionScope.discountCode == null}">
                      
                       <input class="form-control" data-val="true" data-val-number="The field Amount must be a number." data-val-required="The Amount field is required." id="amount" max="100000000" min="1" name="amount" type="number"  value="${totalPriceAll}" readonly />


                    </c:if>
                    <c:if test="${ sessionScope.discountCode != null}">
                       
                        
                        <input class="form-control" data-val="true" data-val-number="The field Amount must be a number." data-val-required="The Amount field is required." id="amount" max="100000000" min="1" name="amount" type="number" readonly value="${totalPriceAllWithDiscount}" />

                    </c:if>
                    <div id="error-message" style="color: red; display: none;">Số tiền thanh toán không hợp lệ</div>
                </div>
                <h4>Chọn phương thức thanh toán</h4>
                <div class="form-group">
                    <h5>Cách 1: Chuyển hướng sang Cổng VNPAY chọn phương thức thanh toán</h5>
                    <input type="radio" checked="true" id="bankCode" name="bankCode" value="">
                    <label for="bankCode">Cổng thanh toán VNPAYQR</label><br>
                    <h5>Cách 2: Tách phương thức tại site của đơn vị kết nối</h5>
                    <input type="radio" id="bankCode" name="bankCode" value="VNPAYQR">
                    <label for="bankCode">Thanh toán bằng ứng dụng hỗ trợ VNPAYQR</label><br>
                    <input type="radio" id="bankCode" name="bankCode" value="VNBANK">
                    <label for="bankCode">Thanh toán qua thẻ ATM/Tài khoản nội địa</label><br>
                    <input type="radio" id="bankCode" name="bankCode" value="INTCARD">
                    <label for="bankCode">Thanh toán qua thẻ quốc tế</label><br>
                </div>
                <div class="form-group">
                    <h5>Chọn ngôn ngữ giao diện thanh toán:</h5>
                    <input type="radio" id="language" checked="true" name="language" value="vn">
                    <label for="language">Tiếng việt</label><br>
                    <input type="radio" id="language" name="language" value="en">
                    <label for="language">Tiếng anh</label><br>
                </div>
                <button type="submit" class="btn btn-primary" href="#">Thanh toán</button>
            </form>
        </div>
        <p>&nbsp;</p>
        <footer class="footer">
            <p>&copy; VNPAY 2020</p>
        </footer>
    </div>
    <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
    <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#frmCreateOrder").submit(function (e) {
                e.preventDefault(); // Ngăn chặn submit mặc định

//                if (!checkAmount()) {
//                    console.log("Số tiền thanh toán không hợp lệ");
//                    $("#error-message").show(); // Hiển thị thông báo lỗi
//                    // Cuộn lên đầu trang
//                    $("html, body").animate({ scrollTop: 0 }, "slow");
//                    return false;
//                }

                var postData = $("#frmCreateOrder").serialize();
                var submitUrl = $("#frmCreateOrder").attr("action");
                $.ajax({
                    type: "POST",
                    url: submitUrl,
                    data: postData,
                    dataType: 'JSON',
                    success: function (x) {
                        if (x.code === '00') {
                            if (window.vnpayorder) {
                                vnpayorder.open({width: 768, height: 600, url: x.data});
                            } else {
                                location.href = x.data;
                            }
                            return false;
                        } else {
                            alert(x.Message);
                        }
                    }
                });
            });

//            function checkAmount() {
//                var amountInput = document.getElementById('amount').value;
//                var totalAmount = document.getElementById('totalAmount') ? document.getElementById('totalAmount').innerText.trim() : null;
//                var totalAmountWithDiscount = document.getElementById('totalAmountWithDiscount') ? document.getElementById('totalAmountWithDiscount').innerText.trim() : null;
//
//                if (amountInput != totalAmount && amountInput != totalAmountWithDiscount) {
//                    return false;
//                }
//                return true;
//            }
        });
    </script>
</body>
</html>

