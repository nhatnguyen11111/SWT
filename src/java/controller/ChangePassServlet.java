
package controller;

import entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import repository1.UserRepository;

import java.io.IOException;

@WebServlet(name = "ChangePassServlet", value = "/changepass")
public class ChangePassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Check if there's a notification to display
        String notification = request.getParameter("thongbao2");
        if (notification != null) {
            if ("1".equals(notification)) {
                request.setAttribute("thongbao2", "Cập nhật mật khẩu thành công");
            } else {
                request.setAttribute("thongbao2", "Mật khẩu cũ không đúng vui lòng nhập lại");
            }
        }

        System.out.println("Lay thong tin cua" + user);
        String firstname = user.getFirstname(), lastname = user.getLastname(), address = user.getAddress(),avatar = user.getAvatar(), phone = user.getPhone();
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname", lastname);
        request.setAttribute("address", address);
        request.setAttribute("avatar", avatar);
        request.setAttribute("phone", phone);
        
        request.getRequestDispatcher("changepass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPass = request.getParameter("oldPassword");
        oldPass = UserRepository.MaHoa(oldPass);
        String newPass = request.getParameter("password");
        newPass = UserRepository.MaHoa(newPass);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userID = user.getUserId();
        int thongbao2 = 1;

        // Check old password and update if correct
        if (UserRepository.checkOldPass(userID, oldPass)) {
            UserRepository.changePass(userID, newPass);
        } else {
            thongbao2 = 0;
        }

        // Redirect to GET method with notification parameter
        response.sendRedirect("changepass?thongbao2=" + thongbao2);
    }
}