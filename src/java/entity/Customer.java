package entity;

public class Customer extends User{
    public Customer() {
    }

    public Customer(String userId, String firstname, String lastname, String address,String avatar, String phone, int role) {
        super(userId, firstname, lastname, address, avatar,  phone, role);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId='" + userId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
