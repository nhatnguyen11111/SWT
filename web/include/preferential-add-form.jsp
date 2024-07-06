<%@page contentType="text/html" pageEncoding="UTF-8" %>

<nav class="nav nav-pills nav-justified">
    <a class="nav-item nav-link" href="preferential-list-manager">Danh sách Mã Giảm Giá</a>
    <a class="nav-item nav-link active" href="#">Thêm Mã Giảm Giá mới</a>
</nav>

<div class="container">
    <h2>Thêm Mã Giảm Giá Mới.</h2>
    <form class="needs-validation" novalidate action="addpreferential" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="preferentialCode">Mã Giảm Giá:</label>
            <input type="text" class="form-control" id="preferentialCode" name="preferentialCode" required>
            <div class="invalid-feedback">Vui lòng nhập mã giảm giá.</div>
        </div>
        <div class="form-group">
            <label for="preferentialName">Tên Mã Giảm Giá:</label>
            <input type="text" class="form-control" id="preferentialName" name="preferentialName" required>
            <div class="invalid-feedback">Vui lòng nhập tên mã giảm giá.</div>
        </div>
        <div class="form-group">
            <label for="startDay">Ngày Bắt đầu:</label>
            <input type="date" class="form-control" id="startDay" name="startDay" required>
            <div class="invalid-feedback">Vui lòng chọn ngày bắt đầu.</div>
        </div>
        <div class="form-group">
            <label for="endDay">Ngày Kết Thúc:</label>
            <input type="date" class="form-control" id="endDay" name="endDay" required>
            <div class="invalid-feedback">Vui lòng chọn ngày kết thúc..</div>
        </div>
        <div class="form-group">
            <label for="quantity">Số Lượng:</label>
            <input type="number" class="form-control" id="quantity" name="quantity" required>
            <div class="invalid-feedback">Vui lòng nhập số lượng.</div>
        </div>
        <div class="form-group">
           <label for="rate">Tỷ lệ giảm (%)</label>
            <input type="number" class="form-control" id="quantity" name="rate" required>
            <div class="invalid-feedback">Vui lòng nhập số lượng.</div>
        </div>
       
        <div class="form-group">
            <label for="description">Mô Tả:</label>
            <textarea class="form-control" id="description" name="preferentialDescription"></textarea>
        </div>
                  <div class="form-group">
            <label for="preferentialImg">Ảnh:</label>
            <input type="file" class="form-control" id="preferentialImg" name="preferentialImg" required>
            <div class="invalid-feedback">Vui lòng chọn ảnh.</div>
        </div>
        <button type="submit" class="btn btn-primary">Thêm</button>
    </form>
</div>

<script>
    // JavaScript for disabling form submissions if there are invalid fields
    (function() {
      'use strict';
      window.addEventListener('load', function() {
        var forms = document.getElementsByClassName('needs-validation');
        Array.prototype.filter.call(forms, function(form) {
          form.addEventListener('submit', function(event) {
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
