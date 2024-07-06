package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import repository1.UserRepository;

import java.io.IOException;

@WebServlet(name = "UnlockEmployeeServlet", value = "/unlockemployee")
public class UnlockEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empID=request.getParameter("empID");
        UserRepository.unlockEmployee(empID);
        response.sendRedirect("manage-emp-account");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
