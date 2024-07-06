<%-- 
    Document   : updateBooking-form
    Created on : May 29, 2024, 4:36:44 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
</div<title></title>
  <script>
    function validateDate() {
        var bookingDate = document.getElementById("bookingDate").value;
        var currentDate = new Date();
        currentDate.setDate(currentDate.getDate() + 2); // Add 2 days to the current date
        var minBookingDate = currentDate.toISOString().slice(0, 10); // Convert to YYYY-MM-DD format
        
        if (bookingDate < minBookingDate) {
            alert("Ngày đặt phòng phải cách ngày hôm sau ít nhất hai ngày.");
            return false;
        }
        return true;
    }
</script>
</head>
<body>
<div class="container mt-5">
    <h2>Chỉnh sửa lịch dịch vụ</h2>
    <form action="UpdateBookingServlet" method="post" onsubmit="return validateDate();">
        <input type="hidden" name="bookingID" value="${booking.bookingID}">
        <div class="form-group">
            <label for="name">Tên</label>
            <input type="text" class="form-control" name="name" value="${booking.name}" required>
        </div>
        <div class="form-group">
            <label for="address">Địa chỉ</label>
            <input type="text" class="form-control" name="address" value="${booking.address}" required>
        </div>
        <div class="form-group">
            <label for="sex">Gioi tính</label>
            <select class="form-control" name="sex" required>
                <option value="Nam" ${booking.sex == 'Nam' ? 'selected' : ''}>Nam</option>
                <option value="Nữ" ${booking.sex == 'Nữ' ? 'selected' : ''}>Nữ</option>
            </select>
        </div>
        <div class="form-group">
            <label for="bookingDate">Ngày</label>
            <input type="date" class="form-control" id="bookingDate" name="bookingDate" value="${booking.bookingDate}" required>
        </div>
        <div class="form-group">
            <label for="slot">Slot</label>
            <select class="form-control" name="slot" required>
                <option value="Slot 1 (7:00 - 9:00)" ${booking.slot == 'Slot 1 (7:00 - 9:00)' ? 'selected' : ''}>Slot 1 (7:00 - 9:00)</option>
                <option value="Slot 2 (9:00 - 11:00)" ${booking.slot == 'Slot 2 (9:00 - 11:00)' ? 'selected' : ''}>Slot 2 (9:00 - 11:00)</option>
                <option value="Slot 3 (13:00 - 15:00)" ${booking.slot == 'Slot 3 (13:00 - 15:00)' ? 'selected' : ''}>Slot 3 (13:00 - 15:00)</option>
                <option value="Slot 4 (15:00 - 18:00)" ${booking.slot == 'Slot 4 (15:00 - 18:00)' ? 'selected' : ''}>Slot 4 (15:00 - 18:00)</option>
            </select>
        </div>
        <div class="form-group">
            <label for="note">Ghi chú</label>
            <textarea class="form-control" name="note" required>${booking.note}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>