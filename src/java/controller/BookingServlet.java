package controller;

import entity.Booking;
import entity.User;
import repository1.ServiceRespository;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BookingServlet", urlPatterns = {"/bookingservlet"})
public class BookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String serviceID = request.getParameter("serviceID");
        String servicePrice = request.getParameter("servicePrice");
        String serviceName = request.getParameter("serviceName");
        request.setAttribute("serviceID", serviceID);
        request.setAttribute("servicePrice", servicePrice);
        request.setAttribute("serviceName", serviceName);
        request.getRequestDispatcher("booking-service.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User oldUser = (User) session.getAttribute("user");

        if (oldUser == null) {
            request.setAttribute("errorMessage", "User is not logged in.");
            request.getRequestDispatcher("booking-service.jsp").forward(request, response);
            return;
        }

        String customerID = oldUser.getUserId();
        String serviceID = request.getParameter("serviceID");
        String servicePriceStr = request.getParameter("servicePrice");
        String serviceName = request.getParameter("serviceName");

        if (serviceID == null || serviceID.isEmpty()) {
            request.setAttribute("errorMessage", "Missing service ID. Please try again.");
            request.setAttribute("servicePrice", servicePriceStr);
            request.setAttribute("serviceName", serviceName);
            request.getRequestDispatcher("booking-service.jsp").forward(request, response);
            return;
        }

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String slot = request.getParameter("slot");
        String bookingDate = request.getParameter("bookingDate");
        String note = request.getParameter("note");
        String email = request.getParameter("email");

        LocalDate bookingDateParsed;
        try {
            bookingDateParsed = LocalDate.parse(bookingDate);
        } catch (DateTimeParseException e) {
            request.setAttribute("serviceID", serviceID);
            request.setAttribute("servicePrice", servicePriceStr);
            request.setAttribute("serviceName", serviceName);
            request.setAttribute("errorMessage", "Invalid date format. Please use yyyy-MM-dd.");
            request.getRequestDispatcher("booking-service.jsp").forward(request, response);
            return;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate minBookingDate = currentDate.plusDays(2); // Minimum booking date is 2 days from today

        if (!bookingDateParsed.isAfter(minBookingDate)) {
            request.setAttribute("serviceID", serviceID);
            request.setAttribute("servicePrice", servicePriceStr);
            request.setAttribute("serviceName", serviceName);
            request.setAttribute("errorMessage", "Ngày đặt phòng phải cách ngày đặt dịch vụ ít nhất hai ngày.");
            request.getRequestDispatcher("booking-service.jsp").forward(request, response);
            return;
        }

        double servicePrice;
        try {
            servicePrice = Double.parseDouble(servicePriceStr);
        } catch (NumberFormatException e) {
            request.setAttribute("serviceID", serviceID);
            request.setAttribute("servicePrice", servicePriceStr);
            request.setAttribute("serviceName", serviceName);
            request.setAttribute("errorMessage", "Invalid service price. Please enter a valid number.");
            request.getRequestDispatcher("booking-service.jsp").forward(request, response);
            return;
        }

        Booking booking = new Booking();
        booking.setCustomerID(customerID);
        booking.setServiceID(serviceID);
        booking.setName(name);
        booking.setAddress(address);
        booking.setPhoneNumber(phone);
        booking.setSex(sex);
        booking.setSlot(slot);
        booking.setBookingDate(bookingDate);
        booking.setBookingStatus(1); // Assuming status is 1 for new bookings
        booking.setNote(note);
        booking.setPrice(servicePrice);
        booking.setEmail(email);
        booking.setServiceName(serviceName);

        ServiceRespository serviceRepository = new ServiceRespository();
        int bookingID = serviceRepository.insertBooking(booking); // Adjust to return booking ID

       
        session.setAttribute("name", name);
        session.setAttribute("bookingID", bookingID);
        session.setAttribute("address", address);
        session.setAttribute("phone", phone);
        session.setAttribute("sex", sex);
        session.setAttribute("customerID", customerID);
        session.setAttribute("slot", slot);
        session.setAttribute("bookingDate", bookingDate);
        session.setAttribute("note", note);
        session.setAttribute("servicePrice", servicePrice);
        session.setAttribute("email", email);
        session.setAttribute("serviceName", serviceName);
        response.sendRedirect("AddBillServiceServlet");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
