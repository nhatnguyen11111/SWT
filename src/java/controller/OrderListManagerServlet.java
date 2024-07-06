package controller;

import entity.OrderAccept;
import entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import repository1.OrderRepository;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "OrderListManagerServlet", value = "/order-list-manager")
public class OrderListManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String CTVID = user.getUserId();
        ArrayList<OrderAccept> listOrder= OrderRepository.getAllOrderByCTVId(CTVID);
        String thongbao;
        try{
            if(!request.getParameter("thongbao").equals(null)){
                if(request.getParameter("thongbao").equals("1")){
                    thongbao="Xác nhận đơn hàng thành công";
                }
                else {
                    thongbao = "Hủy đơn hàng thành công";
                }
                request.setAttribute("thongbao",thongbao);
            }
        }
        catch (Exception e){

        }

        request.setAttribute("listOrder",listOrder);

        request.getRequestDispatcher("order-list-manager.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
