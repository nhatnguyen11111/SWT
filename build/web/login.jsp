<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/vapeShop.jpg">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <title>Đăng Nhập</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: #ffecf2;
        }

        .box-area {
            width: 930px;
            margin: auto;
        }

        .right-box {
            padding: 40px 30px 40px 40px;
        }

        .left-box {
            background: #FF4880;
            color: white;
        }

        .rounded-4 {
            border-radius: 20px;
        }

        .rounded-5 {
            border-radius: 30px;
        }

        @media only screen and (max-width: 768px) {
            .box-area {
                margin: 0 10px;
            }
            .left-box {
                height: 100px;
                overflow: hidden;
            }
            .right-box {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
<% 
String errorMessage = (String) request.getAttribute("errorMessage");
if (errorMessage != null) {
%>
    <div class="alert alert-danger"><%= errorMessage %></div>
<% 
}
%>
<!-- Phần còn lại của mã HTML trong login.jsp -->

<div class="container d-flex justify-content-center align-items-center min-vh-100">

    <div class="row border rounded-5 p-3 bg-white shadow box-area">

        <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
            <div class="featured-image mb-3">
                <img src="img/logobaby.png" class="img-fluid rounded-circle" style="width: 250px;">
            </div>
            <p class="fs-2" style="font-family: 'Courier New', Courier, monospace; font-weight: 600;">BabyCare Login.</p>
            <small class="text-wrap text-center" style="width: 17rem; font-family: 'Courier New', Courier, monospace;">TRẺ EM LÀ MẦM NON CỦA ĐẤT NƯỚC.</small>
        </div>

        <div class="col-md-6 right-box">
            <div class="row align-items-center">
                <div class="header-text mb-4">
                    <h2> Xin chào <i class="fa fa-heart fa-beat-fade" style="color: #ff0000;"></i> </h2>
                    <p>Chúng tôi rất vui khi có bạn trở lại.<c:out value="${error}" /></p>
                </div>

                <form action="login" method="post">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <div class="input-group-text p-3"><i class="fa fa-user fa-bounce" style="color: #000000;"></i></div>
                        </div>
                        <input maxlength="100" type="text" name="username" class="form-control form-control-lg bg-light fs-6 p-1" placeholder=" Địa chỉ email hoặc Người dùng" value="${cookie.username.value}">
                    </div>
                    <div class="input-group mb-1">
                        <div class="input-group-prepend">
                            <div class="input-group-text p-3"><i class="fa fa-key fa-bounce" style="color: #050505;"></i></div>
                        </div>
                        <input maxlength="50" type="password" name="password" id="myInput" class="form-control form-control-lg bg-light fs-6 p-1" placeholder=" Mật Khẩu" value="${cookie.password.value}">
                    </div>
                     <h4 class="bg-warning  text-danger " style="text-align: center;background-color: #fa6342;padding: 2px 2px; font-size: 16px;margin:2px 0px "> ${thongbao}</h4>

                    <c:if test="${not empty userOrEmail}">
                        <div class="alert alert-danger" style="color: red">${userOrEmail}</div>
                    </c:if>
                    
                    <c:if test="${error == 1}">
                        <div class="alert alert-danger" style="color: red">Tài khoản này đang tạm khóa vui lòng liên hệ admin</div>
                    </c:if>
                    <div class="input-group mb-5 d-flex justify-content-between">
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="formCheck" onclick="myFunction()">
                            <label for="formCheck" class="form-check-label text-secondary"><small>Hiện thị mật khẩu</small></label>
                        </div>
                        <div class="forgot">
                            <small><a href="forgotpassword.jsp">Quên mật khẩu?</a></small>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <button class="btn btn-lg btn-primary w-100 fs-6"><i class="fa fa-right-to-bracket fa-beat-fade" style="color: #000000;"></i> Đăng nhập</button>
                    </div>
                </form>
                <div class="row">
                    <small>Không có tài khoản? <a href="register.jsp">Đăng ký ngay</a></small>
                </div>
            </div>
        </div>

    </div>
</div>

<script>
    function myFunction() {
        var x = document.getElementById("myInput");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/fc6bd51969.js" crossorigin="anonymous"></script>
</body>
</html>
