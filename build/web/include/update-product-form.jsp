
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<style>
    body {
        background-color: #f8f9fa;
    }

    .nav-pills .nav-link {
        border-radius: 0;
    }

    .container {

        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .form-label {
        font-weight: bold;
    }

    .current-image img {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 5px;
    }

    .btn-primary {
        width: 100%;
    }

    .invalid-feedback {
        font-size: 0.875em;
    }

</style>
<nav class="nav nav-pills nav-justified mb-3">
    <a class="nav-item nav-link active" href="#">Thông tin chung</a>
    <a class="nav-item nav-link" href="product-list-manager">Danh sách sản phẩm</a>
</nav>

<div class="container">
    <div class="row justify-content-center">
        <form class="needs-validation" novalidate action="updateproduct" method="post" enctype="multipart/form-data">
            <input type="hidden" id="productID" name="productID" value="${product.productId}" required>

            <div class="mb-3">
                <label for="productName" class="form-label">Tên sản phẩm</label>
                <input pattern="^.{1,200}$" type="text" class="form-control" id="productName" name="productName"
                       placeholder="Tên sản phẩm" value="${product.productName}" required>
                <div class="invalid-feedback">Tối đa 1-200 ký tự</div>
            </div>

            <div class="mb-3">
                <label for="productPrice">Giá sản phẩm</label>
                <div class="input-group">
                    <input type="text" pattern="^(?:\d+|\d*\.\d+)$" class="form-control" id="productPrice" placeholder="Giá sản phẩm" name="productPrice"
                           aria-describedby="inputGroupPrepend"  value="${product.productPrice}" required>
                    <div class="invalid-feedback">
                        Giá tiền chỉ bao gồm chữ số
                    </div>
                </div>
            </div>
    </div>


    <div class="mb-3">
        <label for="productType" class="form-label">Chọn loại sản phẩm</label>
        <select class="form-select" id="productType" name="productType">
            <option value="Quần áo trẻ em">Quần áo trẻ em</option>
            <option value="Đồ chơi trẻ em">Đồ chơi trẻ em</option>
            <option value="Tã em bé" ${product.productType.equals("Tã em bé") ? "selected" : ""}>Tã em bé</option>
            <option value="Sữa" ${product.productType.equals("Sữa") ? "selected" : ""}>Sữa</option>
            <option value="Xe đẩy trẻ em" ${product.productType.equals("Xe đẩy trẻ em") ? "selected" : ""}>Xe đẩy trẻ em</option>
            <option value="Nội thất trẻ em" ${product.productType.equals("Nội thất trẻ em") ? "selected" : ""}>Nội thất trẻ em</option>
            <option value="Đồ dùng tắm cho bé" ${product.productType.equals("Đồ dùng tắm cho bé") ? "selected" : ""}>Đồ dùng tắm cho bé</option>
            <option value="Sách trẻ em" ${product.productType.equals("Sách trẻ em") ? "selected" : ""}>Sách trẻ em</option>
            <option value="Đồ dùng ăn uống cho bé" ${product.productType.equals("Đồ dùng ăn uống cho bé") ? "selected" : ""}>Đồ dùng ăn uống cho bé</option>
            <option value="Chăm sóc sức khỏe cho bé" ${product.productType.equals("Chăm sóc sức khỏe cho bé") ? "selected" : ""}>Chăm sóc sức khỏe cho bé</option>
        </select>
    </div>

    <div class="mb-3">
        <label for="productAmount" class="form-label">Số lượng sản phẩm</label>
        <input type="text" pattern="^.{1,30}$" class="form-control" id="productAmount" name="productAmount"
               placeholder="Số lượng sản phẩm" value="${product.productAmount}" required>
        <div class="invalid-feedback">Trường này là bắt buộc, độ dài tối đa 30 ký tự</div>
    </div>

    <div class="mb-3">
        <label for="productOrigin" class="form-label">Xuất xứ</label>
        <input type="text" pattern="^.{1,30}$" class="form-control" id="productOrigin" name="productOrigin"
               placeholder="Xuất xứ" value="${product.origin}" required>
        <div class="invalid-feedback">Độ dài tối đa 30 ký tự</div>
    </div>

    <div class="mb-3">
        <label for="productDescription" class="form-label">Mô tả sản phẩm</label>
        <input type="text" class="form-control" id="productDescription" name="productDescription"
               placeholder="Mô tả sản phẩm" value="${product.productDescription}">
    </div>

    <div class="mb-3">
        <label for="status" class="form-label">Trạng thái</label>
        <select class="form-select" id="status" name="status">
            <option value="1" ${product.status == 1 ? "selected" : ""}>Đang bán</option>
            <option value="0" ${product.status == 0 ? "selected" : ""}>Ngừng bán</option>
        </select>
    </div>

    <div class="mb-3">
        <label for="productImage" class="form-label">Ảnh sản phẩm hiện tại</label>
        <div class="input-group" style="flex-direction: column;">
            <div class="current-image mb-2">
                <img src="img/${product.img}" alt="Ảnh hiện tại" style="max-width: 200px; max-height: 200px;">
            </div>
            <label for="productImg">Đường dẫn ảnh</label>
            <div class="input-group">
                <input id="avatar" type="file" class="form-control" name="productImg" value="" >
            </div>
        </div>
    </div>




    <div class="mb-3 text-center">
        <button class="btn btn-primary" type="submit">Cập nhật</button>
        <div class="mt-2 success">${thongbao}</div>
    </div>
</form>
</div>
</div>

<script>
    // Bootstrap form validation
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
