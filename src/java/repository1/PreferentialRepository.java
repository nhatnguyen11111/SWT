    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository1;

import config.DBConnect;
import entity.Preferential;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */


public class PreferentialRepository {

   public static void addPreferential(String Preferential, String PreferentialName, String StartDay, String EndDay, int Quantity, double Rate, String PreferentiaDescription, String PreferentiaImg,String EmployeeID) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO tblPreferential ( Preferential,  PreferentialName,  StartDay,  EndDay,  Quantity, Rate,  PreferentiaDescription,  PreferentiaImg, EmployeeID) " +
                 "VALUES (?, ?, ?, ?, ?,?, ?, ?,?)";
    
    try (Connection con = DBConnect.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setString(1, Preferential);
        stmt.setString(2, PreferentialName);
        stmt.setString(3, StartDay);
        stmt.setString(4, EndDay);
        stmt.setInt(5, Quantity);
        stmt.setDouble(6, Rate/100);

        stmt.setString(7, PreferentiaDescription);
        stmt.setString(8,PreferentiaImg); 
        stmt.setString(9,EmployeeID);

        stmt.executeUpdate();
        System.out.println("Preferential added successfully.");
        
    } catch (SQLException e) {
        System.out.println("Error in addPreferential");
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        System.out.println("Error in addPreferential");
        e.printStackTrace();
        throw e; // Ném lại ngoại lệ để servlet có thể bắt và xử lý
    }
}

     public static ArrayList<Preferential> getListPreferentialByEmployeeID(String EmployeeID) {
        ArrayList<Preferential> listPreferential = new ArrayList<>();
        try {
            String query = "SELECT * FROM tblPreferential WHERE EmployeeID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, EmployeeID);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                String preferential = results.getString(1);
                String preferentialName = results.getString(2);
                String startDay = results.getString(3);
                String endDay = results.getString(4);
                int quantity = results.getInt(5);
                String preferentiaDescription = results.getString(6);
                String preferentiaImg = results.getString(7);
                Preferential preferentials = new Preferential(preferential, preferentialName, startDay, endDay, quantity, preferentiaDescription, preferentiaImg, EmployeeID);
                listPreferential.add(preferentials);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách sản phẩm theo EmployeeID: " + e.getMessage());
        }
        return listPreferential;
    }
    public static ArrayList<Preferential> getListPreferentialPage(String page) {
        ArrayList<Preferential> listPreferential = new ArrayList<Preferential>();
        try {
            String query = "select * from tblPreferential\n" +
                    "order by Preferential\n" +
                    "OFFSET (? - 1) * 9 ROWS\n" +
                    "FETCH NEXT 9 ROWS ONLY;";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,page);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
               String preferential = results.getString(1);
                String preferentialName = results.getString(2);
                String startDay = results.getString(3);
                String endDay = results.getString(4);
                int quantity = results.getInt(5);
                String preferentiaDescription = results.getString(6);
                String preferentiaImg = results.getString(7);
                String EmployeeID = results.getString(8);
   
                              Preferential preferentials = new Preferential( preferential,  preferentialName,  startDay,  endDay,  quantity,  preferentiaDescription,  preferentiaImg,  EmployeeID);

                listPreferential.add(preferentials);

            }
        } catch (Exception e) {
            System.err.println("Loi database method listFood class ProductRepository");
        }
        return listPreferential;
    } 

public  static int gettPreferentialSize(){
        int size=0;
        try {
            String query = "select COUNT(1) from tblPreferential";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);

            ResultSet results = stmt.executeQuery();
            while (results.next()) {
               size=results.getInt(1);

            }
        } catch (Exception e) {
            System.err.println("Loi database method listFood class ProductRepository");
        }
        return size;
    }
 public static double getDiscountPercent(String discountID) {
        double quantity = 0f;
        try {
            Connection con = DBConnect.getConnection();
            String query = "select Rate from tblPreferential where Preferential =?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, discountID);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                quantity = results.getDouble(1);
                System.out.println("=>>>>>>>>>>>>>>>>>>.." + quantity);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : getDiscountPercent()<=============");
        }
        return quantity;
    }

    public static String getDiscountCodeByOrderID(String orderid) {
        String discountCode = null;
        try {
            Connection con = DBConnect.getConnection();
            String query = "select PreferentialID from tblBill where BillID =?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, orderid);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                discountCode = results.getString(1);
                System.out.println("=>>>>>>>>>>>>>>>>>>.." + discountCode);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : getDiscountCodeByOrderID()<=============");
        }
        return discountCode;
    }

 public static ArrayList<Preferential> getListPreferential() {
        ArrayList<Preferential> listPreferential = new ArrayList<>();
        try {
            String query = "select * from tblPreferential";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                String preferential = results.getString(1);
                String preferentialName = results.getString(2);
                String startDay = results.getString(3);
                String endDay = results.getString(4);
                int quantity = results.getInt(5);
                String preferentiaDescription = results.getString(6);
                String preferentiaImg = results.getString(7);
                String EmployeeID = results.getString(8);
                Preferential preferentials = new Preferential( preferential,  preferentialName,  startDay,  endDay,  quantity,  preferentiaDescription,  preferentiaImg,  EmployeeID);
                listPreferential.add(preferentials);

            }
        } catch (Exception e) {
            System.err.println("Loi database method listPreferential class Preferential");
        }
        return listPreferential;
    }
 
