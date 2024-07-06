<!DOCTYPE html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <title>Update Service</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<nav class="nav nav-pills nav-justified">
    <a class="nav-item nav-link active" href="#">Thông tin chung</a>
</nav>

<div class="container mt-5">
    <h2>Cập nhật dịch vụ</h2>
    <form class="needs-validation mt-3" novalidate action="UpdateService" method="post">
        <div class="row">
            <div class="col-12" style="background-color:#7ab730;border-radius: 10px;">
                <h2>Cập nhật dịch vụ</h2>
            </div>

            <!-- Input for Service ID (hidden) -->
            <div class="col-md-6 offset-3 mb-3">
                <label for="serviceID">ID dịch vụ</label>
                <div class="input-group">
                    <input type="hidden" class="form-control" id="serviceID" name="serviceID" value="${service.serviceID}" required>
                    <h3>${service.serviceID}</h3>
                    <div class="invalid-feedback">
                        Vui lòng nhập ID dịch vụ.
                    </div>
                </div>
            </div>

            <!-- Input for Service Name -->
            <div class="col-md-6 offset-3 mb-3 mt-3">
                <label for="serviceName">Tên dịch vụ</label>
                <div class="input-group">
                    <input pattern="^.{1,50}$" type="text" class="form-control" id="serviceName" placeholder="Tên dịch vụ" name="serviceName"
                           aria-describedby="inputGroupPrepend" value="${service.serviceName}" required>
                    <div class="invalid-feedback">
                        Tối đa 1-50 ký tự.
                    </div>
                </div>
            </div>

            <!-- Input for Service Price -->
            <div class="col-md-6 offset-3 mb-3">
                <label for="servicePrice">Giá dịch vụ</label>
                <div class="input-group">
                    <input type="text" pattern="^(?:\d+|\d*\.\d+)$" class="form-control" id="servicePrice" placeholder="Giá dịch vụ" name="servicePrice"
                           aria-describedby="inputGroupPrepend" value="${service.servicePrice}" required>
                    <div class="invalid-feedback">
                        Giá tiền chỉ bao gồm chữ số.
                    </div>
                </div>
            </div>

            <!-- Input for Service Image URL -->
            <div class="col-md-6 offset-3 mb-3">
                <label for="listImg">Ảnh</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="listImg" placeholder="Ảnh" name="listImg"
                           aria-describedby="inputGroupPrepend" value="${service.listImg}" required>
                    <div class="invalid-feedback">
                        Vui lòng điền vào.
                    </div>
                </div>
            </div>

            <!-- Input for Service Description -->
            <div class="col-md-6 offset-3 mb-3">
                <label for="description">Mô tả</label>
                <div class="input-group">
                    <input type="text" pattern="^.{1,100}$" class="form-control" id="description" placeholder="Mô tả" name="description" value="${service.description}"
                           aria-describedby="inputGroupPrepend" required>
                    <div class="invalid-feedback">
                        Độ dài tối đa 100 ký tự.
                    </div>
                </div>
            </div>

            <!-- Submit Button -->
            <div class="row">
                <div class="col-3 offset-3">
                    <button class="btn btn-primary" type="submit">Cập nhật</button>
                </div>
                <div class="col-6">${thongbao}</div>
            </div>
        </div>
    </form>
</div>

<!-- Add Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<!-- Custom JS for form validation -->
<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

</body>
</html>
