/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class VNPAYConfig {
    private String vnp_Url;
    private String return_Url;
    private String vnp_TmnCode;
    private String secretKey;
    private String vnp_Version;
    private String vnp_Command;
    private String orderType;

    public VNPAYConfig() {
    }

    public VNPAYConfig(String vnp_Url, String return_Url, String vnp_TmnCode, String secretKey, String vnp_Version, String vnp_Command, String orderType) {
        this.vnp_Url = vnp_Url;
        this.return_Url = return_Url;
        this.vnp_TmnCode = vnp_TmnCode;
        this.secretKey = secretKey;
        this.vnp_Version = vnp_Version;
        this.vnp_Command = vnp_Command;
        this.orderType = orderType;
    }

    public String getVnp_Url() {
        return vnp_Url;
    }

    public void setVnp_Url(String vnp_Url) {
        this.vnp_Url = vnp_Url;
    }

    public String getReturn_Url() {
        return return_Url;
    }

    public void setReturn_Url(String return_Url) {
        this.return_Url = return_Url;
    }

    public String getVnp_TmnCode() {
        return vnp_TmnCode;
    }

    public void setVnp_TmnCode(String vnp_TmnCode) {
        this.vnp_TmnCode = vnp_TmnCode;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getVnp_Version() {
        return vnp_Version;
    }

    public void setVnp_Version(String vnp_Version) {
        this.vnp_Version = vnp_Version;
    }

    public String getVnp_Command() {
        return vnp_Command;
    }

    public void setVnp_Command(String vnp_Command) {
        this.vnp_Command = vnp_Command;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "VNPAYConfig{" + "vnp_Url=" + vnp_Url + ", return_Url=" + return_Url + ", vnp_TmnCode=" + vnp_TmnCode + ", secretKey=" + secretKey + ", vnp_Version=" + vnp_Version + ", vnp_Command=" + vnp_Command + ", orderType=" + orderType + '}';
    }
    
    
}
