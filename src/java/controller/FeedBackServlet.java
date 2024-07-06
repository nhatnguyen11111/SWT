package controller;

import entity.Feedback;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import repository1.ServiceRespository;

@WebServlet(name = "FeedBackServlet", urlPatterns = {"/FeedBackServlet"})
public class FeedBackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerID = request.getParameter("CustomerID");        
        int serviceID = Integer.parseInt(request.getParameter("ServiceID"));
        request.setAttribute("CustomerID", customerID);
        request.setAttribute("ServiceID", serviceID);
        request.getRequestDispatcher("feedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerID = request.getParameter("CustomerID");
        String serviceIDString = request.getParameter("ServiceID");
        String testimonial = request.getParameter("testimonial");
        String experienceDateString = request.getParameter("experienceDate");
        String satisfactionLevelString = request.getParameter("satisfactionLevel");
        String name = request.getParameter("name");
      
         
        int serviceID;
        int satisfactionLevel;
        Date experienceDate;
        
       
            serviceID = Integer.parseInt(serviceIDString);
            satisfactionLevel = Integer.parseInt(satisfactionLevelString);
            experienceDate = Date.valueOf(experienceDateString);
       

        Feedback feedback = new Feedback(customerID, serviceID, testimonial, experienceDate, satisfactionLevel, name);

       
            ServiceRespository.saveFeedback(feedback);
            response.sendRedirect("thankyou.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
