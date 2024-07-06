<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container mt-3">
    <form class="needs-validation" novalidate action="changepass" method="post">
    <div class="row">
        <div class="col-md-3" style="background-color: hwb(48 90% 2%); border-radius: 10px; width: 200px; height: 250px; margin-right: 20px;">
            <div>
                <div class="col-12">
                    <div style=" margin: 15px;">
                        <img style="width: 50px; height: 83%; border-radius: 50%; margin-top: 5px;"src="img/${avatar}" class="img-fluid" alt="Ảnh cá nhân">
                    </div>
                    <h2 style=" font-size:15px; font-weight: 550px;">${firstname}  ${lastname}</h2>
                </div>
            </div>

            <section>
                <a href="profile" class="icon-link">
                    <i class="glyphicon glyphicon-user icon"></i>
                    <span class="text">Profile</span>
                </a>
                
            </section>
        </div>
        <div class="col-md-9" style="background-color: hwb(319 83% 3%);
             border-radius: 10px;
             padding: 1rem;
             margin-bottom: 2rem;">
            <div class="row">
                <div class="container mt-3">
                        <div class="row">
                             <div class="col-12" style="border-radius: 10px; text-align: center;">
                                <h2  style="font-weight: 600;color: white;font-size: 22px ;font-family: 'Roboto', sans-serif;" >Change PassWord</h2>
                            </div>
                            <%--input old password--%>
                            <div class="col-md-6 offset-3 mb-3">
                                <label for="old-password">Mật khẩu cũ</label>
                                <div class="input-group">
                                    <input type="password" class="form-control" id="old-password" placeholder="Mật khẩu cũ"
                                           name="oldPassword"
                                           aria-describedby="inputGroupPrepend" pattern="^[a-zA-Z0-9]{6,32}$" required>           
                                </div>
                            </div>

                            <%--input password--%>
                            <div class="col-md-6 offset-3 mb-3">
                                <label for="password">Mật khẩu</label>
                                <div class="input-group">
                                    <input type="password" class="form-control" id="password" placeholder="Mật khẩu" name="password"
                                           aria-describedby="inputGroupPrepend" pattern="^[a-zA-Z0-9]{6,32}$" required>
                                    <div class="invalid-feedback">
                                        Mật khẩu chứa số hoặc chữ cái độ dài từ 6 đến 32 kí tự
                                    </div>
                                </div>
                            </div>

                            <%--input re-password--%>
                            <div class="col-md-6 offset-3 mb-3">
                                <label for="re-password">Nhập mật khẩu</label>
                                <div class="input-group">
                                    <input type="password" class="form-control" id="re-password" placeholder="Nhập lại mật khẩu"
                                           name="re-password"
                                           aria-describedby="inputGroupPrepend" pattern="^[a-zA-Z0-9]{6,32}$" required>
                                    <div class="invalid-feedback">
                                        Mật khẩu không trùng nhau
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-3 offset-3">
                                    <button class="btn btn-primary" type="submit">Cập nhật</button>
                                    <p style="color: green">${thongbao2}</p>
                                </div>

                            </div>
                        </div>        
                    </form>
                </div>


            </div>
        </div>
    </div> 

</div>


<script>
    // Lấy đối tượng của 2 trường password
    const passwordField = document.getElementById("password");
    const rePasswordField = document.getElementById("re-password");

    // Thêm sự kiện khi thay đổi giá trị của trường re-password
    rePasswordField.addEventListener("input", function () {
        // Kiểm tra xem giá trị của 2 trường có giống nhau hay không
        if (passwordField.value !== rePasswordField.value) {
            // Nếu không giống nhau, hiển thị thông báo lỗi
            rePasswordField.setCustomValidity("Mật khẩu không khớp");
        } else {
            // Nếu giống nhau, xóa thông báo lỗi đi
            rePasswordField.setCustomValidity("");
        }
    });

    // Thêm sự kiện submit cho form
    document.querySelector("form").addEventListener("submit", function (event) {
        // Kiểm tra xem giá trị của 2 trường có giống nhau hay không
        if (passwordField.value !== rePasswordField.value) {
            // Nếu không giống nhau, ngăn chặn form được submit
            event.preventDefault();
        }
    });

</script>

<script>
    //Ví dụ khởi động JavaScript để tắt tính năng gửi biểu mẫu nếu có trường không hợp lệ
    (function () {
        'use strict';
        window.addEventListener('load', function () {
          // Tìm nạp tất cả các biểu mẫu mà chúng tôi muốn áp dụng kiểu xác thực Bootstrap tùy chỉnh cho
            var forms = document.getElementsByClassName('needs-validation');
            // Lặp lại chúng và ngăn chặn việc gửi
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
