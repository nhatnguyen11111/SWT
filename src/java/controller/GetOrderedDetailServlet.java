package controller;

import entity.Items;
import entity.User;
import repository1.OrderRepository;
import repository1.PreferentialRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@WebServlet(name = "GetOrderedDetail", value = "/getordereddetail")
public class GetOrderedDetailServlet extends HttpServlet {
    private static final double BASE_SHIPPING_FEE = 30000; // Phí vận chuyển cơ bản
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0 VNĐ");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String discountCode = request.getParameter("discountCode"); // Nhận mã giảm giá từ yêu cầu
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Kiểm tra nếu người dùng chưa đăng nhập
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy thông tin đơn hàng
        List<Items> orderItems = OrderRepository.getOrder(orderId);
        request.setAttribute("orderItems", orderItems);

        // Kiểm tra nếu không tìm thấy đơn hàng
        if (orderItems == null || orderItems.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        double totalShippingFee = BASE_SHIPPING_FEE;

        // Tính tổng số tiền cho đơn hàng trước khi giảm giá
        double totalPrice = calculateTotalPrice(orderItems);

        // Lấy tỷ lệ giảm giá
        double discountPercent = PreferentialRepository.getDiscountPercent(discountCode);
        double priceWithDiscount =  (totalPrice * discountPercent);

        // Tính tổng số tiền sau khi áp dụng giảm giá
        double totalPriceAfterDiscount = totalPrice - (totalPrice * discountPercent) + totalShippingFee;
        double totalPriceAll = totalPrice + totalShippingFee;

        // Định dạng số tiền VNĐ
        String formattedTotalPrice = decimalFormat.format(totalPrice);
        String formattedTotalPriceAfterDiscount = decimalFormat.format(totalPriceAfterDiscount);
        String formattedTotalPriceAll = decimalFormat.format(totalPriceAll);

        request.setAttribute("totalPrice", formattedTotalPrice);
        request.setAttribute("totalPriceAfterDiscount", formattedTotalPriceAfterDiscount);
        request.setAttribute("orderStatus", OrderRepository.getOrderStatus(orderId));
        request.setAttribute("totalShippingFee", decimalFormat.format(totalShippingFee));
        request.setAttribute("orderId", orderId);
        request.setAttribute("totalPriceAll", formattedTotalPriceAll);
        request.setAttribute("discountPercent", discountPercent * 100); // Chuyển đổi thành phần trăm
        request.setAttribute("priceWithDiscount", decimalFormat.format(priceWithDiscount));
        if (discountCode != null && !discountCode.isEmpty()) {
            request.setAttribute("discountCode", discountCode);
        }
        request.getRequestDispatcher("orderdetail.jsp").forward(request, response);
    }

    private double calculateTotalPrice(List<Items> items) {
        double totalPrice = 0;
        for (Items item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}
