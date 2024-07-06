/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository1.OrderRepository;
import repository1.ProductRepository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="CancelOrderForCusServlet", urlPatterns={"/cancelOrderForCus"})
public class CancelOrderForCusServlet extends HttpServlet {
   
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id= request.getParameter("id");
        System.out.println(id);
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        OrderRepository.cancelOrderForCus(id,user.getUserId());
        OrderRepository.updateProductByCancelOrder(id);
        ProductRepository.updateProductByCancelOrder(id);
        response.sendRedirect("getorderhistoryservlet?thongbao=1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
