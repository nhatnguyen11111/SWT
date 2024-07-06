package controller;

import entity.ServiceBill;
import repository1.ServiceRespository;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="AddBillService", urlPatterns={"/AddBillServiceServlet"})
public class AddBillServiceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        Integer bookingID = (Integer) session.getAttribute("bookingID");
        String customerID = (String) session.getAttribute("customerID");
        Double servicePrice = (Double) session.getAttribute("servicePrice");

        if (bookingID == null || customerID == null || servicePrice == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing session attributes");
            return;
        }

        ServiceBill bill = new ServiceBill();
        bill.setBookingID(bookingID);
        bill.setCustomerID(customerID);
        bill.setBillDate(new java.util.Date());
        bill.setTotalAmount(servicePrice);
        bill.setBillStatus(1); // Assuming status is 1 for new bills

        ServiceRespository serviceRepository = new ServiceRespository();
        int billID = serviceRepository.addBill(bill);

        session.setAttribute("billID", billID);
        // Redirect to confirmation page or any other page as needed
        response.sendRedirect("confirmation.jsp");
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for adding service bill";
    }
}
