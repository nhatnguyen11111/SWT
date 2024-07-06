package entity;

public class Preferential {

    protected String preferential;
    protected String preferentialName;
    protected String startDay;
    protected String endDay;
    protected int quantity;
    protected double rate;
    protected String preferentiaDescription;
    protected String preferentiaImg;
    protected String employeeID;

    public Preferential(String preferential, String preferentialName, String startDay, String endDay, int quantity, String preferentiaDescription, String preferentiaImg, String CTVID) {
        this.preferential = preferential;
        this.preferentialName = preferentialName;
        this.startDay = startDay;
        this.endDay = endDay;
        this.quantity = quantity;
        this.preferentiaDescription = preferentiaDescription;
        this.preferentiaImg = preferentiaImg;
        this.employeeID = employeeID;
    }

    public Preferential(String name, String description, int discount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Preferential(String preferential, String preferentialName, String startDay, String endDay, int quantity, String preferentiaDescription, String preferentiaImg) {
              this.preferential = preferential;
        this.preferentialName = preferentialName;
        this.startDay = startDay;
        this.endDay = endDay;
        this.quantity = quantity;
        this.preferentiaDescription = preferentiaDescription;
        this.preferentiaImg = preferentiaImg;
    }

    public Preferential(String preferential, String preferentialName, String startDay, String endDay, int quantity, double rate, String preferentiaDescription, String preferentiaImg, String employeeID) {
        this.preferential = preferential;
        this.preferentialName = preferentialName;
        this.startDay = startDay;
        this.endDay = endDay;
        this.quantity = quantity;
        this.rate = rate;
        this.preferentiaDescription = preferentiaDescription;
        this.preferentiaImg = preferentiaImg;
        this.employeeID = employeeID;
    }
    
    
    public String getPreferential() {
        return preferential;
    }

    public void setPreferential(String preferential) {
        this.preferential = preferential;
    }

    public String getPreferentialName() {
        return preferentialName;
    }

    public void setPreferentialName(String preferentialName) {
        this.preferentialName = preferentialName;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPreferentiaDescription() {
        return preferentiaDescription;
    }

    public void setPreferentiaDescription(String preferentiaDescription) {
        this.preferentiaDescription = preferentiaDescription;
    }

    public String getPreferentiaImg() {
        return preferentiaImg;
    }

    public void setPreferentiaImg(String preferentiaImg) {
        this.preferentiaImg = preferentiaImg;
    }

    public String getCTVID() {
        return employeeID;
    }

    public void setCTVID(String CTVID) {
        this.employeeID = CTVID;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    
    @Override
    public String toString() {
        return "Preferential{" + "preferential=" + preferential + ", preferentialName=" + preferentialName + ", startDay=" + startDay 
                + ", endDay=" + endDay + ", Quantity=" + quantity + ", preferentiaDescription=" + preferentiaDescription + ", preferentiaImg="
                + preferentiaImg + ", employeeID=" + employeeID + '}';
    }


    public Preferential() {
    }
    
}
