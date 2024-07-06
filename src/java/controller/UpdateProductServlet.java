package controller;

import entity.Product;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository1.ProductRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@WebServlet(name = "UpdateProductServlet", value = "/updateproduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50) // Annotation để hỗ trợ việc upload file
public class UpdateProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UpdateProductServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = request.getParameter("productID");

        try {
            Product product = ProductRepository.getProductInfor(productID);
            if (product == null) {
                request.setAttribute("error", "Product not found");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
            request.setAttribute("product", product);
            request.getRequestDispatcher("update-product.jsp").forward(request, response);
        } catch (Exception e) {
            logger.severe("Error fetching product info: " + e.getMessage());
            request.setAttribute("error", "Error fetching product info");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String productID = request.getParameter("productID");
        String productName = request.getParameter("productName");
        String productType = request.getParameter("productType");
        String productOrigin = request.getParameter("productOrigin");
        String productDescription = request.getParameter("productDescription");
        int productAmount = Integer.parseInt(request.getParameter("productAmount"));
        int productStatus = Integer.parseInt(request.getParameter("status"));
        double productPrice;

        try {
            productPrice = Double.parseDouble(request.getParameter("productPrice"));
        } catch (NumberFormatException e) {
            logger.warning("Invalid product price: " + e.getMessage());
            request.setAttribute("error", "Invalid product price");
            request.getRequestDispatcher("update-product.jsp").forward(request, response);
            return;
        }

         Part part = request.getPart("productImg");
        String filename = part != null ? part.getSubmittedFileName() : "";
        String absolutePath = "D:\\FPT_VNI\\Semester 5\\vip3\\BabyCare\\web\\img" + File.separator + filename;
        String relativePath = getServletContext().getRealPath("/") + "img" + File.separator +  filename;

        if (!filename.isEmpty()) {
            // Tạo thư mục nếu chưa tồn tại
            File absoluteDir = new File("D:\\FPT_VNI\\Semester 5\\vip3\\BabyCare\\web\\img");
            if (!absoluteDir.exists()) {
                absoluteDir.mkdirs();
            }

            File relativeDir = new File(getServletContext().getRealPath("/") + "img" + File.separator );
            if (!relativeDir.exists()) {
                relativeDir.mkdirs();
            }

            // Lưu file vào cả hai vị trí
            try (InputStream is = part.getInputStream()) {
                boolean success1 = uploadFile(is, absolutePath);
                boolean success2 = uploadFile(is, relativePath);

                if (!success1 || !success2) {
                    logger.warning("Failed to upload file: " + filename);
                    request.setAttribute("error", "Failed to upload file");
                    request.getRequestDispatcher("update-product.jsp").forward(request, response);
                    return;
                }
            } catch (Exception e) {
                logger.severe("Error uploading file: " + e.getMessage());
                request.setAttribute("error", "Error uploading file");
                request.getRequestDispatcher("update-product.jsp").forward(request, response);
                return;
            }
        }

        Product product = new Product(productID, productName, productType, productOrigin, productPrice, productAmount, productDescription, filename, productStatus);

        try {
            boolean updateSuccess = ProductRepository.UpdateProduct(product);
            if (updateSuccess) {
                request.setAttribute("thongbao", "Cập nhật thành công");
                request.setAttribute("product", product);
                request.getRequestDispatcher("update-product.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Failed to update product");
                request.getRequestDispatcher("update-product.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.severe("Error updating product: " + e.getMessage());
            request.setAttribute("error", "Error updating product");
            request.getRequestDispatcher("update-product.jsp").forward(request, response);
        }
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
