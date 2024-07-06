
package entity;

public class Brand {
    private String brandID;
    private String brandName;
    private String brandDescription;
    private String brandLogo;
    private String brandAddess;
    private String CTVID;
    private int Status;
    
    
    public Brand() {
    }

    public Brand(String brandID, String brandName, String brandDescription, String brandLogo, String brandAddess) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
        this.brandLogo = brandLogo;
        this.brandAddess = brandAddess;
        
    }

    public Brand(String brandID, String brandName, String brandDescription, String brandLogo, String brandAddess, String CTVID, int Status) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
        this.brandLogo = brandLogo;
        this.brandAddess = brandAddess;
        this.CTVID = CTVID;
        this.Status = Status;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

   
    
    public String getCTVID() {
        return CTVID;
    }

    public void setCTVID(String CTVID) {
        this.CTVID = CTVID;
    }
    

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getBrandAddess() {
        return brandAddess;
    }

    public void setBrandAddess(String brandAddess) {
        this.brandAddess = brandAddess;
    }

    @Override
    public String toString() {
        return "Brand{" + "brandID=" + brandID + ", brandName=" + brandName + ", brandDescription=" + brandDescription + ", brandLogo=" + brandLogo + ", brandAddess=" + brandAddess + ", CTVID=" + CTVID + '}';
    }

    

    
}
