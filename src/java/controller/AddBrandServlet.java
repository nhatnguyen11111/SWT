package controller;

import entity.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
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
import repository1.UserRepository;
import service.MyRandom;

@WebServlet(name = "AddBrandServlet", urlPatterns = {"/addBrand"})
@MultipartConfig // Annotation để hỗ trợ việc upload file
public class AddBrandServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddBrandServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Check if user is not logged in
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userID = user.getUserId();
        boolean hasPending = UserRepository.hasPendingRegistration(userID);
        System.out.println(hasPending);
        request.setAttribute("hasPending", hasPending);
        request.getRequestDispatcher("registerctv.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Check user
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userID = user.getUserId();

        String brandName = request.getParameter("brandName");
        System.out.println(brandName);
        String brandDescription = request.getParameter("brandDescription");
        System.out.println(brandDescription);
        Part part = request.getPart("logo");
        logger.info("part: " + part);

        if (part == null || part.getSize() == 0) {
            logger.warning("Part 'logo' is missing or empty.");
            request.setAttribute("thongbao", "Vui lòng chọn hình ảnh sản phẩm.");
            request.getRequestDispatcher("registerctv.jsp").forward(request, response);
            return;
        }

        String filename = part.getSubmittedFileName();
        logger.info("filename: " + filename);
        String logo = filename; // Lưu trữ tên file ảnh vào biến logo

        // Tạo thư mục nếu chưa tồn tại
        File absoluteDir = new File("D:\\FPT_VNI\\Semester 5\\SWP\\BabyCare3\\web\\img\\brand");
        if (!absoluteDir.exists()) {
            absoluteDir.mkdirs();
        }

        File relativeDir = new File(getServletContext().getRealPath("/") + "img" + File.separator + "brand");
        if (!relativeDir.exists()) {
            relativeDir.mkdirs();
        }

        if (filename != null && !filename.isEmpty()) {
            String absolutePath = "D:\\FPT_VNI\\Semester 5\\SWP\\BabyCare3\\web\\img\\brand" + File.separator + filename;
            String relativePath = getServletContext().getRealPath("/") + "img" + File.separator + "brand" + File.separator + filename;

            // Lưu file vào cả hai vị trí
            try (InputStream is = part.getInputStream()) {
                boolean success1 = uploadFile(is, absolutePath);
                boolean success2 = uploadFile(is, relativePath);
                if (success1 && success2) {
                    logger.info("Uploaded file successfully to both locations: " + filename);
                } else {
                    logger.warning("Failed to upload file to one or both locations: " + filename);
                }
            }
        }

        String brandAddress = request.getParameter("brandAddress");
        logger.info("Parameters: brandName=" + brandName + ", brandDescription=" + brandDescription + ", brandAddress=" + brandAddress);

        try {
            if (UserRepository.checkBrandNameExist(brandName)) {
                request.setAttribute("thongbao", "Tên cửa hàng đã tồn tại");
                request.getRequestDispatcher("registerctv.jsp").forward(request, response);
            } else {
                String brandID = MyRandom.getRandomBrandID();

                logger.info("Registering Brand: " + brandID + " " + brandName + " " + brandDescription + " " + logo + " " + brandAddress);
                boolean added = UserRepository.addBrand(brandID, brandName, brandDescription, logo, brandAddress, userID);
                request.setAttribute("thongbao", "Chúng tôi đã tiếp nhận thông tin của bạn. Chúng tôi sẽ thông báo qua email của bạn trong vòng 7 ngày..");
                if (added == false) {
                    request.setAttribute("thongbao", "Có lỗi xảy ra khi đăng kí cửa hàng. Vui lòng thử lại.");
                }
                request.getRequestDispatcher("registerctv.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding brand to the database", e);
            request.setAttribute("thongbao", "Có lỗi xảy ra khi đăng kí cửa hàng. Vui lòng thử lại abc.");
            response.sendRedirect("addBrand");
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
