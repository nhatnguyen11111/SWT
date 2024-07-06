package repository1;

import config.DBConnect;
import entity.Account;
import entity.Brand;
import entity.User;
import java.security.MessageDigest;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.mail.PasswordAuthentication;
import org.apache.tomcat.util.codec.binary.Base64;
import service.MyRandom;

public class UserRepository {

    private static final Logger LOGGER = Logger.getLogger(UserRepository.class.getName());

    public static boolean checkUserNameExist(String username) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from tblCustomer  where UserName =?");
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            System.out.println("loi checkUserNameExsit(String userName)");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateUser(String userID, String firstname, String lastname, String address, String phone) {
        try {
            String query = "UPDATE tblAccount \n"
                    + "SET Firstname = ?, Lastname = ?, Address = ?, Phone= ? \n"
                    + "WHERE UserID = ?";

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, firstname);
            preSt.setString(2, lastname);
            preSt.setString(3, address);
            preSt.setString(5, phone);
            preSt.setString(6, userID);

            preSt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void updateAvatar(String userID, String avatar) throws SQLException, ClassNotFoundException {
        String query = "UPDATE tblAccount SET Avatar = ? WHERE UserID = ?";

        try (Connection conn = DBConnect.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, avatar);
            stmt.setString(2, userID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user avatar", e);
        }
    }

    public static boolean checkExistID(String userID) {
        try {
            String query = "SELECT UserID FROM tblAccount WHERE UserID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, userID);
            ResultSet rs = preSt.executeQuery();
            boolean checkID = rs.next();
            con.close();
            return checkID;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkExistUsername(String username) {
        try {
            String query = "select * from tblAccount where Username=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, username);
            ResultSet rs = preSt.executeQuery();
            boolean checkID = rs.next();
            con.close();
            return checkID;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkExistEmail(String email) {
        try {
            String query = "select * from tblAccount\n"
                    + "where Email=? \n";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, email);
            ResultSet rs = preSt.executeQuery();
            boolean checkEmail = rs.next();
            con.close();
            return checkEmail;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public static boolean addEmp(String userID, String firstname, String lastname, String address, String phone, String username, String password, String email) {

        try {
            String code = generateRandomCode();

            // Insert into tblAccount
            String query = "insert into tblAccount (Username, PasswordAcc, UserID, StatusAcc, Email, Firstname, Lastname, Address, Avatar, Phone, Role, Code) values(?,?,?,?,?,?,?,?,?,?,2,?)";

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, username);
            preSt.setString(2, password);
            preSt.setString(3, userID);
            preSt.setInt(4, 0); // Assuming StatusAcc is set to 0 for new accounts
            preSt.setString(5, email);
            preSt.setString(6, firstname);
            preSt.setString(7, lastname);
            preSt.setString(8, address);
            preSt.setString(9, "avatar.png");
            preSt.setString(10, phone);
            preSt.setString(11, code);
            preSt.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return true;

    }

    public static ArrayList<Account> getListCusAccount() {
        ArrayList<Account> listCusAcc;
        try {

            String query = "select acc.Username,acc.UserID,acc.StatusAcc,cus.FirstnameCus as firstname,cus.LastnameCus as lastname,cus.PhoneCus as phone,acc.Email from tblAccount acc\n"
                    + "join tblCustomer cus on cus.CustomerID=acc.UserID";

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            ResultSet rs = preSt.executeQuery();
            listCusAcc = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString(1);
                String userID = rs.getString(2);
                int status = rs.getInt(3);

                String firstname = rs.getString(4);
                String lastname = rs.getString(5);
                String phone = rs.getString(6);
                String email = rs.getString(7);
                Account newAcc = new Account(username, userID, firstname, lastname, phone, email, status);
                listCusAcc.add(newAcc);
            }
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
        return listCusAcc;
    }

    public static ArrayList<Account> getListEmpAccount() {
        ArrayList<Account> listEmpAcc;
        try {

            String query = "select acc.Username,acc.UserID,acc.StatusAcc,e.FirstnameEmp as firstname\n"
                    + ",e.LastnameEmp as lastname,e.PhoneEmp as phone,acc.Email from tblAccount acc\n"
                    + "join tblEmployee e on e.EmployeeID=acc.UserID";

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            ResultSet rs = preSt.executeQuery();
            listEmpAcc = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString(1);
                String userID = rs.getString(2);
                int status = rs.getInt(3);

                String firstname = rs.getString(4);
                String lastname = rs.getString(5);
                String phone = rs.getString(6);
                String email = rs.getString(7);
                Account newAcc = new Account(username, userID, firstname, lastname, phone, email, status);
                listEmpAcc.add(newAcc);
            }
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
        return listEmpAcc;
    }

    public static boolean lockEmployee(String empID) {
        try {
            String query = "update tblAccount set StatusAcc=0 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, empID);
            preSt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean lockCustomer(String empID) {
        try {
            String query = "update tblAccount set StatusAcc=0 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, empID);
            preSt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean unlockEmployee(String empID) {
        try {
            String query = "update tblAccount set StatusAcc=1 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, empID);
            preSt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean unlockCustomer(String empID) {
        try {
            String query = "update tblAccount set StatusAcc=1 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, empID);
            preSt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean checkOldPass(String userID, String oldPass) {
        try {
            String query = "select PasswordAcc from tblAccount where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, userID);
            ResultSet rs = preSt.executeQuery();
            if (rs.next()) {
                return oldPass.equals(rs.getString(1));
            } else {
                return false;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public static boolean changePass(String userID, String newPass) {
        try {
            String query = "update tblAccount set PasswordAcc=?\n"
                    + "where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, newPass);
            preSt.setString(2, userID);
            preSt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String MaHoa(String str) {
        String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn";// Làm cho mật khẩu phức tap
        String result = null;

        str = str + salt;
        try {
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Tạo mã code ngẫu nhiên 6 chữ số
    private static String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    // Gửi mã code qua email
    private static void sendCodeToEmail(String email, String code) {
        final String username = "phuongnam121103@gmail.com"; // Thay bằng email của bạn
        final String password = "eqna xeml exop mnzc"; // Thay bằng mật khẩu email của bạn

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Verification Code");
            message.setText("Your verification code is: " + code);
            Transport.send(message);
            System.out.println("Code sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void sendCodeToEmailSuccsessOrder(String email, String orderiD, String name) {
//        final String username = "phuongnam121103@gmail.com"; // Thay bằng email của bạn
//        final String password = "eqna xeml exop mnzc"; // Thay bằng mật khẩu email của bạn
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(email));
//            message.setSubject("Đơn hàng " + orderiD + " đã giao hàng thành công");
//            message.setText("Xin chào " + name + "\n\n đơn hàng "  + orderiD + " đã được giao thành công");
//            Transport.send(message);
//            System.out.println("Code sent successfully.");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    public static void sendCodeToEmailSuccsessOrder(String email, String orderID, String name) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address: " + email);
        }

        final String username = "phuongnam121103@gmail.com"; // Thay bằng email của bạn
        final String password = "eqna xeml exop mnzc"; // Thay bằng mật khẩu email của bạn

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Đơn hàng #" + orderID + " đã giao hàng thành công");
            message.setText("Xin chào " + name + "\n\n đơn hàng #" + orderID + " đã được giao thành công");
            Transport.send(message);
            System.out.println("Code sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // Thêm khách hàng và gửi mã code
    public static boolean addCustomer(String userID, String firstname, String lastname, String address, String phone, String username, String password, String email) {
        try {
            String code = generateRandomCode();

            // Insert into tblAccount
            String query = "insert into tblAccount (Username, PasswordAcc, UserID, StatusAcc, Email, Firstname, Lastname, Address, Avatar, Phone, Role, Code) values(?,?,?,?,?,?,?,?,?,?,3,?)";

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, username);
            preSt.setString(2, password);
            preSt.setString(3, userID);
            preSt.setInt(4, 0); // Assuming StatusAcc is set to 0 for new accounts
            preSt.setString(5, email);
            preSt.setString(6, firstname);
            preSt.setString(7, lastname);
            preSt.setString(8, address);
            preSt.setString(9, "avatar.png");
            preSt.setString(10, phone);
            preSt.setString(11, code);
            preSt.executeUpdate();
            con.close();

            // Send the code to the email
            sendCodeToEmail(email, code);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra mã code và cập nhật trạng thái
    public static boolean verifyCodeAndUpdateStatus(String userID, String code) {
        try {
            String query = "select * from tblAccount where UserID=? and Code=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, userID);
            preSt.setString(2, code);
            ResultSet rs = preSt.executeQuery();
            if (rs.next()) {
                // Nếu mã code đúng, cập nhật trạng thái tài khoản
                query = "update tblAccount set StatusAcc=1 where UserID=?";
                preSt = con.prepareStatement(query);
                preSt.setString(1, userID);
                preSt.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean resendVerificationCode(String userID) {
        try {
            String newCode = generateRandomCode();

            // Update the code in the database
            String query = "update tblAccount set Code=? where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, newCode);
            preSt.setString(2, userID);
            int rowsUpdated = preSt.executeUpdate();
            con.close();

            if (rowsUpdated > 0) {
                // Get the email of the user
                query = "select Email from tblAccount where UserID=?";
                con = DBConnect.getConnection();
                preSt = con.prepareStatement(query);
                preSt.setString(1, userID);
                ResultSet rs = preSt.executeQuery();
                String email = null;
                if (rs.next()) {
                    email = rs.getString("Email");
                }
                con.close();

                if (email != null) {
                    // Send the new code to the email
                    sendCodeToEmail(email, newCode);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCustomer(String userID) {
        try {
            String query = "DELETE FROM tblAccount WHERE UserID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, userID);
            preSt.executeUpdate();
            con.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendVerifyCodeAndUpdatePassword(String userEmail) {
        try {
            String verificationCode = generateRandomCode(); // Generate random verification code
            // Send verification code to the user's email
            sendCodeToEmail(userEmail, verificationCode);

            // Update the password with the verification code
            boolean updated = UserRepository.updatePassword(userEmail, verificationCode);

            return updated;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updatePassword(String email, String newPassword) {
        try {
            Connection con = DBConnect.getConnection();
            String query = "UPDATE tblAccount SET PasswordAcc = ? WHERE Email = ?";
            PreparedStatement preSt = con.prepareStatement(query);
            newPassword = UserRepository.MaHoa(newPassword);
            preSt.setString(1, newPassword);
            preSt.setString(2, email);
            int rowsUpdated = preSt.executeUpdate();
            con.close();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateCustomerToCTV(String UserID) {
        Connection con = null;
        try {
            con = DBConnect.getConnection();
            con.setAutoCommit(false); // Start transaction

            // Get user email before updating
            String getEmailQuery = "SELECT Email FROM tblAccount WHERE UserID = ?";
            PreparedStatement getEmailStmt = con.prepareStatement(getEmailQuery);
            getEmailStmt.setString(1, UserID);
            ResultSet rs = getEmailStmt.executeQuery();
            String userEmail = null;
            if (rs.next()) {
                userEmail = rs.getString("Email");
            } else {
                // If user does not exist, rollback and return false
                con.rollback();
                return false;
            }

            // Update Role in tblAccount
            String updateAccountQuery = "UPDATE tblAccount SET Role = ? WHERE UserID = ?";
            PreparedStatement updateAccountStmt = con.prepareStatement(updateAccountQuery);
            updateAccountStmt.setInt(1, 4); 
            updateAccountStmt.setString(2, UserID);
            updateAccountStmt.executeUpdate();

            // Update Status in tblBrand
            String updateBrandQuery = "UPDATE tblBrand SET Status = 1 WHERE CTVID = ?";
            PreparedStatement updateBrandStmt = con.prepareStatement(updateBrandQuery);
            updateBrandStmt.setString(1, UserID);
            updateBrandStmt.executeUpdate();

            // Commit transaction
            con.commit();

            // Close connection
            con.close();

            // Send email notification
            if (userEmail != null) {
                sendUpdateNotificationEmail(userEmail, UserID);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true); // Reset auto commit
                    con.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void sendUpdateNotificationEmail(String email, String newUserID) {
        final String username = "phuongnam121103@gmail.com"; // Thay bằng email của bạn
        final String password = "eqna xeml exop mnzc"; // Thay bằng mật khẩu email của bạn

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Account Update Notification");
            message.setText("Dear User,\n\nTài khoản đăng kí làm CTV đã được duyệt thành công. \n\nBest Regards,\nBabyCare Company");

            Transport.send(message);
            System.out.println("Update notification sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkExistBrandID(String brandID) {
        try {
            String query = "select * from tblBrand\n"
                    + "where BrandID=? \n";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, brandID);
            ResultSet rs = preSt.executeQuery();
            boolean checkBrandID = rs.next();
            con.close();
            return checkBrandID;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }

    public static boolean checkBrandNameExist(String brandName) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from tblBrand  where BrandName =?");
            stmt.setString(1, brandName);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            System.out.println("loi checkBrandNameExsit(String brandName)");
            e.printStackTrace();
        }
        return false;
    }

   

    private static final Logger logger = Logger.getLogger(UserRepository.class.getName());

    public static boolean addBrand(String brandID, String brandName, String brandDescription, String logo, String brandAddress, String userID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        String query = "INSERT INTO tblBrand (BrandID, BrandName, BrandDescription, BrandLogo, BrandAddress, CTVID, Status) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, brandID);
            ps.setString(2, brandName);
            ps.setString(3, brandDescription);
            ps.setString(4, logo);
            ps.setString(5, brandAddress);
            ps.setString(6, userID);
            ps.setInt(7, 0);

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

    public static boolean deleteBrandByCTVID(String ctvID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        String query = "DELETE FROM tblBrand WHERE CTVID = ?";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, ctvID);

            logger.info("Executing query: " + ps.toString());

            result = ps.executeUpdate() > 0;
            if (result) {
                logger.info("Brand(s) deleted successfully.");
            } else {
                logger.warning("Failed to delete brand(s).");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting brand(s)", e);
        }
        return result;
    }

    public static boolean deleteBrandByCTVID1(String ctvID) {
        try {
            String query = "DELETE FROM tblBrand WHERE CTVID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, ctvID);
            preSt.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean hasPendingRegistration(String userId) {
        boolean hasPending = false;
        try {
            String query = "SELECT Status FROM tblBrand WHERE CTVID = ? AND Status = 0";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            hasPending = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasPending;
    }

    public static ArrayList<Brand> getListBrandsWithStatusZero() {
        ArrayList<Brand> listBrands;
        try {
            String query = "SELECT BrandID, BrandName, BrandDescription, BrandLogo, BrandAddress, CTVID, Status "
                    + "FROM tblBrand "
                    + "WHERE Status = 0";

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            ResultSet rs = preSt.executeQuery();
            listBrands = new ArrayList<>();
            while (rs.next()) {
                String brandID = rs.getString(1);
                String brandName = rs.getString(2);
                String brandDescription = rs.getString(3);
                String brandLogo = rs.getString(4);
                String brandAddress = rs.getString(5);
                String ctvid = rs.getString(6);
                int status = rs.getInt(7);

                Brand newBrand = new Brand(brandID, brandName, brandDescription, brandLogo, brandAddress, ctvid, status);
                listBrands.add(newBrand);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listBrands;
    }

    public static ArrayList<Account> getListCTVAccount() {
        ArrayList<Account> listEmpAcc;
        try {
            String query = "select Username, UserID, StatusAcc, Firstname, Lastname, Phone, Email from tblAccount where Role = 4";

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            ResultSet rs = preSt.executeQuery();
            listEmpAcc = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString("Username");
                String userID = rs.getString("UserID");
                int status = rs.getInt("StatusAcc");
                String firstname = rs.getString("FirstnameAd");
                String lastname = rs.getString("LastnameAd");
                String phone = rs.getString("PhoneAd");
                String email = rs.getString("Email");

                Account newAcc = new Account(username, userID, firstname, lastname, phone, email, status);
                listEmpAcc.add(newAcc);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listEmpAcc;
    }

//    public static User getUserByProductId(String productId) {
//    User user = null;
//
//    try {
//        Connection con = DBConnect.getConnection();
//
//        
//        String sql = "SELECT tblCustomer.* "
//                   + "FROM tblProductComment "
//                   + "JOIN tblCustomer ON tblProductComment.UserID = tblCustomer.CustomerID "
//                   + "WHERE tblProductComment.ProductID = ?";
//        PreparedStatement stmt = con.prepareStatement(sql);
//        stmt.setString(1, productId);
//        ResultSet rs = stmt.executeQuery();
//
//         if (rs.next()) {
//                user = new User(rs.getString(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6));
//            } else {
//                // If no result from customerQuery, check CTVID
//                String ctvQuery = "SELECT tblCTV.*"
//                        + "FROM tblBill "
//                        + "JOIN tblCTV ON tblProductComment.UserID = tblCTV.CTVID "
//                        + "WHERE tblBill.BillID = ?";
//                stmt = con.prepareStatement(ctvQuery);
//                stmt.setString(1, productId);
//                rs = stmt.executeQuery();
//
//                if (rs.next()) {
//                    user = new User(rs.getString(1),
//                            rs.getString(2),
//                            rs.getString(3),
//                            rs.getString(4),
//                            rs.getString(5),
//                            rs.getString(6));
//                }
//            }
//
//        con.close();
//    } catch (Exception e) {
//        e.printStackTrace();
//        return null;
//    }
//    return user;
//}
//   public static User getUserDetailsByCommentId(String CommentID) {
//    User user = null;
//
//  try {
//        Connection con = DBConnect.getConnection();
//
//        
//            String sql = "SELECT c.*, ctv.*, a.*\n" +
//                "FROM tblProductComment pc \n" +
//                "LEFT JOIN tblAccount a ON pc.UserID = a.UserID\n" +
//                "LEFT JOIN tblCustomer c ON a.UserID = c.CustomerID \n" +
//                "LEFT JOIN tblCTV ctv ON a.UserID = ctv.CTVID  \n" +
//                "WHERE pc.CommentID = ?";
//        PreparedStatement stmt = con.prepareStatement(sql);
//        stmt.setString(1, CommentID );
//        ResultSet rs = stmt.executeQuery();
//
//         if (rs.next()) {
//                user = new User(rs.getString(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6));
//            }
//
//        con.close();
//    } catch (Exception e) {
//        e.printStackTrace();
//        return null;
//    }
//    return user;
//}
    public static User getUserDetailsByCommentId(String CommentID) {
        User user = null;

        try {
            Connection con = DBConnect.getConnection();

            String sql = "SELECT a.UserID, a.Firstname, a.Lastname, a.Avatar, a.Address, a.Phone "
                    + "FROM tblProductComment pc "
                    + "LEFT JOIN tblAccount a ON pc.UserID = a.UserID "
                    + "WHERE pc.CommentID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, CommentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userID = rs.getString("UserID");
                String firstName = rs.getString("Firstname");
                String lastName = rs.getString("Lastname");
                String avatar = rs.getString("Avatar");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");

                user = new User(userID, firstName, lastName, address, avatar, phone);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        String userID = "U223";
//        String username = "abcd";
//        String password = "123456789";
//        String firstname = "Đặng";
//        String lastname = "Nam";
//        String email = "khoiptde170417@fpt.edu.vn";
//        String address = "abc";
//        String phone = "0795632854";
//
//    
//        UserRepository.addCustomer(userID, firstname, lastname, address, phone, username, password, email);
//        
//        String oldUserID = "U1185"; // 
//        boolean result = UserRepository.updateCustomerToCTV(oldUserID);
//        if (result) {
//            System.out.println("Customer updated to CTV successfully.");
//        } else {
//            System.out.println("Failed to update customer to CTV.");
//        }
//    String userEmail = "khoiptde170417@fpt.edu.vn"; // Thay đổi thành địa chỉ email muốn gửi mã xác minh
//    String verificationCode = "123456"; // Thay đổi thành mã xác minh muốn gửi
//    
//    sendCodeToEmail(userEmail, verificationCode);

//        String userID = "U0010";
//        String firstname = "Đặng";
//        String lastname = "N";
//        String address = "abc";
//        String phone = "0795632854";
//
//        boolean result = updateUser1(userID, firstname, lastname, address, phone);
//
//        if (result) {
//            System.out.println("Cập nhật người dùng thành công!");
//        } else {
//            System.out.println("Cập nhật người dùng thất bại.");
//        }
//
//        String avatar = "img/avatar.png"; // Đặt tên của avatar mới
//        
//        try {
//            UserRepository.updateAvatar(userID, avatar);
//            System.out.println("Avatar updated successfully for user " + userID);
//        } catch (SQLException | ClassNotFoundException e) {
//            System.err.println("Error updating avatar: " + e.getMessage());
//        }
//    System.out.println(MaHoa("1234567"));
//    for (Account ac: getListEmpAccount()
//             ) {
//            System.out.println(ac);
//        }
//        
//        String brandID = MyRandom.getRandomBrandID();
//        String brandName = "Example Brrand";
//        String brandDescription = "This is an example brand description.";
//        String brandLogo = "http://example.com/logo.png";
//        String brandAddress = "123 Example Street";
//        String CTVID = "b";
//
//        boolean result = UserRepository.addBrand(brandID, brandName, brandDescription, brandLogo, brandAddress,CTVID);
//        if (result) {
//            System.out.println("succsecss");
//        }else{
//            System.out.println("fail");
//        }
//        String oldUserID = "U3853";
//
//            boolean result = updateCustomerToCTV(oldUserID);
//
//            if (result) {
//                System.out.println("Customer to CTV update successful.");
//            } else {
//                System.out.println("Customer to CTV update failed.");
//            }
//    ArrayList<Brand> brands = getListBrandsWithStatusZero();
//            if (brands != null) {
//                for (Brand brand : brands) {
//                    System.out.println(brand);
//                }
//            } else {
//                System.out.println("No brands found or an error occurred.");
//            }
//        boolean a = UserRepository.hasPendingRegistration("U6772");
//        System.out.println(a);
//
//try {
//            String commentId = "CM5977"; // Thay bằng CommentID cụ thể
//            User user = getUserDetailsByCommentId(commentId);
//            if (user != null) {
//                System.out.println(user);
//            } else {
//                System.out.println("No user found for the given commentId.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String userID = "U4871"; // Thay thế bằng userID thực tế của bạn
        boolean result = updateCustomerToCTV(userID);

        if (result) {
            System.out.println("Update thành công!");
        } else {
            System.out.println("Update không thành công!");
        }

    }

}
