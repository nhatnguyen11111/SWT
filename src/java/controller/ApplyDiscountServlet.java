package controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository1.PreferentialRepository;
import entity.Cart;
import entity.Items;
import entity.Preferential;
import entity.Product;
import entity.User;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository1.CartRepository;

@WebServlet(name = "ApplyDiscountServlet", value = "/applydiscount")
public class ApplyDiscountServlet extends HttpServlet {
    private static final double BASE_SHIPPING_FEE = 30000; // Phí vận chuyển cơ bản
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0 VNĐ");
    private static final Logger logger = Logger.getLogger(CartServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String discountID = request.getParameter("discountCode");
       
            String userId = user.getUserId();
            List<Items> itemsList = CartRepository.getProductsByUserId(userId);

            // Lặp qua từng item để lấy brandName và đặt vào map
            for (Items item : itemsList) {
                String productId = item.getProduct().getProductId();
                String brandName = CartRepository.getBrandNameByProductId(productId);
                item.setBrandName(brandName); // Đặt brandName vào trong item (nếu Items có setter cho brandName)
            }
            
            double totalAmount = calculateTotalAmount(itemsList);
            double totalShippingFee = calculateTotalShippingFee(itemsList);
            double calculateWithShip = calculateWithShip(itemsList,totalShippingFee);
            double calculatePriceDiscout = calculatePriceDiscout(itemsList, discountID);

            double totalPriceAllWithDiscount = calculateTotalPriceAllWithDiscount(itemsList, totalShippingFee, discountID);
            logger.log(Level.INFO, "Total amount: {0}, Total shipping fee: {1}",
                    new Object[]{decimalFormat.format(totalAmount), decimalFormat.format(totalShippingFee)});

            request.setAttribute("totalAmount", decimalFormat.format(totalAmount));
            request.setAttribute("totalShippingFee", decimalFormat.format(totalShippingFee));
            request.setAttribute("calculateWithShip", decimalFormat.format(calculateWithShip));
            request.setAttribute("calculatePriceDiscout", decimalFormat.format(calculatePriceDiscout));
            request.setAttribute("totalPriceAllWithDiscount", decimalFormat.format(totalPriceAllWithDiscount));
            request.setAttribute("itemsList", itemsList);
          
        try {
            // Kiểm tra mã giảm giá và ngày hiệu lực
            Preferential preferential = PreferentialRepository.checkPreferential(discountID);

            if (preferential != null && preferential.getQuantity() > 0) {
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart.getDiscountCode() == null) {
                    double discountPercent = PreferentialRepository.getDiscountPercent(discountID);
                    cart.setDiscountCode(discountID);
                    cart.setDiscountPercent(discountPercent);
                    String message = "Áp dụng mã giảm giá thành công";
                    request.setAttribute("message", message);
                    request.setAttribute("discountPercent", discountPercent);
                    session.setAttribute("discountCode", discountID);

                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                } else {
                    String message = "Mỗi đơn hàng chỉ được sử dụng 1 mã giảm giá";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("cart").forward(request, response);
                }
            } else {
                String message = "Mã giảm giá không tồn tại hoặc đã hết hiệu lực";
                request.setAttribute("message", message);
                request.getRequestDispatcher("cart").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý mã giảm giá");
        }
    }

     private double calculateTotalAmount(List<Items> itemsList) {
        double total = 0.0;
        for (Items item : itemsList) {
            if (item.getProduct() != null) {
                total += item.getProduct().getProductPrice() * item.getAmount();
            }
        }
        return total;
    }
     
     private double calculateWithShip(List<Items> itemsList, double totalShippingFee) {
        double total = 0.0;
        for (Items item : itemsList) {
            if (item.getProduct() != null) {
                total += item.getProduct().getProductPrice() * item.getAmount();
            }
        }
        return total + totalShippingFee;
    }
      private double calculatePriceDiscout(List<Items> itemsList, String discountCode) {
        double total = 0.0;
        for (Items item : itemsList) {
            if (item.getProduct() != null) {
                total += item.getProduct().getProductPrice() * item.getAmount();
            }
        }
        double discountPercent = PreferentialRepository.getDiscountPercent(discountCode);
        return total * discountPercent;
    }
    
     private double calculateTotalPriceAllWithDiscount(List<Items> itemsList, double totalShippingFee, String discountCode) {
        double totalPrice = 0;
        for (Items item : itemsList) {
            if (item.getProduct() != null) {
                totalPrice += item.getProduct().getProductPrice() * item.getAmount();
            }
        }

        double discountPercent = PreferentialRepository.getDiscountPercent(discountCode);
        return (totalPrice - (totalPrice * discountPercent)) + totalShippingFee;
    }

    private double calculateTotalShippingFee(List<Items> itemsList) {
    double totalShippingFee = 0;
    Set<String> ctvIds = new HashSet<>();

    for (Items item : itemsList) {
        if (item == null) {
            logger.log(Level.WARNING, "Item is null, skipping.");
            continue;
        }

        Product product = item.getProduct();
        if (product == null) {
            logger.log(Level.WARNING, "Product is null for Item ID: {0}, skipping.", item.getProduct());
            continue;
        }

        String ctvId = CartRepository.getCTVIdByProductId(product.getProductId());
        if (ctvId == null || ctvId.isEmpty()) {
            logger.log(Level.WARNING, "CTV ID is null or empty for Product ID: {0}, skipping.", product.getProductId());
            continue;
        }

        if (!ctvIds.contains(ctvId)) {
            logger.log(Level.INFO, "Calculating shipping fee for CTV ID: {0}", ctvId);
            ctvIds.add(ctvId);
            totalShippingFee += BASE_SHIPPING_FEE;

            // Log thông tin sản phẩm
            logger.log(Level.INFO, "Product ID: {0}, Amount: {1}, Shipping fee added: {2}",
                    new Object[]{product.getProductId(), item.getAmount(), decimalFormat.format(BASE_SHIPPING_FEE)});
        }
    }

    logger.log(Level.INFO, "Total shipping fee calculated: {0}", decimalFormat.format(totalShippingFee));

    return totalShippingFee;
    }
    
}
