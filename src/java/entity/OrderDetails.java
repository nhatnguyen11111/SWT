/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class OrderDetails {
    // Properties
    private String billID;
    private String productID;
    private int amountProduct;
    private double priceAtPurchase;

    // Constructors
    public OrderDetails() {
        // Default constructor
    }

    public OrderDetails(String billID, String productID, int amountProduct, double priceAtPurchase) {
        this.billID = billID;
        this.productID = productID;
        this.amountProduct = amountProduct;
        this.priceAtPurchase = priceAtPurchase;
    }

    // Getters and Setters
    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "billID=" + billID + ", productID=" + productID + ", amountProduct=" + amountProduct + ", priceAtPurchase=" + priceAtPurchase + '}';
    }

    
}
