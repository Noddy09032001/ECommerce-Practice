package com.code.ecommerce.dto.requests.orders;

public class AddressDetails {

    private String street;  // the street and the building address of the user
    private String city;  // the city of the user
    private String state;  // the state of the user
    private String country;  // the country details of the user
    private String pinCode;  // the pin-code of the address of the user

    public AddressDetails(){}

    public AddressDetails(String street, String city, String state, String country, String pinCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
}
