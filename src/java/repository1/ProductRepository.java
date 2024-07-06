package repository1;

import config.DBConnect;
import entity.Brand;
import entity.CommentProduct;
import entity.Items;
import entity.Product;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;

public class ProductRepository {

    public static void addProduct(String productID, String productName, String productType, String productOrigin, double productPrice, int productAmount, String productImg, String CTVID, String productDescription) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tblProduct (ProductID, ProductName, ProductType, ProductPrice, ProductImage, Amount, StatusProduct, CTVID, ProductOrigin,ProductDescription,sold) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

        try (Connection con = DBConnect.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, productID);
            stmt.setString(2, productName);
            stmt.setString(3, productType);
            stmt.setDouble(4, productPrice);
            stmt.setString(5, productImg);
            stmt.setInt(6, productAmount);
            stmt.setInt(7, 1); // Assuming StatusProduct is set to 1 as default
            stmt.setString(8, CTVID);
            stmt.setString(9, productOrigin);
            stmt.setString(10, productDescription);
            stmt.setInt(11, 0);

            stmt.executeUpdate();
            System.out.println("Product added successfully.");

        } catch (SQLException e) {
            System.out.println("Error in addProduct");
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> getListProductByCTVID(String CTVID) {
        ArrayList<Product> listProduct = new ArrayList<>();
        try {
            // Tạo truy vấn SQL để lấy danh sách sản phẩm dựa trên CTVID
            String query = "SELECT * FROM tblProduct WHERE CTVID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, CTVID);
            ResultSet results = stmt.executeQuery();

            // Duyệt qua kết quả trả về và thêm sản phẩm vào danh sách
            while (results.next()) {
                String productId = results.getString(1);
                String productName = results.getString(2);
                String productType = results.getString(3);
                double productPrice = results.getDouble(4);
                String ctvId = results.getString(5);
                int productAmount = results.getInt(6);
                int productStatus = results.getInt(7);
                String productImg = results.getString(8);
                String origin = results.getString(9);
                String productDescription = results.getString(10);
                int sold = results.getInt(12);

                // Tạo đối tượng Product và thêm vào danh sách
                Product product = new Product(productId, productName, productType, origin, productPrice, productAmount, ctvId, productStatus, productImg, productDescription, sold);
                listProduct.add(product);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra
            System.err.println("Lỗi khi lấy danh sách sản phẩm theo CTVID: " + e.getMessage());
        }
        return listProduct;
    }

    public static ArrayList<Product> getListProduct() {
        ArrayList<Product> listProduct = new ArrayList<Product>();
        try {
            String query = "select * from tblProduct";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                String productId = results.getString(1);
                String productName = results.getString(2);
                String productType = results.getString(3);
                double productPrice = results.getDouble(4);
                String productImg = results.getString(5);
                int productAmount = results.getInt(6);
                int productStatus = results.getInt(7);
                String CTVID = results.getString(8);
                String ProductOrigin = results.getString(9);
                String productDescription = results.getString(10);
                int sold = results.getInt(12);
                Product product = new Product(productId, productName, productType, ProductOrigin, productPrice, productAmount, productImg, productStatus, CTVID, productDescription, sold);
                listProduct.add(product);
            }
        } catch (Exception e) {
            System.err.println("Loi database method listPet class ProductRepository");
        }
        return listProduct;
    }

    public static boolean updateProductSold(String productId, int quantity) {
        Connection con = null;
        PreparedStatement updateStmt = null;

        try {
            con = DBConnect.getConnection();

            // Cập nhật giá trị 'sold' của sản phẩm
            String updateQuery = "UPDATE tblProduct SET sold = sold + ? WHERE ProductID = ?";
            updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setInt(1, quantity);
            updateStmt.setString(2, productId);

            int rowsUpdated = updateStmt.executeUpdate();

            // Kiểm tra số dòng được cập nhật
            return rowsUpdated > 0;

        } catch (Exception e) {
            System.out.println("==========> ERROR: updateProductSold() <=============");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (updateStmt != null) {
                    updateStmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean updateProductSoldByCancel(String productId, int quantity) {
        Connection con = null;
        PreparedStatement updateStmt = null;

        try {
            con = DBConnect.getConnection();

            // Cập nhật giá trị 'sold' của sản phẩm
            String updateQuery = "UPDATE tblProduct SET sold = sold - ? WHERE productId = ?";
            updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setInt(1, quantity);
            updateStmt.setString(2, productId);

            int rowsUpdated = updateStmt.executeUpdate();

            // Kiểm tra số dòng được cập nhật
            return rowsUpdated > 0;

        } catch (Exception e) {
            System.out.println("==========> ERROR: updateProductSoldByCancel() <=============");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (updateStmt != null) {
                    updateStmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateProductByCancelOrder(String orderId) {
        Connection con = null;
        PreparedStatement selectStmt = null;

        try {
            con = DBConnect.getConnection();

            // Lấy danh sách sản phẩm và số lượng tương ứng từ đơn hàng bị hủy
            String selectQuery = "SELECT ProductID, AmountProduct FROM tblOrderDetails WHERE BillID = ?";
            selectStmt = con.prepareStatement(selectQuery);
            selectStmt.setString(1, orderId);

            ResultSet rs = selectStmt.executeQuery();

            while (rs.next()) {
                String productId = rs.getString("ProductID");
                int quantity = rs.getInt("AmountProduct");

                // Giảm giá trị 'sold' của sản phẩm
                ProductRepository.updateProductSoldByCancel(productId, quantity);
            }

        } catch (Exception e) {
            System.out.println("==========> ERROR: updateProductByCancelOrder() <=============");
            e.printStackTrace();
        } finally {
            try {
                if (selectStmt != null) {
                    selectStmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkExistProductID(String productID) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT ProductID FROM tblProduct WHERE ProductID = ?");
            stmt.setString(1, productID);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            System.out.println("Error in checkExistProductID(String productID)");
            e.printStackTrace();
        }
        return false;
    }

    public static Product getProductById(String productId) {
        Product product = null;
        try {
            String query = "SELECT * FROM tblProduct WHERE productId = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, productId);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                String productName = results.getString(2);
                String productType = results.getString(3);
                double productPrice = results.getDouble(4);
                String productImg = results.getString(5);
                int productAmount = results.getInt(6);
                int productStatus = results.getInt(7);
                String CTVID = results.getString(8);
                String productOrigin = results.getString(9);
                String productDescription = results.getString(10);
                int sold = results.getInt(12);
                product = new Product(productId, productName, productType, productOrigin, productPrice, productAmount, productImg, productStatus, CTVID, productDescription, sold);
            }
        } catch (Exception e) {
            System.err.println("Error in database method getProductById class ProductRepository: " + e.getMessage());
        }
        return product;
    }

    public static Brand getBrandByCTVId(String CTVID) {
        Brand brand = null;
        try {
            String query = "SELECT * FROM tblBrand WHERE CTVID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, CTVID); // Thay đổi tham số từ 6 thành 1
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String brandId = rs.getString("BrandID");
                String brandName = rs.getString("BrandName");
                String brandDescription = rs.getString("BrandDescription");
                String brandLogo = rs.getString("BrandLogo");
                String brandAddress = rs.getString("BrandAddress");
                String brandCTVID = rs.getString("CTVID");
                int status = rs.getInt("Status");

                brand = new Brand(brandId, brandName, brandDescription, brandLogo, brandAddress, brandCTVID, status);
            }
        } catch (Exception e) {
            System.err.println("Error in database method getBrandById class ProductRepository: " + e.getMessage());
        }
        return brand;
    }
    private static final Logger logger = Logger.getLogger(UserRepository.class.getName());

    public static boolean addComment1(String commentId, String ProductID, String BillID, String Comment, String UserID, String commentImg, int Rating) throws SQLException, ClassNotFoundException {
        boolean result = false;
        String sql = "INSERT INTO tblProductComment (CommentID, ProductID,BillID, Comment, UserID, Rating, CommentImg, Created_at) "
                + "VALUES (?, ?,?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, commentId);
            ps.setString(2, ProductID);
            ps.setString(3, Comment);
            ps.setString(4, BillID);

            ps.setString(5, UserID);
            ps.setInt(6, Rating);
            ps.setString(7, commentImg);
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            ps.setTimestamp(8, currentTimestamp);

            logger.info("Executing query: " + ps.toString());

            result = ps.executeUpdate() > 0;
            if (result) {
                logger.info("Brand added successfully.");
            } else {
                logger.warning("Failed to add brand.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding brand", e);
        }
        return result;
    }

    public static double getAverageRatingForProduct(String productId) throws SQLException, ClassNotFoundException {
        int totalRating = 0;
        int totalNumberOfRatings = 0;
        double averageRating = 0.0;

        String totalRatingQuery = "SELECT SUM(Rating) AS TotalRating FROM tblProductComment WHERE ProductID = ?";
        String totalNumberOfRatingsQuery = "SELECT COUNT(*) AS TotalNumberOfRatings FROM tblProductComment WHERE ProductID = ?";

        try (Connection con = DBConnect.getConnection()) {
            try (PreparedStatement psTotalRating = con.prepareStatement(totalRatingQuery);
                    PreparedStatement psTotalNumberOfRatings = con.prepareStatement(totalNumberOfRatingsQuery)) {

                psTotalRating.setString(1, productId);
                psTotalNumberOfRatings.setString(1, productId);

                try (ResultSet rsTotalRating = psTotalRating.executeQuery();
                        ResultSet rsTotalNumberOfRatings = psTotalNumberOfRatings.executeQuery()) {

                    if (rsTotalRating.next()) {
                        totalRating = rsTotalRating.getInt("TotalRating");
                    }

                    if (rsTotalNumberOfRatings.next()) {
                        totalNumberOfRatings = rsTotalNumberOfRatings.getInt("TotalNumberOfRatings");
                    }
                }
            }

            if (totalNumberOfRatings != 0) {
                averageRating = (double) totalRating / totalNumberOfRatings;
            }
        } catch (SQLException e) {
            System.err.println("Error in getAverageRatingForProduct method: " + e.getMessage());
            e.printStackTrace();
        }

        return averageRating;
    }

    public static int getTotalRatingForProduct(String productId) throws SQLException, ClassNotFoundException {
        int totalRating = 0;
        String query = "SELECT SUM(Rating) AS TotalRating FROM tblProductComment WHERE ProductID = ?";

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalRating = rs.getInt("TotalRating");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in getTotalRatingForProduct method: " + e.getMessage());
            e.printStackTrace();
        }

        return totalRating;
    }

    public static int getTotalCommentsForProduct(String productId) throws SQLException, ClassNotFoundException {
        int totalComments = 0;
        String query = "SELECT COUNT(*) AS TotalComments FROM tblProductComment WHERE ProductID = ?";

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalComments = rs.getInt("TotalComments");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error in getTotalCommentsForProduct method: " + e.getMessage());
            e.printStackTrace();
        }

        return totalComments;
    }

    // Method to list all comments for a specific product
    public static List<CommentProduct> listCommentsByProductId(String productId) throws SQLException, ClassNotFoundException {
        List<CommentProduct> comments = new ArrayList<>();
        String sql = "SELECT * FROM tblProductComment WHERE ProductID = ?";
        try (Connection conn = DBConnect.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String commentId = rs.getString("CommentID");
                String productID = rs.getString("ProductID"); // Thêm dòng này để lấy ID của sản phẩm từ cơ sở dữ liệu
                String comment = rs.getString("Comment");
                String userId = rs.getString("UserID");
                int rating = rs.getInt("rating");
                String commentImg = rs.getString("CommentImg");
                Timestamp createdAt = rs.getTimestamp("Created_at"); // Sử dụng getTimestamp để lấy kiểu Timestamp
                CommentProduct commentProduct = new CommentProduct(commentId, productID, comment, userId, rating, commentImg, createdAt);
                comments.add(commentProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static boolean isCommentExists(String productId, String orderId, String userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM tblProductComment WHERE ProductID = ? AND BillID = ? AND UserID = ?";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, productId);
            ps.setString(2, orderId);
            ps.setString(3, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public static boolean allProductsCommented(String orderId, List<Items> orderItems, String userId) throws SQLException, ClassNotFoundException {
        for (Items item : orderItems) {
            boolean commentExists = isCommentExists(item.getProduct().getProductId(), orderId, userId);
            if (!commentExists) {
                return false;
            }
        }
        return true;
    }

    public int count(String txtSearch) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            // SQL query to count products
            String query = "SELECT COUNT(*) FROM tblProduct WHERE ProductName LIKE ?";
            // Get database connection
            connection = DBConnect.getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query);
            // Set the search parameter
            statement.setString(1, "%" + txtSearch + "%");
            // Execute the query
            resultSet = statement.executeQuery();
            // Get the count result
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public List<Product> search(String txtSearch, int index, int size) {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnect.getConnection();
            String query = "with x as (select ROW_NUMBER () over (order by ProductName) as r, * from tblProduct where ProductName like ?) "
                    + "select * from x where r between (? * 3 - 2) and (? * 3)";
            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + txtSearch + "%");
            statement.setInt(2, index);
            statement.setInt(3, size);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("ProductID"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setProductType(resultSet.getString("ProductType"));
                product.setProductPrice(resultSet.getDouble("ProductPrice"));
                product.setImg(resultSet.getString("ProductImage"));
                product.setProductAmount(resultSet.getInt("Amount"));
                product.setStatus(resultSet.getInt("StatusProduct"));
                product.setCTVID(resultSet.getString("CTVID"));
                product.setOrigin(resultSet.getString("ProductOrigin"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
                productList.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

    public static Product getProductInfor(String id) {
        Product product = null;
        try {
            String query = "SELECT * FROM tblProduct WHERE ProductID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getString("ProductID"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setProductType(resultSet.getString("ProductType"));
                product.setProductPrice(resultSet.getDouble("ProductPrice"));
                product.setImg(resultSet.getString("ProductImage"));
                product.setProductAmount(resultSet.getInt("Amount"));
                product.setStatus(resultSet.getInt("StatusProduct"));
                product.setCTVID(resultSet.getString("CTVID"));
                product.setOrigin(resultSet.getString("ProductOrigin"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
            }

            resultSet.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Loi method getProdInfor() trong ProductRepository.java: ");
            e.printStackTrace();
        }
        return product;
    }
    
    public static boolean UpdateProduct(Product product) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement("update  tblProduct set ProductName=?\n" +
                    "                 ,ProductType=?\n" +
                    "                 ,ProductOrigin=?\n" +
                    "                 ,ProductPrice=?\n" +
                    "                 ,Amount=?\n" +
                    "                 ,StatusProduct=?\n" +
                    "                 ,ProductDescription=?\n" +
                    "                 ,ProductImage=?\n" +
                    "                 where ProductID= ?");
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getProductType());
            stmt.setString(3, product.getOrigin());
            stmt.setDouble(4, product.getProductPrice());
            stmt.setInt(5, product.getProductAmount());
            stmt.setInt(6, product.getStatus());
            stmt.setString(7, product.getProductDescription());
            stmt.setString(8, product.getImg());
            stmt.setString(9,product.getProductId());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("loi addFood(Product product)");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

//        String productID = "P004";
//        String productName = "Sample Product";
//        String productType = "Electronics";
//        String productOrigin = "USA";
//        double productPrice = 299.99;
//        int productAmount = 50;
//        String productImg = "sample_image.jpg";
//        String CTVID = "C3117";
//        String mota = "abc";
//
//        // Check if the product already exists
//        if (checkExistProductID(productID)) {
//            System.out.println("Product with ID " + productID + " already exists.");
//        } else {
//            // Attempt to add the product
//            addProduct(productID, productName, productType, productOrigin, productPrice, productAmount, productImg, CTVID,mota);
//        }
//
//        ArrayList<Product> productList = ProductRepository.getListProduct();
//
//        // In ra danh sách sản phẩm
//        System.out.println("Danh sách sản phẩm:");
//        for (Product product : productList) {
//            System.out.println(product);
//        }
//        String CTVID = "C3117"; 
//        Brand brand = ProductRepository.getBrandByCTVId(CTVID);
//        if (brand != null) {
//            System.out.println(brand);
//        } else {
//            System.out.println("Brand not found!");
//        }
//        CommentProduct comment = new CommentProduct();
//        comment.setCommentID("Cm124");
//        comment.setProductID("P9003");
//        comment.setComment("This is a test comment.");
//        comment.setUserID("U8516");
//        comment.setCommentImg("image.jpg");
//        comment.setCreatedAt(new Date(System.currentTimeMillis()));
//
//        // Gọi phương thức addComment từ ProductRepository
//        ProductRepository.addComment(comment);
        // In ra thông báo khi thêm bình luận thành công
//        System.out.println("Comment added successfully.");
//try {
//        // Gọi phương thức listCommentsByProductId từ ProductRepository
//        List<CommentProduct> comments = ProductRepository.listCommentsByProductId("P9003");
//
//        // In ra danh sách các bình luận
//        for (CommentProduct comment : comments) {
//            System.out.println(comment);
//        }
//    } catch (SQLException | ClassNotFoundException e) {
//        e.printStackTrace();
//    }
//
//         ProductRepository productRepository = new ProductRepository();
//        
//        // Thay thế "txtSearch" bằng từ khóa tìm kiếm thực tế của bạn
//        String txtSearch = "";
//        int index = 1; // Trang đầu tiên
//        int size = 10; // Số lượng sản phẩm mỗi trang
//
//        List<Product> products = productRepository.search(txtSearch, index, size);
//        
//        // In kết quả ra màn hình
//        for (Product product : products) {
//            System.out.println("ProductID: " + product.getProductId());
//            System.out.println("ProductName: " + product.getProductName());
//            System.out.println("ProductType: " + product.getProductType());
//            System.out.println("ProductPrice: " + product.getProductPrice());
//            System.out.println("ProductImage: " + product.getImg());
//            System.out.println("Amount: " + product.getProductAmount());
//            System.out.println("StatusProduct: " + product.getStatus());
//            System.out.println("CTVID: " + product.getCTVID());
//            System.out.println("ProductOrigin: " + product.getOrigin());
//            System.out.println("ProductDescription: " + product.getProductDescription());
//            System.out.println("----------------------------------------");
//        }
//String productId = "P4238";
//    List<CommentProduct> productList = ProductRepository.listCommentsByProductId(productId);
////
////        // In ra danh sách sản phẩm
//        System.out.println("Danh sách sản phẩm:");
//        for (CommentProduct commentProduct : productList) {
//            System.out.println(commentProduct);
//        }
//    
//
//String productId = "P2716"; // Giả sử đây là ID sản phẩm của bạn
//        int quantity = 5; // Giả sử đây là số lượng bạn muốn cập nhật
//
//        boolean result = ProductRepository.updateProductSold(productId, quantity);
//
//        if (result) {
//            System.out.println("Cập nhật số lượng bán thành công cho sản phẩm ID: " + productId);
//        } else {
//            System.out.println("Cập nhật số lượng bán thất bại cho sản phẩm ID: " + productId);
//        }
// String productId = "P1091"; // Thay đổi ID sản phẩm theo nhu cầu của bạn
//        Product product = ProductRepository.getProductById(productId);
//        
//        if (product != null) {
//            System.out.println("Product found: " + product);
//        } else {
//            System.out.println("Product not found for ID: " + productId);
//        }
//    }
//        String productId = "P4238"; // Thay đổi ID sản phẩm theo nhu cầu của bạn
//        try {
//            int totalRating = getTotalRatingForProduct(productId);
//            System.out.println("Total rating for product ID " + productId + ": " + totalRating);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String productId = "P2716"; // Thay bằng ID sản phẩm bạn muốn kiểm tra
        Product product = getProductInfor(productId);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID: " + productId);
        }
//        Product product = new Product();
//        product.setProductId("P9727");
//        product.setProductName("Updated Product Name");
//        product.setProductType("Đồ chơi trẻ em");
//        product.setOrigin("Vietnam");
//        product.setProductPrice(100.0);
//        product.setStatus(1);
//        product.setImg("updated_image.jpg");
//
//        // Call the update method
//        boolean result = ProductRepository.UpdateProduct(product);
//
//        // Check the result
//        if (result) {
//            System.out.println("Product updated successfully.");
//        } else {
//            System.out.println("Failed to update product.");
//        }
    

    }
}
