package controller;

import entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String fromId = user.getUserId();
        request.setAttribute("fromId", fromId);
        String CTVID = request.getParameter("CTVID");

        if (CTVID != null) {
            request.setAttribute("CTVID", CTVID);
            request.getRequestDispatcher("chat.jsp").forward(request, response);
        } else {
            response.getWriter().println("Missing parameter: CTVID");
        }
    }
}
