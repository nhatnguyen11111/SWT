package service;
import config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import repository1.UserRepository;

import java.util.Locale;
import java.util.Random;
import repository1.ProductRepository;

public class MyRandom {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int LENGTH = 4;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

//    public static String getRandomCusID() {
//        StringBuilder sb ;
//        do {
//            sb = new StringBuilder();
//            sb.append("U");
//
//            for (int i = 1; i <= LENGTH; i++) {
//                sb.append(new Random().nextInt(10));
//            }
//        }
//        while(UserRepository.checkExistID(sb.toString()));
//        return sb.toString();
//    }
    public static String getRandomUserID() {
    StringBuilder sb;
    do {
        sb = new StringBuilder();
        for (int i = 0; i <5; i++) { // 5 số
            sb.append(new Random().nextInt(10));
        }
    } while (UserRepository.checkExistID(sb.toString()));
    return sb.toString();
    }

    public static String getRandomEmpID() {
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
        for (int i = 0; i <5; i++) { // 5 số
            sb.append(new Random().nextInt(10));
        }
        }
        while(UserRepository.checkExistID(sb.toString()));
        return sb.toString();
    }
    
    public static String getRandomBrandID() {
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("B");

            for (int i = 1; i <= LENGTH; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(UserRepository.checkExistID(sb.toString()));
        return sb.toString();
    }
    
    public static String getRandomProductID(){
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("P");

            for (int i = 1; i <= LENGTH; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(ProductRepository.checkExistProductID(String.valueOf(sb)));
        return sb.toString();
    }
    
    public static String getRandomDiscountID(){
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("DC");

            for (int i = 1; i <= LENGTH; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(ProductRepository.checkExistProductID(String.valueOf(sb)));
        return sb.toString();
    }
    public static String getRandomCommentID() {
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("CM");

            for (int i = 1; i <= LENGTH; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(UserRepository.checkExistID(sb.toString()));
        return sb.toString();
    }

    public static String generateRandomString() {
        int length = 10;
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }
        return sb.toString();
    }
   public static void main(String[] args) {

    }
     
}
