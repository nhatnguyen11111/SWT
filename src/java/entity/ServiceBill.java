package entity;

import java.util.Date;


public class ServiceBill {
    private int billID;
    private int bookingID;
    private String customerID;
    private Date billDate;
    private double totalAmount;
    private int billStatus;
    public ServiceBill() {
    }

    public ServiceBill(int billID, int bookingID, String customerID, Date billDate, double totalAmount, int billStatus) {
        this.billID = billID;
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.billDate = billDate;
        this.totalAmount = totalAmount;
        this.billStatus = billStatus;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }

    @Override
    public String toString() {
        return "ServiceBill{" + "billID=" + billID + ", bookingID=" + bookingID + ", customerID=" + customerID + ", billDate=" + billDate + ", totalAmount=" + totalAmount + ", billStatus=" + billStatus + '}';
    }

   

   
}
