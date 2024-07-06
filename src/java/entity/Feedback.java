/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Feedback {
    private int id;
    private String customerID;
    private int serviceID;
    private String testimonial;
    private Date experienceDate;
    private int satisfactionLevel;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Feedback() {
    }

    public Feedback( String customerID, int serviceID, String testimonial, Date experienceDate, int satisfactionLevel, String name) {
        this.customerID = customerID;
        this.serviceID = serviceID;
        this.testimonial = testimonial;
        this.experienceDate = experienceDate;
        this.satisfactionLevel = satisfactionLevel;
        this.name = name;
    }

   

    public Feedback(int id, String customerID, int serviceID, String testimonial, Date experienceDate, int satisfactionLevel) {
        this.id = id;
        this.customerID = customerID;
        this.serviceID = serviceID;
        this.testimonial = testimonial;
        this.experienceDate = experienceDate;
        this.satisfactionLevel = satisfactionLevel;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getTestimonial() {
        return testimonial;
    }

    public void setTestimonial(String testimonial) {
        this.testimonial = testimonial;
    }

   

    

    public Date getExperienceDate() {
        return experienceDate;
    }

    public void setExperienceDate(Date experienceDate) {
        this.experienceDate = experienceDate;
    }

    public int getSatisfactionLevel() {
        return satisfactionLevel;
    }

    public void setSatisfactionLevel(int satisfactionLevel) {
        this.satisfactionLevel = satisfactionLevel;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", customerID=" + customerID + ", serviceID=" + serviceID + ", testimonial=" + testimonial + ", experienceDate=" + experienceDate + ", satisfactionLevel=" + satisfactionLevel + ", name=" + name + '}';
    }

   

    

    
}
