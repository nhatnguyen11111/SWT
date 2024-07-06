package controller;

import entity.Cart;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RemoveDiscountCodeServlet", urlPatterns = {"/removediscountcode"})
public class RemoveDiscountCodeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            cart.removeDiscountCode(); // Gọi phương thức xóa discountCode từ Cart
        }
        session.removeAttribute("discountCode");
        session.removeAttribute("typePayment");
        response.sendRedirect(request.getContextPath() + "/cart.jsp");
    }
}
