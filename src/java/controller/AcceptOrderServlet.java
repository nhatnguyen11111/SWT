package controller;

import entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository1.OrderRepository;

import java.io.IOException;

@WebServlet(name = "AcceptOrderServlet", value = "/acceptorder")
public class AcceptOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id= request.getParameter("id");
        System.out.println(id);
            HttpSession session=request.getSession();
            User user=(User) session.getAttribute("user");
            OrderRepository.acceptOrder(id,user.getUserId());
            response.sendRedirect("order-list-manager?thongbao=1");
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
