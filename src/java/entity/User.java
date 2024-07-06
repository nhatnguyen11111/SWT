package entity;

public class User {
    protected String userId ,firstname, lastname,address,avatar,phone;
    protected int role;


    public User() {
        
    }

    public User(String userId, String firstname, String lastname, String address, String avatar, String phone) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.avatar = avatar;
        this.phone = phone;

    }

    public User(String userId, String firstname, String lastname, String address, String avatar, String phone, int role) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.avatar = avatar;
        this.phone = phone;
        this.role = role;
    }
    

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", address=" + address + ", avatar=" + avatar + ", phone=" + phone + ", role=" + role + '}';
    }

    
   
}