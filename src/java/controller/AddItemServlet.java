import entity.Brand;
import entity.Cart;
import entity.CommentProduct;
import entity.Items;
import entity.Product;
import entity.User;
import repository1.ProductRepository;
import repository1.CartRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import repository1.UserRepository;

@WebServlet(name = "AddItemServlet", value = "/additem")
public class AddItemServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            request.setAttribute("thongbao", "Vui lòng đăng nhập để sử dụng dịch vụ");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        User user = (User) session.getAttribute("user");
        String userID = user.getUserId();

        try {
            String id = request.getParameter("id");
            String amount = request.getParameter("amount");
            Product p = ProductRepository.getProductById(id);

            Cart cart = (Cart) session.getAttribute("cart");
            boolean productExistsInCart = false;

            if (cart != null) {
                for (Items item : cart.getCart()) {
                    if (item.getProduct().getProductId().equals(id)) {
                        // If the product already exists in the cart, update the quantity
                        int quantity = item.getAmount() + Integer.parseInt(amount);
                        item.setAmount(quantity);
                        CartRepository.updateCartItemQuantity(userID, id, item.getAmount());
                        productExistsInCart = true;
                        break;
                    }
                }
            } else {
                cart = new Cart();
            }
            
            Product product = ProductRepository.getProductById(id);
            if (product == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            List<CommentProduct> comments = ProductRepository.listCommentsByProductId(id);
            
            
            for (CommentProduct comment : comments) {
                User userr = UserRepository.getUserDetailsByCommentId(comment.getCommentID());
                comment.setUser(userr); // 
            }
            Brand brand = ProductRepository.getBrandByCTVId(product.getCTVID());
//            User userr = UserRepository.getUserByProductId(id);
            int TotalRating = ProductRepository.getTotalRatingForProduct(product.getProductId());
            int TotalComment = ProductRepository.getTotalCommentsForProduct(product.getProductId());
            request.setAttribute("listComments", comments);
//            request.setAttribute("user", userr);
            request.setAttribute("TotalRating", TotalRating);
            request.setAttribute("TotalComment", TotalComment);
            request.setAttribute("product", product);
            request.setAttribute("brand", brand);

            // Sau khi cập nhật hoặc thêm sản phẩm vào giỏ hàng
            if (!productExistsInCart) {
                Items item = new Items(p, Integer.parseInt(amount));
                cart.addItems(item);
                CartRepository.saveCart(cart, userID); // Lưu giỏ hàng vào cơ sở dữ liệu
            }

            request.setAttribute("product", p);
            request.setAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công");
            session.setAttribute("cart", cart);
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("=============> Loi AdditemServlet <===============");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Bổ sung xử lý POST nếu cần
    }
}

