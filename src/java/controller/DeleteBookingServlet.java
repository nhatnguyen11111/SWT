package controller;

import repository1.ServiceRespository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="DeleteBookingServlet", urlPatterns={"/DeleteBookingServlet"})
public class DeleteBookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String bookingID = request.getParameter("bookingID");
        String billID = request.getParameter("billID");
        if (bookingID != null && !bookingID.isEmpty()) {
            ServiceRespository serviceRepository = new ServiceRespository();
            serviceRepository.deleteBillService(Integer.parseInt(billID));
            serviceRepository.deleteBooking(Integer.parseInt(bookingID));
            
        }

        response.sendRedirect("ListBookingCustomerIDServlet");
    }
      
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
