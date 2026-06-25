package com.code.ecommerce.dto.response;

// storing the user response
public class UserDetailsResponse {

    private String name;
    private String email;
    private String city;
    private String state;
    private String country;
    private String role;
    private String gender;

    public UserDetailsResponse(){}

    public UserDetailsResponse(String email, String name, String city, String state, String country, String role, String gender) {
        this.email = email;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
