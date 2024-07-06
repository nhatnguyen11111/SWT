package controller;

import entity.Feedback;
import entity.Service;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository1.ServiceRespository;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "getServiceDetailServlet", value = "/getservicedetail")
public class GetServiceDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("serviceID");
        if (id == null) {
            response.sendRedirect("index.jsp");
        } else {
            System.out.println("===============>");
            System.out.println("===============> " + id);
            Service s = ServiceRespository.getService(id);
            ArrayList<Feedback> feedbackList = ServiceRespository.getFeedBackByServiceID(id);
 
                request.setAttribute("feedbackList", feedbackList);
            request.setAttribute("service", s);
            request.getRequestDispatcher("service-detail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
