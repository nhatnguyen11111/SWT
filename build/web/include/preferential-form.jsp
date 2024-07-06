
<%@page contentType="text/html" pageEncoding="UTF-8"%>


    <div class="container mt-5">
        
        <!-- Hero Start -->
        <div class="container-fluid py-5 hero-header wow fadeIn" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-lg-7 col-md-12">
                        <h1 class="mb-3 text-primary">We Care Your Baby.</h1>
                        <h1 class="mb-5 display-1 text-white">The Best Play Area For Your Kids</h1>
                        <a href="" class="btn btn-primary px-4 py-3 px-md-5  me-4 btn-border-radius">Get Started</a>
                        <a href="" class="btn btn-primary px-4 py-3 px-md-5 btn-border-radius">Learn More</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Hero End -->
        <div class="container-fluid d-none d-lg-block pt-5 mb-5">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Mã Giảm Giá</h6>
                    <h1 class="display-5 text-uppercase mb-0">Siêu Khuyến Mãi</h1>
                </div>
                <div class="row">
                    <c:forEach var="preferential" items="${preferentialList}">
                        <div style="height: 350px;" class="pb-5 mb-5 mt-5 col-4">
                            <div class="product-item owl-item position-relative bg-light d-flex flex-column text-center">
                                <img class="img-fluid mb-4 w-100" src="img/${preferential.preferentiaImg}" alt="Image">
                                <h6 class="text-uppercase">${preferential.preferentialName}</h6>
                                <h5 class="text-primary mb-0">SỐ LƯỢNG:${preferential.quantity}</h5>
                                <div class="btn-action d-flex justify-content-center">
                                    <a href="product" class="btn btn-primary">Mua Sắm Để Nhận Ưu Đãi Ngay</a>
                                    <!-- <a class="btn btn-primary py-2 px-3" href="getpreferentialdetail?id=${preferential.preferential}"><i class="bi bi-cart"></i></a> -->
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

