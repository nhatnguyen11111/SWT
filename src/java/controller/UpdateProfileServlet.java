package controller;

import entity.User;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import repository1.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet(name = "UpdateProfileServlet", value = "/updateprofile")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("profile");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        HttpSession session = request.getSession();
        User oldUser = (User) session.getAttribute("user");
        String userID = oldUser.getUserId();
        
        // Update user in session
        oldUser.setFirstname(firstname);
        oldUser.setLastname(lastname);
        oldUser.setAddress(address);
        oldUser.setPhone(phone);
        session.setAttribute("user", oldUser);
        
        // Update in database
        UserRepository.updateUser(userID, firstname, lastname, address, phone);
        
        Part part = request.getPart("avatar");
        String filename = part.getSubmittedFileName();
        if (filename != null && !filename.isEmpty()) {
            // Đường dẫn tuyệt đối trên hệ thống
            String absolutePath = "D:\\FPT_VNI\\Semester 5\\SWP\\BabyCare3\\web\\img" + File.separator + filename;
            // Đường dẫn tương đối trong thư mục của dự án
            String relativePath = getServletContext().getRealPath("/") + "img" + File.separator + filename;

            request.setAttribute("imagePath", absolutePath);
            request.setAttribute("imagePathRelative", relativePath);
            try (InputStream is = part.getInputStream()) {
                boolean success1 = uploadFile(is, absolutePath);
                boolean success2 = uploadFile(is, relativePath);
                if (success1 && success2) {
                    // Update avatar in session and database if upload is successful
                    oldUser.setAvatar(filename);
                    session.setAttribute("user", oldUser);
                    try {
                        UserRepository.updateAvatar(userID, filename);
                    } catch (SQLException ex) {
                        Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    // Handle the case where one or both uploads fail
                    Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.WARNING, "Failed to upload file to one or both locations: " + filename);
                }
            }   
        }
        response.sendRedirect("profile");
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
