package controller;

import entity.Cart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "changeAmountServlet", value = "/changeamount")
public class ChangeAmountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        String increase = request.getParameter("increase");
        String decrease = request.getParameter("decrease");
        String delete = request.getParameter("delete");
        if (increase != null) {
            System.out.println(cart.increaseAmount(increase));
        } else if (decrease != null) {
            System.out.println(cart.decreaseAmount(decrease));
        } else if (delete != null) {
            System.out.println(cart.removeItem(delete));
        }
        request.getRequestDispatcher("cart").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
