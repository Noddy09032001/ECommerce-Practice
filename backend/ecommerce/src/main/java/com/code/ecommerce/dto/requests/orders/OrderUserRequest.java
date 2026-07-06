package com.code.ecommerce.dto.requests.orders;

public class OrderUserRequest {

    private String email;  // the email of the current user
    private String phoneNumber;  // the phone number of the current user

    public OrderUserRequest(){}

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
}
