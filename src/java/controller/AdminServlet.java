package controller;

import entity.User;
import repository1.UserRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


           if (user == null || user.getRole() != 1) {
            response.sendRedirect("login.jsp");
            return;
        }
//        ArrayList<User> userList = UserRepository.getAllUsers();
//        request.setAttribute("userList", userList);

        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests if needed
    }
}
