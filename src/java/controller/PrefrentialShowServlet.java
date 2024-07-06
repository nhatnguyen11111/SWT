package controller;

import entity.Preferential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import repository1.PreferentialRepository;

@WebServlet(name = "PrefrentialShowServlet", urlPatterns = "/preferential")
public class PrefrentialShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        // Get the full list of preferentials
        ArrayList<Preferential> listPreferential = PreferentialRepository.getListPreferential();

        // Pass the full list to the JSP
        request.setAttribute("preferentialList", listPreferential);
        request.getRequestDispatcher("/preferential.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests if needed
    }
}
