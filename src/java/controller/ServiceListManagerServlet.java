
package controller;

import entity.Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import repository1.ServiceRespository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ServiceListManagetServlet", urlPatterns = {"/ServiceList"})
public class ServiceListManagerServlet extends HttpServlet {
            @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       ArrayList<Service> list = ServiceRespository.getALLService();
        request.setAttribute("listService",list);
        request.getRequestDispatcher("service-list-manager.jsp").forward(request,response);
    }

    }


