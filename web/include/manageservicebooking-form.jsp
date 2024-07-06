<%-- 
    Document   : manageservicebooking-form
    Created on : May 29, 2024, 1:37:38 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <h2 class="my-4" style=" font-size: 24px; /* Adjust the font size */
    color: #333; /* Set the text color */
    margin-top: 20px; /* Add some top margin for spacing */
    margin-bottom: 20px; /* Add some bottom margin for spacing */
    text-align: center; /* Center-align the text */
    text-transform: uppercase; /* Convert text to uppercase */
    letter-spacing: 2px; /* Add some letter spacing */
    font-weight: bold; /* Make the text bold */">Lịch sử dịch vụ</h2>
    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover">
            <thead class="thead-dark">
                <tr>
                   
                    <th scope="col">Customer ID</th>
                    <th scope="col">Service ID</th>
                    <th scope="col">Tên</th>
                    <th scope="col">SĐT</th>
                    <th scope="col">Địa Chỉ</th>
                    <th scope="col">Sex</th>
                    <th scope="col">Ngày Đặt Lịch</th>
                    <th scope="col">Slot</th>
                    <th scope="col">Trạng Thái</th>
                    <th scope="col">Ghi Chú</th>
                    <th scope="col">Giá</th>
                    <th scope="col">Thanh Toán</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${listB}">
                    
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
                                <c:when test="${booking.bookingStatus == 0}">Lịch đã bị hủy</c:when>
                                <c:when test="${booking.bookingStatus == 1}">Đang xử lý ...</c:when>
                                <c:when test="${booking.bookingStatus == 2}">Đã Đặt</c:when>
                                <c:when test="${booking.bookingStatus == 3}">Đã Hoàn Thành.</c:when>
                                <c:otherwise>Other Status</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${booking.note}</td>
                        <td>${booking.price}.VNĐ</td>
                         <td>
<c:choose>
                              
                                 <c:when test="${booking.billStatus == 1}">Đang xủ lý...</c:when>
                                <c:when test="${booking.billStatus == 2}">Chưa Thanh Toán</c:when>
                                <c:when test="${booking.billStatus == 3}">Đã Thanh Toán</c:when>    
                                
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${booking.bookingStatus == 1}">
                                <form action="DeleteBookingServlet" method="post" onsubmit="return confirm('Bạn chắc chắn muốn xóa dịch vụ này?');">
                                    <input type="hidden" name="bookingID" value="${booking.bookingID}">
                                    <input type="hidden" name="billID" value="${booking.billID}">
                                    <button type="submit" class="btn btn-danger btn-sm">Hủy</button>
                                </form>
                            </c:if>
                            <c:if test="${booking.bookingStatus == 0}">
                                <form action="DeleteBookingServlet" method="post">
                                    <input type="hidden" name="bookingID" value="${booking.bookingID}">
                                    <input type="hidden" name="billID" value="${booking.billID}">
                                    <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                                </form>
                            </c:if>
                              <c:if test="${booking.bookingStatus == 3}">
                                <form action="FeedBackServlet" method="get">
                                    
                                    <input type="hidden" name="CustomerID" value="${booking.customerID}">
                                    <input type="hidden" name="ServiceID" value="${booking.serviceID}">
                                    <button type="submit" class="btn btn-danger btn-sm">Feedback</button>
                                </form>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${booking.bookingStatus == 1}">
                                <form action="UpdateBookingServlet" method="get" onsubmit="return confirm('Bạn chắc chắn muốn chỉnh sửa ?');">
                                    <input type="hidden" name="bookingID" value="${booking.bookingID}">
                                    <button type="submit" class="btn btn-success btn-sm">Chỉnh sửa</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                     </c:forEach>
              
            </tbody>
        </table>
    </div>
</div>