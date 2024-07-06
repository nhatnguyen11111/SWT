package controller;

import entity.Cart;
import entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import repository1.AuthenticationRepository;
import repository1.UserRepository;

import java.io.IOException;
import repository1.CartRepository;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Lấy session hiện tại nếu có, không tạo mới
        User user = (User) session.getAttribute("user");
        
        // Nếu người dùng đã đăng nhập, chuyển hướng đến trang chủ
        if (user != null) {
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = UserRepository.MaHoa(password);
        System.out.println(username + " " + password);
        User user = (User) AuthenticationRepository.Verify(username, password);
        System.out.println(user);

        if (user != null) {
            if (AuthenticationRepository.getStatusAcc(username) == 0) {
                request.setAttribute("thongbao", "Tài khoản hiện bị khóa vui lòng liên hệ với Admin");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                Cookie userCookies = new Cookie("username", username);
                Cookie passCookies = new Cookie("password", password);
                userCookies.setMaxAge(60 * 60 * 24);
                passCookies.setMaxAge(60 * 60 * 24);
                response.addCookie(userCookies);
                response.addCookie(passCookies);
                 Cart cart = CartRepository.loadCartByUserId(user.getUserId());
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("cart", cart);
                System.out.println("Đăng nhập thành công");

                // Chuyển hướng đến trang admin.jsp nếu tên người dùng là "Admin"
                if ("Admin".equalsIgnoreCase(username)) {
                    response.sendRedirect("admin");
                } else {
                    response.sendRedirect("index.jsp");
                }
            }
        } else {
            request.setAttribute("thongbao", "Thông tin đăng nhập không chính xác");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
