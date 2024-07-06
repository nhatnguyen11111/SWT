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
@WebServlet(name="ForgotPasswordServlet", urlPatterns={"/forgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {
   
   

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String email = request.getParameter("email");
        
        boolean codeVerified = UserRepository.sendVerifyCodeAndUpdatePassword(email);
            if (codeVerified) {
                // Nếu mã code đúng, chuyển hướng người dùng sau khi đăng ký thành công tới trang đăng nhập
                request.setAttribute("thongbao", "Password mới của bạn đã được hệ thống gửi về email");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            
            } else {
                // Nếu mã code không đúng, thông báo lỗi
                request.setAttribute("thongbao", "Email không tồn tại");
                request.getRequestDispatcher("forgotPass.jsp").forward(request, response);
            }

    }  

}
