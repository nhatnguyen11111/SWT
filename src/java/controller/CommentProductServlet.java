

package controller;

import entity.CommentProduct;
import entity.Items;
import entity.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import repository1.OrderRepository;
import repository1.ProductRepository;
import service.MyRandom;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="CommentProductServlet", urlPatterns={"/commentproduct"})
@MultipartConfig // Annotation để hỗ trợ việc upload file
public class CommentProductServlet extends HttpServlet {
      private static final Logger logger = Logger.getLogger(GetProductDetailServlet.class.getName());
      
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          try {
              HttpSession session = request.getSession(false);
              if (session == null) {
                  response.sendRedirect("login.jsp");
                  return;
              }
              
              User user = (User) session.getAttribute("user");
              if (user == null) {
                  response.sendRedirect("login.jsp");
                  return;
              }             
              String orderId = request.getParameter("id");
                         
              if (orderId == null || orderId.isEmpty()) {
                  response.sendRedirect("orderhistory.jsp");
                  return;
              }
              
              
              String CTVID = OrderRepository.getCTVIDByBILLID(orderId);
              List<Items> orderItems = OrderRepository.getOrder(orderId);
         
              for (Items item : orderItems) {
                  try {
                      boolean commentExists = ProductRepository.isCommentExists(item.getProduct().getProductId(), orderId, user.getUserId());
                      item.setCommentExists(commentExists); // Đặt thuộc tính này cho mỗi item
                  } catch (SQLException | ClassNotFoundException ex) {
                      Logger.getLogger(CommentProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
                  request.setAttribute("CTVID", CTVID);
              request.setAttribute("orderItems", orderItems);
              request.setAttribute("orderId", orderId);
              
              // Kiểm tra nếu không tìm thấy đơn hàng
              if (orderItems == null || orderItems.isEmpty()) {
                  response.sendRedirect("cart.jsp");
                  return;
              }
              
              
              request.getRequestDispatcher("commentproduct.jsp").forward(request, response);
          } catch (SQLException ex) {
              Logger.getLogger(CommentProductServlet.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(CommentProductServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    response.setCharacterEncoding("utf-8");
    request.setCharacterEncoding("utf-8");

    String orderId = request.getParameter("orderId");
    String CTVID = request.getParameter("CTVID");
    String productId = request.getParameter("productID");
    String ratingStr = request.getParameter("rating");
    String comment = request.getParameter("comment");

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String userID = user.getUserId();
    int rating;
    try {
        rating = Integer.parseInt(ratingStr);
    } catch (NumberFormatException e) {
        rating = 0; // Hoặc xử lý lỗi khác tùy vào yêu cầu của bạn
    }
     Part part = request.getPart("commentImg");
        logger.info("part: " + part);
        
        String filename = part.getSubmittedFileName();
        logger.info("filename: " + filename);
        String commentImg = filename; // Lưu trữ tên file ảnh vào biến productImg

        File uploadDir = new File(getServletContext().getRealPath("/") + "img");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        if (filename != null && !filename.isEmpty()) {
             String path = "D:\\FPT_VNI\\Semester 5\\SWP\\BabyCare3\\web\\img\\commentProduct" + File.separator + filename;
//           String path = getServletContext().getRealPath("/") + "img" + File.separator + "commentProduct" + File.separator + filename;

            request.setAttribute("imagePath", path);
            try (InputStream is = part.getInputStream()) {
                boolean success = uploadFile(is, path);
                if (success) {
                    logger.info("Uploaded file successfully: " + filename);
                } else {
                    logger.warning("Failed to upload file: " + filename);
                }
            }   
        }


    String commentID = MyRandom.getRandomCommentID();
    boolean commentProductSuccess = false;
    try {
        commentProductSuccess = ProductRepository.addComment1(commentID, productId, comment, orderId, userID,commentImg, rating);
    } catch (Exception ex) {
        Logger.getLogger(CommentProductServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

    if (commentProductSuccess) {
       
        // Kiểm tra xem tất cả sản phẩm đã được đánh giá chưa
        if (commentProductSuccess) {
            // Lấy danh sách các sản phẩm trong đơn hàng
            List<Items> orderItems = OrderRepository.getOrder(orderId);

            // Kiểm tra xem tất cả sản phẩm đã được đánh giá chưa
            boolean allCommented = false;
            try {
                allCommented = ProductRepository.allProductsCommented(orderId, orderItems, userID);
            } catch (SQLException ex) {
                Logger.getLogger(CommentProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CommentProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Nếu tất cả sản phẩm đã được comment, cập nhật trạng thái của đơn hàng
            if (allCommented) {
                boolean updateSuccess = OrderRepository.commentSuccessOrder(orderId, CTVID);
                if (!updateSuccess) {
                    logger.info("Failed to update order status for orderId: " + orderId);
                } else {
                    logger.info("Order status updated successfully for orderId: " + orderId);
                }
            }

            response.sendRedirect("getorderhistoryservlet");
        } else {
            response.sendRedirect("orderhistory.jsp?success=false");
        }

    logger.info("Product ID from request: " + productId);
    logger.info("Rating from request: " + rating);
    logger.info("Comment from request: " + comment);
    logger.info("Order ID from request: " + orderId);
    logger.info("Comment ID generated: " + commentID);
    logger.info("User ID: " + userID);
    logger.info("CTV ID from request: " + CTVID);
}


//    // Hàm kiểm tra xem tất cả sản phẩm trong đơn hàng đã được đánh giá chưa
//    public boolean allProductsCommented(List<Items> orderItems) {
//        for (Items item : orderItems) {
//            if (!item.isCommentExists()) {
//                return false;
//            }
//        }
//        return true;

    }
public boolean uploadFile(InputStream is, String path) {
        boolean test = false;
        try {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            try (FileOutputStream fos = new FileOutputStream(path)) {
                fos.write(bytes);
                fos.flush();
            }
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
}

