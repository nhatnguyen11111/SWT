/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.OrderAccept;
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
import repository1.OrderRepository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="ListOrderSuccsessServlet", urlPatterns={"/list-order-succsess"})
public class ListOrderSuccsessServlet extends HttpServlet {
   
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String CTVID = user.getUserId();
        ArrayList<OrderAccept> listOrder = OrderRepository.getAllOrderSuccsessByCTVId(CTVID);
        request.setAttribute("listOrder",listOrder);
        request.getRequestDispatcher("order-list-succsess.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
