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
import repository1.UserRepository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="SucscessfullOrderServlet", urlPatterns={"/succsessfull"})
public class SucscessfullOrderServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id= request.getParameter("id");
        System.out.println(id);
            HttpSession session=request.getSession();
            User user=(User) session.getAttribute("user");
            OrderRepository.SuccsessOrder(id,user.getUserId());
            OrderRepository orderRepository = new OrderRepository();
            String name = orderRepository.getNameByOrderId(id);
            String email = orderRepository.getEmailByOrderId(id);
            UserRepository userRepository = new UserRepository();
            UserRepository.sendCodeToEmailSuccsessOrder(email,id,name);
            
            response.sendRedirect("order-list-paid?thongbao=1");
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
