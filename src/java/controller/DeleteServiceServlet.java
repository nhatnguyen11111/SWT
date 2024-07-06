/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static repository1.ServiceRespository.deleteService;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteServiceServlet", urlPatterns = {"/DeleteService"})
public class DeleteServiceServlet extends HttpServlet {

       
    @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        

     
        try {
            int serviceID = Integer.parseInt(request.getParameter("serviceID"));
            boolean isDeleted = deleteService(serviceID);
            
            
            
            
            // Chuyển hướng về trang danh sách dịch vụ
            response.sendRedirect(request.getContextPath() + "/ServiceList");
        } catch (SQLException ex) {
            Logger.getLogger(DeleteServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Lấy serviceID từ yêu cầu
//        String service = request.getParameter("serviceID");
//        try {
//            int serviceID = Integer.parseInt(service);
//            boolean isDeleted = deleteService(serviceID);
//
//            if (isDeleted) {
//                // Chuyển hướng về trang danh sách dịch vụ
//                response.sendRedirect(request.getContextPath() + "ServiceList");
//            } else {
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                response.getWriter().write("Service not found.");
//            }
//        } catch (NumberFormatException e) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("Invalid service ID.");
//        } catch (SQLException | ClassNotFoundException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("Error deleting service.");
//            e.printStackTrace();
//        }
//        request.getRequestDispatcher("ServiceList").forward(request, response);
//    }
       
}
