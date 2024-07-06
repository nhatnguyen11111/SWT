package entity;

import java.sql.Date;
import java.sql.Timestamp;

public class CommentProduct {

    private String commentID;
    private String productID;
    private String comment;
    private String userID;
    private int rating;
    private String commentImg;
    private Timestamp  createdAt;
    private String BillID;
    private User user;

    // Constructor, getters và setters cho các trường hiện có

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    public CommentProduct(String commentID, String productID, String comment, String userID, String commentImg, int rating) {
        this.commentID = commentID;
        this.productID = productID;
        this.comment = comment;
        this.userID = userID;
        this.commentImg = commentImg;
        this.rating = rating;
    }

    public CommentProduct(String commentID, String productID, String comment, String userID, int rating, String commentImg, Timestamp createdAt) {
        this.commentID = commentID;
        this.productID = productID;
        this.comment = comment;
        this.userID = userID;
        this.rating = rating;
        this.commentImg = commentImg;
        this.createdAt = createdAt;
    }

    public CommentProduct(String commentID, String productID, String comment, int rating, String userID, String fileName) {
        this.commentID = commentID;
        this.productID = productID;
        this.comment = comment;
        this.userID = userID;
        this.commentImg = fileName;
        this.rating = rating;
    }
    
 public CommentProduct() {
    }

   
    
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCommentImg() {
        return commentImg;
    }

    public void setCommentImg(String commentImg) {
        this.commentImg = commentImg;
    }

    public Timestamp  getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp  createdAt) {
        this.createdAt = createdAt;
    }

    public String getBillID() {
        return BillID;
    }

    public void setBillID(String BillID) {
        this.BillID = BillID;
    }
    

    @Override
    public String toString() {
        return "CommentProduct{" + "commentID=" + commentID + ", productID=" + productID + ", comment=" + comment + ", userID=" + userID + ", commentImg=" + commentImg + ", createdAt=" + createdAt + '}';
    }
}
