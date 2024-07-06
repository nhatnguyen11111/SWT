<%-- 
    Document   : list-service-cancellation-form
    Created on : Jun 6, 2024, 11:25:29 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav class="nav nav-pills nav-justified">
    
    <a class="nav-item nav-link " href="ListBookingEmploye">Danh Sách khách hàng đặt lịch</a>
    <a class="nav-item nav-link  " href="ListBillService">Danh sách đã xác nhận </a>
    <a class="nav-item nav-link " href="ListCustomerPayment">Danh sách  Thanh Toán</a>
     <a class="nav-item nav-link " href="ListConfirmedServices">Danh sách  Hoàn thành </a>
    <a class="nav-item nav-link  active" href="ListServiceCancellation">Danh sách khách hàng hủy dịch vụ</a>
</nav>

<div class="container">
    <h2 class="my-4"style=" font-size: 24px; /* Adjust the font size */
    color: #333; /* Set the text color */
    margin-top: 20px; /* Add some top margin for spacing */
    margin-bottom: 20px; /* Add some bottom margin for spacing */
    text-align: center; /* Center-align the text */
    text-transform: uppercase; /* Convert text to uppercase */
    letter-spacing: 2px; /* Add some letter spacing */
    font-weight: bold; /* Make the text bold */">Danh Sách khách hàng đặt lịch</h2>
    
    
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>

                <th>Customer ID</th>
                <th>Service ID</th>
                <th>Tên</th>
                <th>SĐT</th>
                <th>Địa Chỉ</th>
                <th>Sex</th>
                <th>Ngày Đặt Lịch</th>
                <th>Slot</th>
                <th>Trạng Thái</th>
                <th>Ghi Chú</th>
                <th>Gía</th>
              
               
                
            </tr>
        </thead>
        <tbody>
            <c:forEach var="booking" items="${listE}">
                <tr>

                    <td>${booking.customerID}</td>
                    <td>${booking.serviceID}</td>
                    <td>${booking.name}</td>
                    <td>${booking.phoneNumber}</td>
                    <td>${booking.address}</td>
                    <td>${booking.sex}</td>
                    <td>${booking.bookingDate}</td>
                    <td>${booking.slot}</td>
                    <td>
                        <c:choose>
                            <c:when test="${booking.bookingStatus == 0}">
                                Lịch đã bị hủy
                            </c:when>
                            <c:when test="${booking.bookingStatus == 1}">
                                Đang xử lý ...
                            </c:when>
                            <c:when test="${booking.bookingStatus == 2}">
                                Đã Đặt
                            </c:when>
                            <c:when test="${booking.bookingStatus == 3}">
                                Đã Hoàn Thành.
                            </c:when>
                            <c:otherwise>
                                Other Status
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${booking.note}</td>
                    <td>${booking.price}.VNĐ</td>
                   
                    

                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>