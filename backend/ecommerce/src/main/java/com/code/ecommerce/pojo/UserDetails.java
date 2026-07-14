package com.code.ecommerce.pojo;

import com.code.ecommerce.common.constants.GenderConstants;
import com.code.ecommerce.common.constants.RoleConstants;
import com.code.ecommerce.pojo.orders.Order;
import com.code.ecommerce.pojo.orders.OrderDetails;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    // adding the address details
    private String city;
    private String state;
    private String country;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(columnDefinition = "TEXT")
    private String address;  // storing the basic address

    @Enumerated(EnumType.STRING)
    private GenderConstants gender;   // gender of the user

    @Enumerated(EnumType.STRING)
    private RoleConstants role;  // role of the user

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public UserDetails(){}

    public UserDetails(Long id, String name, String password, String email, String phoneNumber, String city, String state,
                       String country, String pinCode, String address, GenderConstants gender, RoleConstants role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.address = address;
        this.gender = gender;
        this.role = role;
    }

    public UserDetails(String name, String password, String email, String phoneNumber, String city, String state,
                       String country, String pinCode, String address, GenderConstants gender, RoleConstants role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.address = address;
        this.gender = gender;
        this.role = role;
    }

    public UserDetails(Long id, String name, String password, String phoneNumber, String email, String city,
                       String country, String state, String pinCode, String address, GenderConstants gender,
                       RoleConstants role, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.country = country;
        this.state = state;
        this.pinCode = pinCode;
        this.address = address;
        this.gender = gender;
        this.role = role;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GenderConstants getGender() {
        return gender;
    }

    public void setGender(GenderConstants gender) {
        this.gender = gender;
    }

    public RoleConstants getRole() {
        return role;
    }

    public void setRole(RoleConstants role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
