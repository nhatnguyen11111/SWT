package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import repository1.UserRepository;

import java.io.IOException;

@WebServlet(name = "ResendCodeServlet", value = "/ResendCodeServlet")
public class ResendCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        boolean isCodeResent = UserRepository.resendVerificationCode(userID);

        if (isCodeResent) {
            request.setAttribute("thongbao", "Mã xác nhận mới đã được gửi lại.");
        } else {
            request.setAttribute("thongbao", "Có lỗi xảy ra. Vui lòng thử lại.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("verificode.jsp");
        dispatcher.forward(request, response);
    }
}
