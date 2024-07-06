/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Preferential;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository1.PreferentialRepository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "PreferentialListManagerServlet", urlPatterns = {"/preferential-list-manager"})
public class PreferentialListManagerServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String EmployeeID = user.getUserId();
        ArrayList<Preferential> listPreferential = PreferentialRepository.getListPreferentialByEmployeeID(EmployeeID);
        request.setAttribute("preferentialList", listPreferential);
        request.getRequestDispatcher("preferential-list-manager.jsp").forward(request, response);
        System.out.println("EmployeeID: " + EmployeeID);
        System.out.println("List Size: " + listPreferential.size()); // Logging
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST request if needed
    }
}
