<%-- 
    Document   : managebookingEmploye-form
    Created on : May 30, 2024, 9:52:08 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav class="nav nav-pills nav-justified">

    <a class="nav-item nav-link" href="ListBookingEmploye">Danh Sách khách hàng đặt lịch</a>
    <a class="nav-item nav-link  " href="ListBillService">Danh sách đã xác nhận </a>
    <a class="nav-item nav-link " href="ListCustomerPayment">Danh sách  Thanh Toán</a>
    <a class="nav-item nav-link active" href="ListConfirmedServices">Danh Sách lịch hoàn thành</a>
    <a class="nav-item nav-link  " href="ListServiceCancellation">Danh sách khách hàng hủy dịch vụ</a>
    
</nav>

<div class="container">
    <h2 class="my-4"style=" font-size: 24px; /* Adjust the font size */
        color: #333; /* Set the text color */
        margin-top: 20px; /* Add some top margin for spacing */
        margin-bottom: 20px; /* Add some bottom margin for spacing */
        text-align: center; /* Center-align the text */
        text-transform: uppercase; /* Convert text to uppercase */
        letter-spacing: 2px; /* Add some letter spacing */
        font-weight: bold; /* Make the text bold */">Danh Lịch Cần Hoàn Thành</h2>


    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>

                <th>Customer ID</th>

                <th>Tên</th>
                <th>SĐT</th>
                <th>Địa Chỉ</th>
                <th>Sex</th>
                <th>Ngày Đặt Lịch</th>
                <th>Slot</th>
                <th>Trạng Thái</th>
                <th>Ghi Chú</th>
                <th>Gía</th>
                <th>Email</th>
                <th>Service Name</th>

                <th>Actions</th>

            </tr>
        </thead>
        <tbody>
            <c:forEach var="booking" items="${listE}">
                <tr>

                    <td>${booking.customerID}</td>

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
                    <td>${booking.price}.VNĐ</td> <!-- Sửa 'servicePrice' thành 'price' -->
                    <td>${booking.email}</td>
                    <td>${booking.serviceName}</td>
                    
                    <td>
                        
                        <c:if test="${booking.bookingStatus == 2}">
                            <form action="StatusBookingServlet2" method="post" onsubmit="return confirm('Bạn chắc chắn muốn hoàn thành lịch của khách hàng này ?');">
                                <input type="hidden" name="bookingID" value="${booking.bookingID}">
                                <input type="hidden" name="bookingStatus" value="3">
                                <button type="submit" class="btn btn-success">Hoàn thành</button>
                            </form>
                        </c:if>
                    </td>



                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>