package filter;

import entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "CTVFilter",urlPatterns = {
    "/product-list-manager",
        "/product-add.jsp",
    "/order-list-manager",
    "/list-order-accepted",    "/order-list-paid",
    "/list-order-cancel",
    "/list-order-accepted",



})
public class CTVFilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo bộ lọc, nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
         if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null && user.getRole() == 4) {
                // User is an admin, allow access
                chain.doFilter(request, response);
                return;
            }
        }
        httpRequest.setAttribute("errorMessage", "Bạn không có quyền truy cập tác vụ này, vui lòng đăng nhập lại account ");

        // Người dùng không đăng nhập hoặc không phải quản trị viên, chuyển hướng đến trang đăng nhập
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
    }

    @Override
    public void destroy() {
        // Hủy bộ lọc, nếu cần
    }
}
