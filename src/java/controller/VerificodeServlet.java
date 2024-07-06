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
@WebServlet(name="VerificodeServlet", urlPatterns={"/VerificodeServlet"})
public class VerificodeServlet extends HttpServlet {
   
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String userID = (String) request.getSession().getAttribute("userID");
        String code = request.getParameter("authcode");
        
         boolean codeVerified = UserRepository.verifyCodeAndUpdateStatus(userID, code);
            if (codeVerified) {
                // Nếu mã code đúng, chuyển hướng người dùng sau khi đăng ký thành công tới trang đăng nhập
                request.setAttribute("thongbao", "Đăng ký thành công, vui lòng đăng nhập vào hệ thống");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            
            } else {
                // Nếu mã code không đúng, thông báo lỗi
                request.setAttribute("thongbao", "Mã xác minh không hợp lệ");
                UserRepository.deleteCustomer(userID);
                request.getRequestDispatcher("verificode.jsp").forward(request, response);
            }

    }
    
 
}
