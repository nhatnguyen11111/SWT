/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository1.UserRepository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="UnApproveCTVServlet", urlPatterns={"/unApproveCTV"})
public class UnApproveCTVServlet extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId=request.getParameter("CTVID");
        UserRepository.deleteBrandByCTVID1(userId);
        response.sendRedirect("listRegisterCTV");
    }

    

}
