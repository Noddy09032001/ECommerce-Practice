package com.code.ecommerce.dto.requests;

import com.code.ecommerce.common.constants.GenderConstants;
import com.code.ecommerce.common.constants.RoleConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDetailsRequest {

    @NotNull(message = "Role is required")
    private RoleConstants role;

    @NotNull(message = "Gender is required")
    private GenderConstants gender;

    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    private String password;
    private String phoneNumber;

    private String city;
    private String state;
    private String country;
    private String pinCode;
    private String address;

    public UserDetailsRequest(){}

    public UserDetailsRequest(RoleConstants role, GenderConstants gender, String name, String email, String password, String phoneNumber, String state, String city,
                              String country, String pinCode, String address) {
        this.role = role;
        this.gender = gender;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.city = city;
        this.country = country;
        this.pinCode = pinCode;
        this.address = address;
    }

    public RoleConstants getRole() {
        return role;
    }

    public void setRole(RoleConstants role) {
        this.role = role;
    }

    public GenderConstants getGender() {
        return gender;
    }

    public void setGender(GenderConstants gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
}
