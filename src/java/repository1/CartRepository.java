package repository1;

import config.DBConnect;
import entity.Cart;
import entity.Items;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.MyRandom;

public class CartRepository {

 // Phương thức lưu giỏ hàng và1o cơ sở dữ liệu
     public static void saveCart(Cart cart , String userID) {
        try (Connection conn = DBConnect.getConnection()) {
           

            // Thêm các mục giỏ hàng mới
             String insertCartItem = "INSERT INTO tblCart_items (userId, productId, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertCartItem)) {
                for (Items item : cart.getCart()) {
                    ps.setString(1, userID);
                    ps.setString(2, item.getProduct().getProductId());
                    ps.setInt(3, item.getAmount());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCartItemQuantity(String userId, String productId, int quantity) {
        try (Connection conn = DBConnect.getConnection()) {
            String updateCartItemQuantity = "UPDATE tblCart_items SET quantity = ? WHERE userId = ? AND productId = ?";
            try (PreparedStatement ps = conn.prepareStatement(updateCartItemQuantity)) {
                ps.setInt(1, quantity);
                ps.setString(2, userId);
                ps.setString(3, productId);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean updateProductQuantity(String productId, int Amount) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            // Kết nối đến cơ sở dữ liệu
            connection = DBConnect.getConnection();
            
            // Chuẩn bị câu truy vấn SQL
            String query = "UPDATE tblProduct SET Amount = Amount - ? WHERE ProductID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, Amount);
            statement.setString(2, productId);
            
            // Thực thi truy vấn
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng kết nối, câu lệnh
            try {
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
    }
    public static void removeCartItemFromDatabase(String userId, String productId) {
    try (Connection conn = DBConnect.getConnection()) {
        String deleteCartItem = "DELETE FROM tblCart_items WHERE userId = ? AND productId = ?";
        try (PreparedStatement ps = conn.prepareStatement(deleteCartItem)) {
            ps.setString(1, userId);
            ps.setString(2, productId);
            ps.executeUpdate();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public static void removeAllCartItems(String userId) {
    try (Connection conn = DBConnect.getConnection()) {
        String deleteAllCartItems = "DELETE FROM tblCart_items WHERE userId = ?";
        try (PreparedStatement ps = conn.prepareStatement(deleteAllCartItems)) {
            ps.setString(1, userId);
            ps.executeUpdate();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    // Phương thức tải giỏ hàng từ cơ sở dữ liệu theo userId
    public static Cart loadCartByUserId(String userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);

        try (Connection conn = DBConnect.getConnection()) {
            String selectCartItems = "SELECT productId, quantity FROM tblCart_items WHERE userId = ?";
            try (PreparedStatement ps = conn.prepareStatement(selectCartItems)) {
                ps.setString(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String productId = rs.getString("productId");
                    int ammout = rs.getInt("quantity");
                    Product product = ProductRepository.getProductById(productId);
                    Items item = new Items(product, ammout);
                    cart.addItems(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cart;
    }
//    public static List<Product> getProductsByUserId(String userId) {
//        List<Product> products = new ArrayList<>();
//        String query = "SELECT p.ProductID, p.ProductName, p.ProductType, p.ProductPrice, p.ProductImage, " +
//                       "p.Amount, p.StatusProduct, p.CTVID, p.ProductOrigin, p.ProductDescription " +
//                       "FROM tblCart_items ci " +
//                       "JOIN tblProduct p ON ci.productId = p.ProductID " +
//                       "WHERE ci.userId = ?";
//
//        try (Connection con = DBConnect.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query)) {
//             
//            stmt.setString(1, userId);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    Product product = new Product();
//                    product.setProductId(rs.getString("ProductID"));
//                    product.setProductName(rs.getString("ProductName"));
//                    product.setProductType(rs.getString("ProductType"));
//                    product.setProductPrice(rs.getDouble("ProductPrice"));
//                    product.setImg(rs.getString("ProductImage"));
//                    product.setProductAmount(rs.getInt("Amount"));
//                    product.setStatus(rs.getInt("StatusProduct"));
//                    product.setCTVID(rs.getString("CTVID"));
//                    product.setOrigin(rs.getString("ProductOrigin"));
//                    product.setProductDescription(rs.getString("ProductDescription"));
//                    products.add(product);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error in method getProductsByUserId in CartRepository.java: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        return products;
//    }
    public static Items getItemByProductId(String productId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Items item = null;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT * FROM tblCart_items WHERE productId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, productId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int amount = rs.getInt("quantity");
                // Để lấy thông tin chi tiết về sản phẩm, bạn có thể gọi một hàm khác như getProductById(productId)
                Product product = ProductRepository.getProductById(productId);
                if (product != null) {
                    item = new Items(product, amount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
       
        }
        return item;
    }
    public static List<Items> getProductsByUserId(String userId) {
        List<Items> itemsList = new ArrayList<>();
        String query = "SELECT ci.productId, ci.quantity, p.ProductName, p.ProductPrice, p.ProductImage " +
                       "FROM tblCart_items ci " +
                       "JOIN tblProduct p ON ci.productId = p.ProductID " +
                       "WHERE ci.userId = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
             
            stmt.setString(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String productId = rs.getString("productId");
                    int quantity = rs.getInt("quantity");
                    String productName = rs.getString("ProductName");
                    double productPrice = rs.getDouble("ProductPrice");
                    String productImage = rs.getString("ProductImage");
                    
                    Product product = new Product(productId, productName, productPrice, productImage);
                    Items item = new Items(product, quantity);
                    itemsList.add(item);
                }
            }
        } catch (Exception e) {
            System.out.println("Error in method getProductsByUserId in CartRepository.java: " + e.getMessage());
            e.printStackTrace();
        }

        return itemsList;
    }
    public static String getBrandNameByProductId(String productId) {
        String brandName = null;
        String query = "SELECT b.BrandName " +
                       "FROM tblCart_items ci " +
                       "JOIN tblProduct p ON ci.productId = p.ProductID " +
                       "JOIN tblBrand b ON p.CTVID = b.CTVID " +
                       "WHERE ci.productId = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
             
            stmt.setString(1, productId);

            try (ResultSet results = stmt.executeQuery()) {
                if (results.next()) {
                    brandName = results.getString("BrandName");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in method getBrandNameByProductId in CartRepository.java: " + e.getMessage());
            e.printStackTrace();
        }

        return brandName;
    }
    
      public static String getCTVIdByProductId(String productId) {
        String CTVID = null;
        String query = "SELECT p.CTVID " +
                       "FROM tblCart_items ci " +
                       "JOIN tblProduct p ON ci.productId = p.ProductID " +
                       "WHERE ci.productId = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
             
            stmt.setString(1, productId);

            try (ResultSet results = stmt.executeQuery()) {
                if (results.next()) {
                    CTVID = results.getString("CTVID");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in method getCTVIdByProductId in CartRepository.java: " + e.getMessage());
            e.printStackTrace();
        }

        return CTVID;
    }
     
    

    
    public static void main(String[] args) {
//        String productId = "P9727"; // 
//        String CTVID = getBrandNameByProductId(productId);
//        System.out.println("CTVID for product ID " + productId + ": " + CTVID);
//String userId = "U9776"; // Replace with actual userId
//        List<Product> products = getProductsByUserId(userId);
//        for (Product product : products) {
//            System.out.println("Product ID: " + product.getProductId());
//            System.out.println("Product Name: " + product.getProductName());
//            // Print other product details as needed
//        }
String userId = "U9776"; // Replace with an actual user ID from your database

        List<Items> itemsList = CartRepository.getProductsByUserId(userId);

        for (Items item : itemsList) {
            System.out.println("Product ID: " + item.getProduct().getProductId());
            System.out.println("Product Name: " + item.getProduct().getProductName());
            System.out.println("Quantity: " + item.getAmount());
            System.out.println("Product Price: " + item.getProduct().getProductPrice());
            System.out.println("Product Image: " + item.getProduct().getImg());
            System.out.println();
        }
    }
    }

