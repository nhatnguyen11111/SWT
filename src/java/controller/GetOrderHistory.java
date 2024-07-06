package controller;

import entity.Cart;
import entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository1.OrderRepository;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import repository1.PreferentialRepository;

@WebServlet(name = "GetOrderHistory", value = "/getorderhistoryservlet")
public class GetOrderHistory extends HttpServlet {
    private static final DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Kiểm tra nếu người dùng chưa đăng nhập
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userId = user.getUserId();
//        ArrayList<String> listOrderId = OrderRepository.getOrderIdList(userId); //danh sach id cua cac order
                List<Cart> listOrdered  = OrderRepository.getOrdersWithDetails(userId); //danh sach id cua cac order

//        ArrayList<Cart> listOrdered = new ArrayList<>(); //list cac don hang da dat(cart)
//
//        for (String orderId : listOrderId) {
//            Cart orderedCart = new Cart();
//            orderedCart.setCart(OrderRepository.getOrder(orderId));
//            orderedCart.setOrderedId(orderId);
//            orderedCart.setOrderStatus(OrderRepository.getOrderStatus(orderId));
//            orderedCart.setDate(OrderRepository.getOrderDate(orderId));
//            orderedCart.setDiscountCode(OrderRepository.getDiscountCodeByOrderID(orderId));
//            orderedCart.setDiscountPercent(PreferentialRepository.getDiscountPercent(orderedCart.getDiscountCode()));
//
//            // Check if the order can be canceled within 24 hours
//            boolean canCancel = canCancelOrder(orderedCart.getDate());
//            orderedCart.setCanCancel(canCancel);
//
//            listOrdered.add(orderedCart);
//        }
        String thongbao;
        try{
            if(!request.getParameter("thongbao").equals(null)){
                if(request.getParameter("thongbao").equals("1")){
                    thongbao="Đơn hàng của bạn được hủy thành công";
                }
                else {
                    thongbao = "Đơn hàng của bạn được hủy không thành công";
                }
                request.setAttribute("thongbao",thongbao);
            }
        }
        catch (Exception e){

        }

//        Collections.sort(listOrdered, new Comparator<Cart>() {
//            @Override
//            public int compare(Cart o1, Cart o2) {
//                LocalDateTime localDateTime1;
//                LocalDateTime localDateTime2;
//                try {
//                    localDateTime1 = LocalDateTime.parse(o1.getDate(), formatterWithTime);
//                } catch (DateTimeParseException e) {
//                    localDateTime1 = LocalDate.parse(o1.getDate(), formatterWithoutTime).atStartOfDay();
//                }
//                try {
//                    localDateTime2 = LocalDateTime.parse(o2.getDate(), formatterWithTime);
//                } catch (DateTimeParseException e) {
//                    localDateTime2 = LocalDate.parse(o2.getDate(), formatterWithoutTime).atStartOfDay();
//                }
//                return -1 * localDateTime1.compareTo(localDateTime2);
//            }
//        });
//
//        request.setAttribute("listOrdered", listOrdered);
//        request.getRequestDispatcher("orderhistory.jsp").forward(request, response);
     Collections.sort(listOrdered, new Comparator<Cart>() {
            @Override
            public int compare(Cart o1, Cart o2) {
                LocalDateTime localDateTime1;
                LocalDateTime localDateTime2;
                try {
                    localDateTime1 = LocalDateTime.parse(o1.getDate(), formatterWithTime);
                } catch (DateTimeParseException e) {
                    localDateTime1 = LocalDate.parse(o1.getDate(), formatterWithoutTime).atStartOfDay();
                }
                try {
                    localDateTime2 = LocalDateTime.parse(o2.getDate(), formatterWithTime);
                } catch (DateTimeParseException e) {
                    localDateTime2 = LocalDate.parse(o2.getDate(), formatterWithoutTime).atStartOfDay();
                }
                return -1 * localDateTime1.compareTo(localDateTime2);
            }
        });

        request.setAttribute("listOrdered", listOrdered);
        request.getRequestDispatcher("orderhistory.jsp").forward(request, response);
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private boolean canCancelOrder(String dateString) {
        LocalDateTime orderDateTime;
        try {
            DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            orderDateTime = LocalDateTime.parse(dateString, formatterWithTime);
        } catch (DateTimeParseException e) {
            DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            orderDateTime = LocalDate.parse(dateString, formatterWithoutTime).atStartOfDay();
        }
        
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(orderDateTime, now);
        return duration.toHours() < 24;
    }
}
