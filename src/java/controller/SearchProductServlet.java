import entity.Product;
import repository1.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchProductServlet", value = "/SearchControl")
public class SearchProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        // Lấy từ khóa tìm kiếm và thông số phân trang từ yêu cầu
        String txtSearch = request.getParameter("txtSearch");
        ProductRepository productRepository = new ProductRepository();
        int count = productRepository.count(txtSearch);
        String indexString = request.getParameter("index");
        int index = Integer.parseInt(indexString);
        int pageSize = 10;  
        int endPage = 0;
        endPage = count/pageSize;
        if(count % pageSize != 0){
            endPage++;
        }
        
        List<Product> productList = productRepository.search(txtSearch, index, pageSize);

        // Đặt kết quả tìm kiếm vào request
        request.setAttribute("productList", productList);
        request.setAttribute("endPage", endPage);

        // Chuyển hướng đến trang productservlet.jsp
        request.getRequestDispatcher("searchProduct.jsp").forward(request, response);
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
