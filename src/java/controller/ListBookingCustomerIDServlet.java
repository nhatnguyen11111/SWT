package controller;

import entity.Booking;
import entity.ServiceBill;
import entity.ServiceBooked;
import entity.User;
import repository1.ServiceRespository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

@WebServlet(name="ListBookingCustomerIDServlet", urlPatterns={"/ListBookingCustomerIDServlet"})
public class ListBookingCustomerIDServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         HttpSession session = request.getSession();
        User oldUser = (User) session.getAttribute("user");
        String customerID = oldUser.getUserId();
        ArrayList<ServiceBooked> list = ServiceRespository.getAllServiceBookingCustomer(customerID);
       
        request.setAttribute("listB", list);
        
        request.getRequestDispatcher("manageservicebooking.jsp").forward(request, response);
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
        return "Short description";
    }
}
