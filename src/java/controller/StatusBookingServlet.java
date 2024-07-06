package controller;

import repository1.ServiceRespository;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StatusBookingServlet", urlPatterns = {"/StatusBookingServlet"})
public class StatusBookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String bookingIDStr = request.getParameter("bookingID");
        String bookingStatusStr = request.getParameter("bookingStatus");

        // Lấy các giá trị khác từ form
        String name = request.getParameter("name");
        String serviceName = request.getParameter("serviceName");
        String bookingDate = request.getParameter("bookingDate");
        String slot = request.getParameter("slot");
        String priceStr = request.getParameter("price"); // Sửa 'servicePrice' thành 'price'
        String note = request.getParameter("note");
        String email = request.getParameter("email");

        if (bookingIDStr != null && bookingStatusStr != null) {
            int bookingID = Integer.parseInt(bookingIDStr);
            int bookingStatus = Integer.parseInt(bookingStatusStr);
            double price = Double.parseDouble(priceStr); // Chuyển đổi giá trị từ String sang double

            ServiceRespository serviceRepository = new ServiceRespository();
            serviceRepository.updateBookingStatus(bookingID, bookingStatus);

            // Gửi email xác nhận nếu trạng thái đặt chỗ là 2
            if (bookingStatus == 2) {
                String subject = "Xác nhận đặt dịch vụ";
                String body = "Kính gửi " + name + ",\n\n"
                        + "Cảm ơn bạn đã đặt dịch vụ của chúng tôi. Dưới đây là chi tiết:\n\n"
                        + "Tên dịch vụ: " + serviceName + "\n"
                        + "Ngày đặt lịch: " + bookingDate + "\n"
                        + "Khung giờ: " + slot + "\n"
                        + "Giá: " + price + "\n"
                        + "Ghi chú: " + note + "\n\n"
                        + "Trân trọng,\n"
                        + "BabyCare";

                ServiceRespository.sendEmail(email, subject, body);
            }

            response.sendRedirect("ListBookingEmploye");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid booking ID or status.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for updating booking status";
    }
}
