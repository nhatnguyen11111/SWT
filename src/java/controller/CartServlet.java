package controller;

import entity.Items;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository1.CartRepository;
import repository1.PreferentialRepository;

//@WebServlet(name = "CatServlet", urlPatterns = { "/cart" })
//public class CatServlet extends HttpServlet {
//    private static final double BASE_SHIPPING_FEE = 30000; // Phí vận chuyển cơ bản
//    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0 VNĐ");
//    private static final Logger logger = Logger.getLogger(CatServlet.class.getName());
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        String discoutCode = request.getParameter("discoutCode");
//        
//        if (session.getAttribute("user") == null) {
//            request.setAttribute("thongbao", "Vui lòng đăng nhập để sử dụng dịch vụ");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//            return;
//        }
//        if (user != null) {
//            String userId = user.getUserId();
//            List<Items> itemsList = CartRepository.getProductsByUserId(userId);
//             
//            // Lặp qua từng item để lấy brandName và đặt vào map
//            for (Items item : itemsList) {
//                String productId = item.getProduct().getProductId();
//                String brandName = CartRepository.getBrandNameByProductId(productId);
//                item.setBrandName(brandName); // Đặt brandName vào trong item (nếu Items có setter cho brandName)
//            }
//            
//            
//            
//            
//            
//            double totalAmount = calculateTotalAmount(itemsList);
//            double totalShippingFee = calculateTotalShippingFee(itemsList);
//            double calculateWithShip = calculateWithShip(itemsList, totalShippingFee);
//            double totalPriceAllWithDiscount = calculateTotalPriceAllWithDiscount(itemsList, totalShippingFee, discoutCode);
//            double calculatePriceDiscout = calculatePriceDiscout(itemsList, discoutCode);
//
//            logger.log(Level.INFO, "Total amount: {0}, Total shipping fee: {1}",
//                    new Object[]{decimalFormat.format(totalAmount), decimalFormat.format(totalShippingFee)});
//
//            request.setAttribute("totalAmount", decimalFormat.format(totalAmount));
//            request.setAttribute("totalShippingFee", decimalFormat.format(totalShippingFee));
//            request.setAttribute("calculateWithShip", decimalFormat.format(calculateWithShip));
//            request.setAttribute("totalPriceAllWithDiscount", decimalFormat.format(totalPriceAllWithDiscount));
//            request.setAttribute("calculatePriceDiscout", decimalFormat.format(calculatePriceDiscout));
//         
//
//            request.setAttribute("itemsList", itemsList);
//            request.getRequestDispatcher("cart.jsp").forward(request, response);
//        } else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in.");
//        }
//    }
//
//    private double calculateTotalAmount(List<Items> itemsList) {
//        double total = 0.0;
//        for (Items item : itemsList) {
//            if (item.getProduct() != null) {
//                total += item.getProduct().getProductPrice() * item.getAmount();
//            }
//        }
//        return total;
//    }
//    private double calculateWithShip(List<Items> itemsList, double totalShippingFee) {
//        double total = 0.0;
//        for (Items item : itemsList) {
//            if (item.getProduct() != null) {
//                total += item.getProduct().getProductPrice() * item.getAmount();
//            }
//        }
//        return total + totalShippingFee;
//    }
//     private double calculatePriceDiscout(List<Items> itemsList, String discountCode) {
//        double total = 0.0;
//        for (Items item : itemsList) {
//            if (item.getProduct() != null) {
//                total += item.getProduct().getProductPrice() * item.getAmount();
//            }
//        }
//        double discountPercent = PreferentialRepository.getDiscountPercent(discountCode);
//        return total * discountPercent;
//    }
//    
//     private double calculateTotalPriceAllWithDiscount(List<Items> itemsList, double totalShippingFee, String discountCode) {
//        double totalPrice = 0;
//        for (Items item : itemsList) {
//            if (item.getProduct() != null) {
//                totalPrice += item.getProduct().getProductPrice() * item.getAmount();
//            }
//        }
//
//        double discountPercent = PreferentialRepository.getDiscountPercent(discountCode);
//        return (totalPrice - (totalPrice * discountPercent)) + totalShippingFee;
//    }
//
//    private double calculateTotalShippingFee(List<Items> itemsList) {
//    double totalShippingFee = 0;
//    Set<String> ctvIds = new HashSet<>();
//
//    for (Items item : itemsList) {
//        if (item == null) {
//            logger.log(Level.WARNING, "Item is null, skipping.");
//            continue;
//        }
//
//        Product product = item.getProduct();
//        if (product == null) {
//            logger.log(Level.WARNING, "Product is null for Item ID: {0}, skipping.", item.getProduct());
//            continue;
//        }
//
//        String ctvId = CartRepository.getCTVIdByProductId(product.getProductId());
//        if (ctvId == null || ctvId.isEmpty()) {
//            logger.log(Level.WARNING, "CTV ID is null or empty for Product ID: {0}, skipping.", product.getProductId());
//            continue;
//        }
//
//        if (!ctvIds.contains(ctvId)) {
//            logger.log(Level.INFO, "Calculating shipping fee for CTV ID: {0}", ctvId);
//            ctvIds.add(ctvId);
//            totalShippingFee += BASE_SHIPPING_FEE;
//
//            // Log thông tin sản phẩm
//            logger.log(Level.INFO, "Product ID: {0}, Amount: {1}, Shipping fee added: {2}",
//                    new Object[]{product.getProductId(), item.getAmount(), decimalFormat.format(BASE_SHIPPING_FEE)});
//        }
//    }
//
//    logger.log(Level.INFO, "Total shipping fee calculated: {0}", decimalFormat.format(totalShippingFee));
//
//    return totalShippingFee;
//    }
//    
//    
//        
//protected void doPost(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    HttpSession session = request.getSession();
//    User user = (User) session.getAttribute("user");
//    String discoutCode = request.getParameter("discoutCode");
//    
//    if (user == null) {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in.");
//        return;
//    }
//    String userId = user.getUserId();
//
//    List<Items> itemsList = CartRepository.getProductsByUserId(userId);
//             
//            // Lặp qua từng item để lấy brandName và đặt vào map
//            for (Items item : itemsList) {
//                String productId = item.getProduct().getProductId();
//                String brandName = CartRepository.getBrandNameByProductId(productId);
//                item.setBrandName(brandName); // Đặt brandName vào trong item (nếu Items có setter cho brandName)
//            }
//
//    String[] selectedItems = request.getParameterValues("selectedItems");
//    List<Items> selectedProducts = new ArrayList<>();
//
//    if (selectedItems != null && selectedItems.length > 0) {
//        for (String itemId : selectedItems) {
//            Items item = null;
//            try {
//                item = CartRepository.getItemByProductId(itemId); // Lấy sản phẩm từ ID (cần triển khai hàm này trong CartRepository)
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(CatServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            if (item != null) {
//                selectedProducts.add(item);
//            }
//        }
//    }
//
//    // Tính toán tổng số tiền từ danh sách sản phẩm được chọn
//    double totalAmount = calculateTotalAmount(selectedProducts);
//    double totalShippingFee = calculateTotalShippingFee(selectedProducts);
//    double calculateWithShip = calculateWithShip(selectedProducts, totalShippingFee);
//    double totalPriceAllWithDiscount = calculateTotalPriceAllWithDiscount(selectedProducts, totalShippingFee, discoutCode);
//    double calculatePriceDiscout = calculatePriceDiscout(selectedProducts, discoutCode);
//
//    // Đặt các giá trị tính toán vào request để hiển thị trên JSP
//    request.setAttribute("totalAmount", decimalFormat.format(totalAmount));
//    request.setAttribute("totalShippingFee", decimalFormat.format(totalShippingFee));
//    request.setAttribute("calculateWithShip", decimalFormat.format(calculateWithShip));
//    request.setAttribute("totalPriceAllWithDiscount", decimalFormat.format(totalPriceAllWithDiscount));
//    request.setAttribute("calculatePriceDiscout", decimalFormat.format(calculatePriceDiscout));
//    request.setAttribute("itemsList", selectedProducts); // Đưa lại danh sách sản phẩm đã chọn để hiển thị
//        request.setAttribute("itemsList", itemsList); // Đưa lại danh sách sản phẩm đã chọn để hiển thị
//
//
//    request.getRequestDispatcher("cart.jsp").forward(request, response);
//}
//
//}
@WebServlet(name = "CartServlet", urlPatterns = { "/cart" })
public class CartServlet extends HttpServlet {
    private static final double BASE_SHIPPING_FEE = 30000; // Phí vận chuyển cơ bản
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0 VNĐ");
    private static final Logger logger = Logger.getLogger(CartServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String discountCode = request.getParameter("discountCode");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userId = user.getUserId();
        List<Items> itemsList = CartRepository.getProductsByUserId(userId);

        for (Items item : itemsList) {
            String productId = item.getProduct().getProductId();
            String brandName = CartRepository.getBrandNameByProductId(productId);
            item.setBrandName(brandName); // Đặt brandName vào trong item
        }

        // Lưu danh sách sản phẩm đã chọn trong session để sử dụng lại ở phương thức doPost
        session.setAttribute("itemsList", itemsList);

        request.setAttribute("totalAmount", decimalFormat.format(calculateTotalAmount(itemsList)));
        request.setAttribute("totalShippingFee", decimalFormat.format(calculateTotalShippingFee(itemsList)));
        request.setAttribute("calculateWithShip", decimalFormat.format(calculateWithShip(itemsList, calculateTotalShippingFee(itemsList))));
        request.setAttribute("totalPriceAllWithDiscount", decimalFormat.format(calculateTotalPriceAllWithDiscount(itemsList, calculateTotalShippingFee(itemsList), discountCode)));
        request.setAttribute("calculatePriceDiscount", decimalFormat.format(calculatePriceDiscount(itemsList, discountCode)));
        request.setAttribute("itemsList", itemsList);

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String discountCode = request.getParameter("discountCode");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Items> itemsList = (List<Items>) session.getAttribute("itemsList");
        String[] selectedItems = request.getParameterValues("selectedItems");
        List<Items> selectedProducts = new ArrayList<>();

        if (selectedItems != null && selectedItems.length > 0) {
            for (String itemId : selectedItems) {
                Items item = null;
                try {
                    item = CartRepository.getItemByProductId(itemId);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (item != null) {
                    selectedProducts.add(item);
                }
            }
        }

        // Lưu lại danh sách sản phẩm đã chọn vào session để sử dụng lại khi cần thiết
        session.setAttribute("itemsList", selectedProducts);

        // Tính toán lại các giá trị cho danh sách sản phẩm đã chọn
        double totalAmount = calculateTotalAmount(selectedProducts);
        double totalShippingFee = calculateTotalShippingFee(selectedProducts);
        double calculateWithShip = calculateWithShip(selectedProducts, totalShippingFee);
        double totalPriceAllWithDiscount = calculateTotalPriceAllWithDiscount(selectedProducts, totalShippingFee, discountCode);
        double calculatePriceDiscount = calculatePriceDiscount(selectedProducts, discountCode);

        // Đặt các giá trị tính toán vào request để hiển thị trên JSP
        request.setAttribute("totalAmount", decimalFormat.format(totalAmount));
        request.setAttribute("totalShippingFee", decimalFormat.format(totalShippingFee));
        request.setAttribute("calculateWithShip", decimalFormat.format(calculateWithShip));
        request.setAttribute("totalPriceAllWithDiscount", decimalFormat.format(totalPriceAllWithDiscount));
        request.setAttribute("calculatePriceDiscout", decimalFormat.format(calculatePriceDiscount));
        request.setAttribute("itemsList", selectedProducts); // Đưa lại danh sách sản phẩm đã chọn để hiển thị

        request.getRequestDispatcher("cart.jsp").forward(request, response);
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

    private double calculatePriceDiscount(List<Items> itemsList, String discountCode) {
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
