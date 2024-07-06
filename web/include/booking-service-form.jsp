<%-- 
    Document   : booking-service-form
    Created on : May 26, 2024, 6:42:01 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Book Appointment Start -->
<div class="container-fluid appointment py-5">
    <div class="container py-5">
        <div class="row g-5 align-items-center">
            <div class="col-lg-6 wow fadeInLeft" data-wow-delay="0.2">
                <div class="section-title text-start">
                    <h4 class="sub-title pe-3 mb-0">Giải pháp cho bạn</h4>
                    <h1 class="display-4 mb-4">Chất lượng tốt nhất và dịch vụ chuyên nghiệp</h1>
                    <p class="mb-4">Babycare là trang thông tin dành cho Bố và Bé, chuyên cung cấp các dịch vụ tiện ích cho em bé. Nhằm giúp cho độc giả có những quyết định tốt trong việc chăm sóc sức khỏe, xây dựng đời sống gia đình và nuôi dạy trẻ tốt hơn</p>
                    <div class="row g-4">
                        <div class="col-sm-6">
                            <div class="d-flex flex-column h-100">
                                <div class="mb-4">
                                    <h5 class="mb-3"><i class="fa fa-check text-primary me-2"></i>Chuyên nghiệp và sáng tạo</h5>
                                    <p class="mb-0">Dịch vụ trang trí sinh nhật của chúng tôi tận tâm mang đến cho bạn những bữa tiệc sinh nhật tuyệt vời nhất với những cách trang trí độc đáo, sáng tạo.</p>
                                </div>
                                <div class="mb-4">
                                    <h5 class="mb-3"><i class="fa fa-check text-primary me-2"></i>Đa dạng và linh hoạt</h5>
                                    <p class="mb-0">Chúng tôi cung cấp nhiều gói trang trí sinh nhật với giá cả phải chăng, từ đơn giản đến cao cấp, phục vụ mọi nhu cầu và túi tiền. </p>
                                </div>
                               
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="video h-100">
                                <img src="images/y/y4.jpg" class="img-fluid rounded w-100 h-100" style="object-fit: cover;" alt="">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 wow fadeInRight" data-wow-delay="0.4s">
                <div class="appointment-form rounded p-5">
                    <p class="fs-4 text-uppercase text-primary">Liên lạc</p>
                    <h1 class="display-5 mb-4">Đặt Lịch</h1>

                    <% if (request.getAttribute("errorMessage") != null) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("errorMessage")%>
                    </div>
                    <% }%>
                    <form action="bookingservlet" method="post" onsubmit="return validateDate();">
                        <div class="row gy-3 gx-4">
                            <div class="col-md-6 offset-3 mb-3">
                                <div class="input-group">
                                    <input type="hidden" class="form-control" id="serviceID" name="serviceID" value="${serviceID}" aria-describedby="inputGroupPrepend" required>
                                    <input type="hidden" class="form-control" id="servicePrice" name="servicePrice" value="${servicePrice}" aria-describedby="inputGroupPrepend" required>
                                    <input type="hidden" class="form-control" id="serviceName" name="serviceName" value="${serviceName}" aria-describedby="inputGroupPrepend" required>
                                    <div class="invalid-feedback"></div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <input type="text" class="form-control py-3 border-primary bg-transparent text-dark" placeholder="Tên" name="name" required>
                            </div>
                            <div class="col-xl-6">
                                <input type="text" class="form-control py-3 border-primary bg-transparent text-dark" placeholder="Địa chỉ" name="address" required>
                            </div>
                            <div class="col-xl-6">
                                <input type="text" class="form-control py-3 border-primary bg-transparent text-dark" placeholder="Điện Thoại" name="phone" pattern="\d{10}" maxlength="10" required>
                                <div class="invalid-feedback">Please enter a valid 10-digit phone number.</div>
                            </div>
                            <div class="col-xl-6">
                                <select class="form-select py-3 border-primary bg-transparent text-dark" name="slot" required>
                                    <option selected disabled>Slot</option>
                                    <option value="Slot 1 (7:00 - 9:00)">Slot 1 (7:00 - 9:00)</option>
                                    <option value="Slot 2 (9:00 - 11:00)">Slot 2 (9:00 - 11:00)</option>
                                    <option value="Slot 3 (13:00 - 15:00)">Slot 3 (13:00 - 15:00)</option>
                                    <option value="Slot 4 (15:00 - 18:00)">Slot 4 (15:00 - 18:00)</option>
                                </select>
                            </div>
                            <div class="col-xl-6">
                                <input type="date" class="form-control py-3 border-primary bg-transparent text-dark" id="bookingDate" name="bookingDate" required>
                            </div>
                            <div class="col-xl-6">
                                <select class="form-select py-3 border-primary bg-transparent text-dark" name="sex" required>
                                    <option selected disabled>Giới tính em bé</option>
                                    <option value="Nam">Nam</option>
                                    <option value="Nữ">Nữ</option>
                                </select>
                            </div>
                            <div class="col-xl-6">
                                <input type="email" class="form-control py-3 border-primary bg-transparent text-dark" placeholder="Email" name="email" required>
                            </div>
                            <div class="col-12">
                                <textarea class="form-control py-3 border-primary bg-transparent text-dark" name="note" placeholder="Ghi chú" rows="4"></textarea>
                            </div>
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary text-white w-100 py-3 px-5">Đặt Ngay</button>
                            </div>
                        </div>
                    </form>




                </div>
            </div>
        </div>
    </div>
</div>


