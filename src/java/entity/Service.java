package entity;

public class Service {

    private int serviceID;
    private String serviceName;
    private double servicePrice;
    private String description;
    private String listImg;

    public Service() {
    }

    public Service(int serviceID, String serviceName, double servicePrice, String description, String listImg) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.description = description;
        this.listImg = listImg;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListImg() {
        return listImg;
    }

    public void setListImg(String listImg) {
        this.listImg = listImg;
    }

    @Override
    public String toString() {
        return "Service{" + "serviceID=" + serviceID + ", serviceName=" + serviceName + ", servicePrice=" + servicePrice + ", description=" + description + ", listImg=" + listImg + '}';
    }

    

    

   

}
