//package controller;
//
//import entity.Revenue;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import repository1.StatisticsRepository;
//
//import java.io.IOException;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
//@WebServlet(name = "StatisticsServlet", value = "/statistics")
//public class StatisticsServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int numberOfUsers = StatisticsRepository.getUserAmount();
//
//        int numberOfOrderToConfirm = StatisticsRepository.getNumberOfOrdersToConfirm();
//
//        int numberOfPetsLeft = StatisticsRepository.getNumberOfPetsLeft();
//
//        double orderRevenue=StatisticsRepository.getOrderRevenue();
//        double order1 = StatisticsRepository.getOrder1();
//        double order2 = StatisticsRepository.getOrder2();
//        double order3 = StatisticsRepository.getOrder3();
//        double order4 = StatisticsRepository.getOrder4();
//        double order5 = StatisticsRepository.getOrder5();
//        double order6 = StatisticsRepository.getOrder6();
//        double order7 = StatisticsRepository.getOrder7();
//        double order8 = StatisticsRepository.getOrder8();
//        double order9 = StatisticsRepository.getOrder9();
//        double order10 = StatisticsRepository.getOrder10();
//        double order11 = StatisticsRepository.getOrder11();
//        double order12 = StatisticsRepository.getOrder12();
//
//
//
//
//        double serviceRevenue=StatisticsRepository.getServiceRevenueByYear();
//
//
//
//
//        ArrayList<Revenue> listOderRevenues= StatisticsRepository.getOrderRevenueEachMonths();
//        ArrayList<Revenue> listServiceRevenues= StatisticsRepository.getServiceRevenueEachMonths();
//        DecimalFormat formatter = new DecimalFormat("#,###,###");
//        System.out.println(  formatter.format(orderRevenue));
////        ArrayList listFood=StatisticsRepository.getListRankOfFood();
//        request.setAttribute("order1",formatter.format(order1));
//        request.setAttribute("order2", formatter.format(order2));
//        request.setAttribute("order3",  formatter.format(order3));
//        request.setAttribute("order4",formatter.format(order4));
//        request.setAttribute("order5",formatter.format(order5));
//        request.setAttribute("order6", formatter.format(order6));
//        request.setAttribute("order7", formatter.format(order7));
//        request.setAttribute("order8", formatter.format(order8));
//        request.setAttribute("order9", formatter.format(order9));
//        request.setAttribute("order10", formatter.format(order10));
//        request.setAttribute("order11", formatter.format(order11));
//        request.setAttribute("order12", formatter.format(order12));
//        request.setAttribute("numberOfUsers", numberOfUsers);
//        request.setAttribute("numberOfOrderToConfirm", numberOfOrderToConfirm);
//        request.setAttribute("numberOfPetsLeft", numberOfPetsLeft);
//        request.setAttribute("orderRevenue",   formatter.format(orderRevenue));
////        request.setAttribute("listFood",listFood);
//        request.setAttribute("serviceRevenue",formatter.format(serviceRevenue));
//        request.setAttribute("listOderRevenues",listOderRevenues);
//        request.setAttribute("listServiceRevenues",listServiceRevenues);
//        request.getRequestDispatcher("statisticspage.jsp").forward(request,response);
//
//        //request.setAttribute();
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}
