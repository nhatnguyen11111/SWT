package entity;

public class ServiceBooked {
    private int bookingID;
    private String customerID;
    private String serviceID;
    private String name;
    private String phoneNumber;
    private String address;
    private String sex;
    private String bookingDate;
    private String slot;
    private int bookingStatus;
    private String note;
    private double price;
    private String email;
    private String serviceName;
    private int billID;
    private int billStatus;

    public ServiceBooked() {
    }

    public ServiceBooked(int bookingID, String customerID, String serviceID, String name, String phoneNumber, String address, String sex, String bookingDate, String slot, int bookingStatus, String note, double price, String email, String serviceName, int billID, int billStatus) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.serviceID = serviceID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sex = sex;
        this.bookingDate = bookingDate;
        this.slot = slot;
        this.bookingStatus = bookingStatus;
        this.note = note;
        this.price = price;
        this.email = email;
        this.serviceName = serviceName;
        this.billID = billID;
        this.billStatus = billStatus;
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

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }

    @Override
    public String toString() {
        return "ServiceBooked{" + "bookingID=" + bookingID + ", customerID=" + customerID + ", serviceID=" + serviceID + ", name=" + name + ", phoneNumber=" + phoneNumber + ", address=" + address + ", sex=" + sex + ", bookingDate=" + bookingDate + ", slot=" + slot + ", bookingStatus=" + bookingStatus + ", note=" + note + ", price=" + price + ", email=" + email + ", serviceName=" + serviceName + ", billID=" + billID + ", billStatus=" + billStatus + '}';
    }
    
    
    }