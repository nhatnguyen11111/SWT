
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
import repository1.PreferentialRepository;

@WebServlet(name = "AddPreferentialServlet", urlPatterns = {"/addpreferential"})
@MultipartConfig // Bổ sung để hỗ trợ việc tải lên file
public class AddPreferentialServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddPreferentialServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("preferential-add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String preferentialCode = request.getParameter("preferentialCode");
        if (preferentialCode == null || preferentialCode.isEmpty()) {
            logger.warning("Preferential value is null or empty.");
            request.setAttribute("thongbao", "Vui lòng nhập mã giảm giá.");
            request.getRequestDispatcher("preferential-add.jsp").forward(request, response);
            return;
        }

        String preferentialName = request.getParameter("preferentialName");
        String startDay = request.getParameter("startDay");
        String endDay = request.getParameter("endDay");
        String quantityStr = request.getParameter("quantity");
        double rate = Double.parseDouble(request.getParameter("rate"));
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            logger.warning("Invalid quantity value: " + quantityStr);
            request.setAttribute("thongbao", "Giá trị số lượng không hợp lệ");
            request.getRequestDispatcher("preferential-add.jsp").forward(request, response);
            return;
        }

        String preferentialDescription = request.getParameter("preferentialDescription");
        Part part = request.getPart("preferentialImg");
        String filename = part.getSubmittedFileName();

        if (filename != null && !filename.isEmpty()) {
            String uploadPath = getServletContext().getRealPath("/") + "img" + File.separator;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String filePath = uploadPath + filename;
            try (InputStream is = part.getInputStream(); FileOutputStream fos = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                logger.info("Uploaded file successfully: " + filename);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error uploading file", e);
                request.setAttribute("thongbao", "Có lỗi xảy ra khi tải lên file: " + e.getMessage());
                request.getRequestDispatcher("preferential-add.jsp").forward(request, response);
                return;
            }
        }

        String preferentialImg = filename;
        User user = (User) session.getAttribute("user");
        String CTVID = user.getUserId();

        try {
            PreferentialRepository.addPreferential(preferentialCode, preferentialName, startDay, endDay, quantity, rate, preferentialDescription, preferentialImg, CTVID);
            request.setAttribute("thongbao", "Thêm thành công");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "SQL Error", ex);
            request.setAttribute("thongbao", "Có lỗi xảy ra khi thêm ưu đãi: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "Class Not Found Error", ex);
            request.setAttribute("thongbao", "Có lỗi xảy ra khi thêm ưu đãi: " + ex.getMessage());
        }

        request.getRequestDispatcher("preferential-add.jsp").forward(request, response);
    }
}
