package controller;

import entity.Brand;
import entity.CommentProduct;
import entity.Product;
import entity.User;
import repository1.ProductRepository;
import repository1.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "GetProductDetailServlet", value = "/getProductDetail")
public class GetProductDetailServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(GetProductDetailServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            Product product = ProductRepository.getProductById(id);
            if (product == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            List<CommentProduct> comments = ProductRepository.listCommentsByProductId(id);
            Brand brand = ProductRepository.getBrandByCTVId(product.getCTVID());
            for (CommentProduct comment : comments) {
                User user = UserRepository.getUserDetailsByCommentId(comment.getCommentID());
                comment.setUser(user); // 
            }
            int TotalRating = ProductRepository.getTotalRatingForProduct(product.getProductId());
            double getAverageRating = ProductRepository.getAverageRatingForProduct(product.getProductId());
            int TotalComment = ProductRepository.getTotalCommentsForProduct(product.getProductId());
            request.setAttribute("listComments", comments);
            request.setAttribute("TotalRating", getAverageRating);
            request.setAttribute("TotalComment", TotalComment);
            request.setAttribute("product", product);
            request.setAttribute("brand", brand);
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error occurred while getting product details: ", ex);
            response.sendRedirect("index.jsp");
        }
    }
}