public static Preferential checkPreferential(String discountID) throws ClassNotFoundException {
        Preferential preferential = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DBConnect.getConnection(); // Lấy kết nối đến cơ sở dữ liệu
            String query = "SELECT Preferential, PreferentialName, StartDay, EndDay, Quantity, Rate, PreferentiaDescription, PreferentiaImg, EmployeeID " +
                           "FROM tblPreferential " +
                           "WHERE Preferential = ? AND StartDay <= CURRENT_TIMESTAMP AND EndDay >= CURRENT_TIMESTAMP AND Quantity > 0";
            stmt = con.prepareStatement(query);
            stmt.setString(1, discountID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String preferentialId = rs.getString("Preferential");
                String preferentialName = rs.getString("PreferentialName");
                String startDay = rs.getString("StartDay");
                String endDay = rs.getString("EndDay");
                int quantity = rs.getInt("Quantity");
                double rate = rs.getDouble("Rate");
                String preferentiaDescription = rs.getString("PreferentiaDescription");
                String preferentiaImg = rs.getString("PreferentiaImg");
                String employeeID = rs.getString("EmployeeID");

                preferential = new Preferential(preferentialId, preferentialName, startDay, endDay, quantity, rate, preferentiaDescription, preferentiaImg, employeeID);
            }
        } catch (SQLException e) {
            System.err.println("Error checking Preferential: " + e.getMessage());
        } finally {
            // Đóng các đối tượng ResultSet, PreparedStatement và Connection
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }

        return preferential;
    }

 public static void decreasePreferentialQuantity(String preferential) {
    try {
        String query = "UPDATE tblPreferential SET Quantity = Quantity - 1 WHERE Preferential = ?";
        Connection con = DBConnect.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, preferential);
        stmt.executeUpdate();
        con.close();
    } catch (Exception e) {
        System.err.println("Error decreasing Quantity for Preferential " + preferential + ": " + e.getMessage());
    }
}
 
   public static void main(String[] args) {
//    try {
//        String preferential = "PREF00121";
//        String preferentialName = "Discount 10%";
//        String startDay = "2022-01-01";
//        String endDay = "2022-12-31";
//        int quantity = 100;
//        String description = "10% off on all products";
//        String imgPath = "image.jpg";
//        String EmployeeID = "E0622";
//
//        // Gọi hàm addPreferential để kiểm thử
//        addPreferential(preferential, preferentialName, startDay, endDay, quantity, description, imgPath, EmployeeID);
//
//        // Kiểm tra xem liệu dữ liệu có được thêm thành công hay không
//        System.out.println("Preferential added successfully.");
//    } catch (SQLException | ClassNotFoundException ex) {
//        System.err.println("Error occurred: " + ex.getMessage());
//        ex.printStackTrace();
//    }
//        String discountID = "aaaaa"; // Thay thế bằng ID thực tế
//        double discountPercent = getDiscountPercent(discountID);
//        System.out.println("Discount Percent: " + discountPercent);
//   
String discountID = "aaaa"; // Giả sử discountID bạn muốn kiểm tra là "PREF001"

        try {
            Preferential preferential = checkPreferential(discountID);
            if (preferential != null) {
                System.out.println("Preferential found:");
                System.out.println("ID: " + preferential.getPreferential());
                System.out.println("Name: " + preferential.getPreferentialName());
                System.out.println("Start Day: " + preferential.getStartDay());
                System.out.println("End Day: " + preferential.getEndDay());
                System.out.println("Quantity: " + preferential.getQuantity());
                System.out.println("Rate: " + preferential.getRate());
                System.out.println("Description: " + preferential.getPreferentiaDescription());
                System.out.println("Image: " + preferential.getPreferentiaImg());
                System.out.println("Employee ID: " + preferential.getEmployeeID());
            } else {
                System.out.println("Preferential not found or cannot be applied.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Class not found - " + e.getMessage());
        }
    }

   }



