package controller;

import entity.OrderAccept;
import entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import repository1.OrderRepository;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ListOrderCancelServlet", value = "/list-order-cancel")
public class ListOrderCancelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String CTVID = user.getUserId();
        ArrayList<OrderAccept> listOrder = OrderRepository.getAllOrderCancel(CTVID);
        request.setAttribute("listOrder",listOrder);
        request.getRequestDispatcher("order-list-cancel.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
