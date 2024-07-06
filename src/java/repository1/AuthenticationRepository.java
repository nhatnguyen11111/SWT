package repository1;


import config.DBConnect;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationRepository {
    public static User Verify(String username, String password) {
    try {
        String query = "Select * from tblAccount where username=? and passwordAcc=?";
        Connection con = DBConnect.getConnection();
        PreparedStatement preSt = con.prepareStatement(query);
        preSt.setString(1, username);
        preSt.setString(2, password);
        ResultSet rs = preSt.executeQuery();
        if (rs.next()) {
            String userID = rs.getString("UserID");
            String firstname = rs.getString("Firstname");
            String lastname = rs.getString("Lastname");
            String address = rs.getString("Address");
            String avatar = rs.getString("Avatar");
            String phone = rs.getString("Phone");
            int role = rs.getInt("Role");
            con.close();

            switch (role) {
                case 1: // Admin
                    return new Admin(userID, firstname, lastname, address, avatar, phone,role);
                case 2: // Employee
                    return new Employee(userID, firstname, lastname, address, avatar, phone,role);
                case 3: // Customer
                    return new Customer(userID, firstname, lastname, address, avatar, phone,role);
                case 4: // CTV
                    return new CTV(userID, firstname, lastname, address, avatar, phone,role);
                default:
                    return null;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


    


    public static  int getStatusAcc(String username){
        try {
            Connection con = DBConnect.getConnection();
            String query = "select StatusAcc from tblAccount\n" +
                    "where  Username=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                return  rs.getInt(1);
            }


            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi method getStatusAcc(Cart cart,String orderId) trong OrderRepository.java ");
           return 0;
        }

        return 0;
    }
    public static void main(String[] args) {
        String username = "nam";
        String password = "qO66kiOwYbcaV0sUwqI4WT6JF0o=";

        User user = Verify(username, password);
        
        if (user != null) {
            System.out.println("Login successful!");
            System.out.println("User Info: " + user);
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }
}
