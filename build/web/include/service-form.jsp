<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 19/06/2023
  Time: 4:37 CH
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Services Start -->
 <div class="header-carousel-item">
     <img src="images/h/h3.jpg" class="img-fluid w-100" style="height: 600px" alt="Image">
                    <div class="carousel-caption">
                        <div class="carousel-caption-content p-3">
                            <h5 class="text-white text-uppercase fw-bold mb-4" style="letter-spacing: 3px;">Trung Tâm Dịch Vụ</h5>
                            <h1 class="display-1 text-capitalize text-white mb-4">Giải Pháp Tốt Nhất Cho Bạn </h1>
                            <p class="mb-5 fs-5">Chúng tôi luôn sẵn sàng mang lại những dịch vụ tốt nhất cho bạn. 
                            </p>
                          
                        </div>
                    </div>
                </div>
<div class="container-fluid py-5">
    <div class="container">
        <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
            <h6 class="text-primary text-uppercase">DỊCH VỤ</h6>
            <h1 class="display-5 text-uppercase mb-0">ĐẶT DỊCH VỤ TRỰC TUYẾN CHO BÉ CỦA BẠN</h1>
        </div>
        <div class="row g-5">
             <c:forEach items="${ListS}" var="S">
            <div class="col-md-6">
               
                <div class="row">
                    <div class="col-4">
                        <img src="${S.listImg}" style="width: 200px; height: 180px" >
                    </div>
                    <div class="col-8">
                         <div class="service-item bg-light d-flex p-4">
                       
                        <div>
                            <h5 class="text-uppercase mb-3">${S.serviceName}</h5>
                            <p>${S.description}</p>
                            <a class="text-primary text-uppercase" href="getservicedetail?serviceID=${S.serviceID}">Xem thêm<i class="bi bi-chevron-right"></i></a>
                        </div>
                    </div>
                    </div>
                </div>
                
            </div>
                        </c:forEach>
        </div>
    </div>
</div>

<!-- Services End -->


<div class="container-fluid about bg-light py-5">
                <div class="container py-5">
                    <div class="row g-5 align-items-center">
                        <div class="col-lg-5 wow fadeInLeft" data-wow-delay="0.2s">
                            <div class="about-img pb-5 ps-5">
                                <img src="images/y/y5.jpg" class="img-fluid rounded w-100" style="object-fit: cover;" alt="Image">
                                
                               
                            </div>
                        </div>
                        <div class="col-lg-7 wow fadeInRight" data-wow-delay="0.4s">
                            <div class="section-title text-start mb-5">
                                <h4 class="sub-title pe-3 mb-0">About Us</h4>
                                <h1 class="display-3 mb-4">Chúng tôi luôn sẵn sàng mang lại những dịch vụ tốt nhất cho bạn.</h1>
                                <p class="mb-4">Babycare là trang thông tin dành cho Bố và Bé, chuyên cung cấp các dịch vụ tiện ích cho em bé. Nhằm giúp cho độc giả có những quyết định tốt trong việc chăm sóc sức khỏe, xây dựng đời sống gia đình và nuôi dạy trẻ tốt hơn</p>
                                <div class="mb-4">
                                    <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Dịch vụ chuyên nghiệp và nhanh chóng</p>
                                    <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Cung cấp đồ trang trí, nội thất và phụ kiện theo yêu cầu</p>
                                    <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Chi Phí hợp lý </p>
                                </div>
                                <a href="#" class="btn btn-primary rounded-pill text-white py-3 px-5">Discover More</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>