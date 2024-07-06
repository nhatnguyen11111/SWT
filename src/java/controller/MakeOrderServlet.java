package controller;

import entity.Cart;
import entity.Items;
import entity.User;
import repository1.OrderRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository1.CartRepository;
import repository1.PreferentialRepository;
import repository1.ProductRepository;

@WebServlet(name = "MakeOrderServlet", value = "/makeorder")
public class MakeOrderServlet extends HttpServlet {
       private static final Logger logger = Logger.getLogger(MakeOrderServlet.class.getName());
    private static final double BASE_SHIPPING_FEE = 30000; // Phí vận chuyển cơ bản
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0 VNĐ");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");    
        Cart cart = (Cart) session.getAttribute("cart");
        String userID = user.getUserId();
        String discountCode = request.getParameter("discountCode");  // Lấy mã giảm giá từ session
        
        if (cart == null || cart.getCart().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        if (user != null) {
            Map<String, List<Items>> orderItemsMap = new HashMap<>();
            List<String> orderIds = new ArrayList<>();
            boolean allOrdersCreated = true;

            // Split cart into orders by CTV
            Map<String, List<Items>> itemsByCTV = cart.splitByCTV();

            for (Map.Entry<String, List<Items>> entry : itemsByCTV.entrySet()) {
                String sellerId = entry.getKey();
                List<Items> ctvItems = entry.getValue();
                System.out.println("Creating order for sellerId: " + sellerId + " with items: " + ctvItems);

                String orderId = OrderRepository.createOrder(ctvItems, user, sellerId, discountCode, cart.getPaymentType());

                if (orderId != null) {
                    orderIds.add(orderId);
                    System.out.println("Order created with ID: " + orderId);
                    // Retrieve the cart items for the current order
                    List<Items> orderItems = OrderRepository.getOrdersByBillId(orderId);
                    if (orderItems != null && !orderItems.isEmpty()) {
                        orderItemsMap.put(orderId, orderItems);
                        System.out.println("Items for order " + orderId + ": " + orderItems);
                    } else {
                        orderItemsMap.put(orderId, new ArrayList<>());
                        System.out.println("No items found for order " + orderId);
                    }
                     
                    for (Items item : orderItems) {
                        logger.info("Updating sold for product ID: " + item.getProduct().getProductId() + ", Quantity: " + item.getAmount());
                        boolean soldUpdated = ProductRepository.updateProductSold(item.getProduct().getProductId(), item.getAmount());
                        if (!soldUpdated) {
                            logger.warning("Failed to update sold quantity for product ID: " + item.getProduct().getProductId());
                        }
                    }


                    
                    for (Items item : ctvItems) {
                        try {
                            boolean isUpdated = CartRepository.updateProductQuantity(item.getProduct().getProductId(), item.getAmount());
                            if (!isUpdated) {
                                System.out.println("Failed to update quantity for product ID: " + item.getProduct().getProductId());
                                allOrdersCreated = false;
                                break;
                            }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MakeOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    double discount = PreferentialRepository.getDiscountPercent(discountCode);
                    double totalPrice = calculateTotalPrice(ctvItems);
                    double priceWithDiscount = totalPrice * discount;
                    String formattedTotalPrice = decimalFormat.format(totalPrice);

                    session.setAttribute("discountCode", discountCode);
                    request.setAttribute("priceWithDiscount", decimalFormat.format(priceWithDiscount));
                    request.setAttribute("totalPrice", formattedTotalPrice);
                    request.setAttribute("orderStatus", OrderRepository.getOrderStatus(orderId));
                    request.setAttribute("orderItems", orderItems);
                } else {
                    allOrdersCreated = false;
                    System.out.println("Failed to create order for sellerId: " + sellerId);
                    break;
                }
            }

            if (allOrdersCreated) {
                double discountPercent = PreferentialRepository.getDiscountPercent(discountCode) * 100;
                cart.removeAll();
                
                double totalShippingFee = calculateTotalShippingFee(orderIds.size());
                double totalPriceAll = calculateTotalPriceAll(orderItemsMap, totalShippingFee);
                double totalPriceAllWithDiscount = calculateTotalPriceAllWithDiscount(orderItemsMap, totalShippingFee, discountCode);
                String formattedTotalPriceAll = decimalFormat.format(totalPriceAll);
                if (discountCode != null && !discountCode.isEmpty()) {
                        PreferentialRepository.decreasePreferentialQuantity(discountCode);
                    }
                request.setAttribute("discountPercent", discountPercent);
                request.setAttribute("orderItemsMap", orderItemsMap);
                request.setAttribute("orderIds", orderIds);
                request.setAttribute("totalShippingFee", decimalFormat.format(totalShippingFee));
                request.setAttribute("totalPriceAll", formattedTotalPriceAll);
                request.setAttribute("totalPriceAllWithDiscount", decimalFormat.format(totalPriceAllWithDiscount));
                CartRepository.removeAllCartItems(userID);
                session.removeAttribute("cart");
                System.out.println("Order creation successful. orderItemsMap: " + orderItemsMap + ", orderIds: " + orderIds);
                request.getRequestDispatcher("ordered.jsp").forward(request, response);

            } else {
                System.out.println("Failed to create one or more orders.");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create one or more orders.");
            }
        } else {
            System.out.println("User not logged in.");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in.");
        }
    }

    private double calculateTotalPrice(List<Items> items) {
        double totalPrice = 0;
        for (Items item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    private double calculateTotalShippingFee(int numberOfOrders) {
        return BASE_SHIPPING_FEE * numberOfOrders;
    }

    private double calculateTotalPriceAll(Map<String, List<Items>> orderItemsMap, double totalShippingFee) {
        double totalPrice = 0;
        for (List<Items> items : orderItemsMap.values()) {
            for (Items item : items) {
                totalPrice += item.getPrice();
            }
        }
        return totalPrice + totalShippingFee;
    }

    private double calculateTotalPriceAllWithDiscount(Map<String, List<Items>> orderItemsMap, double totalShippingFee, String discountCode) {
        double totalPrice = 0;
        for (List<Items> items : orderItemsMap.values()) {
            for (Items item : items) {
                totalPrice += item.getPrice();
            }
        }

        double discountPercent = PreferentialRepository.getDiscountPercent(discountCode);
        return (totalPrice - (totalPrice * discountPercent)) + totalShippingFee;
    }
    
}
