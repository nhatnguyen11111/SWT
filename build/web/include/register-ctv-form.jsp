<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-fluid pt-5">
        
    <div class="container">
        <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
            <h1 class="display-5 text-uppercase mb-0">Đăng kí cửa hàng</h1>
        </div>
        <div class="row g-5">
            <div class="col-12">        
                <c:if test="${hasPending}">
                    <h3 class="text-danger">Chúng tôi đã tiếp nhận thông tin của bạn. Chúng tôi sẽ thông báo qua email của bạn trong vòng 7 ngày.</h3>
                </c:if>
                <c:if test="${!hasPending}">
                    <h1>${message}</h1>
                    <form class="needs-validation" novalidate action="addBrand" method="post" enctype="multipart/form-data">
                        <div class="row">
                            <!-- Brand Name -->
                            <div class="col-md-6 offset-3 mb-3">
                                <label for="brandName">Tên cửa hàng<span class="text-danger">*</span></label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="brandName" placeholder="Tên cửa hàng"
                                           name="brandName" aria-describedby="inputGroupPrepend" pattern="^.{1,100}$" required>
                                    <div class="invalid-feedback">
                                        Tên cửa hàng chứa từ 1 đến 100 kí tự
                                    </div>
                                </div>
                            </div>
                            <!-- Brand Description -->
                            <div class="col-md-6 offset-3 mb-3">
                                <label for="brandDescription">Mô tả cửa hàng</label>
                                <div class="input-group">
                                    <textarea class="form-control" id="brandDescription" placeholder="Mô tả cửa hàng"
                                              name="brandDescription" aria-describedby="inputGroupPrepend" maxlength="200"></textarea>
                                    <div class="invalid-feedback">
                                        Mô tả cửa hàng không quá 200 kí tự
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6 offset-3 mb-3">
                              
                                <div class="input-field col s6">
                                    <label for="brandLogo">Logo cửa hàng</label>
                                <div class="input-group">
                                    <input id="avatar" type="file" class="form-control" name="logo" value="" required>
                                </div>
                             </div>
                            </div>
                            <!-- Contact Phone -->
                            <div class="col-md-6 offset-3 ol mb-3">
                                <label for="address">Địa chỉ cửa hàng<span class="text-danger">*</span></label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="address" placeholder="Địa chỉ cửa hàng"
                                           name="brandAddress" aria-describedby="inputGroupPrepend" pattern="^.{1,100}$" required>
                                    <div class="invalid-feedback">
                                        Địa chỉ có độ dài tối đa 100 kí tự
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-3 offset-3">
                                    <button class="btn btn-primary" type="submit">Đăng ký</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:if>
                <h3 class="text-danger">${thongbao}</h3>
            </div>
        </div>
    </div>
</div>

<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
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
