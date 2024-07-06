/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository1.ServiceRespository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateServiceServlet", urlPatterns = {"/UpdateService"})
public class UpdateServiceServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateServiceServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateServiceServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String id = request.getParameter("serviceID");

    // Retrieve service information from the repository
    Service service = ServiceRespository.getService(id);

    // Debugging output
    System.out.println(service);

    // Set service attribute and forward the request
    request.setAttribute("service", service);
    request.getRequestDispatcher("service-update.jsp").forward(request, response);
}

   
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Retrieve parameters from the request
    int serviceID = Integer.parseInt(request.getParameter("serviceID"));
    
    String serviceName = request.getParameter("serviceName");
    double servicePrice = Double.parseDouble(request.getParameter("servicePrice"));
    String listImg = request.getParameter("listImg");
    String description = request.getParameter("description");

    // Debugging output
    System.out.println("Updating service  " + serviceName + " " + servicePrice + " " + listImg + " " + description);

    // Create a new Service object
    Service service = new Service( serviceID, serviceName, servicePrice,listImg, description);

    // Update the service using the repository
    boolean updateSuccess = false;
        try {
            updateSuccess = ServiceRespository.updateService(service);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    // Set attributes and forward the request
    if (updateSuccess) {
        request.setAttribute("thongbao", "Cập nhật thành công");
    } else {
        request.setAttribute("thongbao", "Cập nhật thất bại");
    }
    request.setAttribute("service", service);

    // Forward the request to the JSP page
    request.getRequestDispatcher("service-update.jsp").forward(request, response);
}

    }

   
  
