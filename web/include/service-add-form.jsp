
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${sessionScope.user==null}">
        <% response.sendRedirect("login.jsp");%>
        </c:if>
<nav class="nav nav-pills nav-justified">
    <a class="nav-item nav-link active" href="service-add.jsp">Thêm dịch vụ mới</a>
    <a class="nav-item nav-link" href="ServiceList">Danh sách dịch vụ</a>
</nav>

<div class="container">
    <div class="row">
        <form class="needs-validation mt-3" novalidate action="addservice" method="post">
            <div class="row">
                <div class="col-12" style="background-color:#7ab730;border-radius: 10px;">
                    <h2>Thêm dịch vụ mới</h2>
                </div>
                
                <%-- Input Service Name --%>
                <div class="col-md-6 offset-3 mb-3 mt-3">
                    <label for="serviceName">Tên dịch vụ</label>
                    <div class="input-group">
                        <input pattern="^.{1,50}$" type="text" class="form-control" id="serviceName" placeholder="Tên dịch vụ" name="serviceName" aria-describedby="inputGroupPrepend" required>
                        <div class="invalid-feedback">
                            Tối đa từ 1-50 ký tự
                        </div>
                    </div>
                </div>
                <%-- Input Service Price --%>
                <div class="col-md-6 offset-3 mb-3">
                    <label for="servicePrice">Giá dịch vụ</label>
                    <div class="input-group">
                        <input type="text" pattern="^(?:\\d+|\\d*\\.\\d+)$" class="form-control" id="servicePrice" placeholder="Giá dịch vụ" name="servicePrice" aria-describedby="inputGroupPrepend" required>
                        <div class="invalid-feedback">
                            Giá tiền chỉ bao gồm chữ số
                        </div>
                    </div>
                </div>
                <%-- Input Service Image URL --%>
                <div class="col-md-6 offset-3 mb-3">
                    <label for="serviceUrlImg">Đường dẫn ảnh</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="serviceUrlImg" placeholder="URL" name="serviceUrlImg" aria-describedby="inputGroupPrepend" required>
                        <div class="invalid-feedback">
                            Vui lòng điền vào
                        </div>
                    </div>
                </div>
                <%-- Input Service Description --%>
                <div class="col-md-6 offset-3 mb-3 mt-3">
                    <label for="Description">Mô tả</label>
                    <div class="input-group">
                        <input pattern="^.{1,100}$" type="text" class="form-control" id="Description" placeholder="Mô tả" name="Description" aria-describedby="inputGroupPrepend" required>
                        <div class="invalid-feedback">
                            Tối đa từ 1-100 ký tự
                        </div>
                    </div>
                </div>
                <%-- Submit Button and Message --%>
                <div class="row">
                    <div class="col-3 offset-3">
                        <button class="btn btn-primary" type="submit">Thêm</button>
                    </div>
                    <div class="col-6">${thongbao}</div>
                </div>
            </div>
        </form>
    </div>
</div>
