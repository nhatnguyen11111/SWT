package controller;

import entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("Lay thong tin cua" + user);
        String firstname = user.getFirstname(), lastname = user.getLastname(), address = user.getAddress(),avatar = user.getAvatar(), phone = user.getPhone();
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname", lastname);
        request.setAttribute("address", address);
        request.setAttribute("avatar", avatar);
        request.setAttribute("phone", phone);
        
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
