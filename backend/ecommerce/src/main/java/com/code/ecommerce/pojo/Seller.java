package com.code.ecommerce.pojo;

import com.code.ecommerce.pojo.orders.OrderDetails;
import com.code.ecommerce.pojo.orders.OrderItemDetails;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// merchant or seller details for the items
@Entity
@Table(name = "seller")
public class Seller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seller_id", nullable = false, unique = true)
    private String sellerId;  // the unique id for the seller

    @Column(name = "seller_name", nullable = false)
    private String sellerName;  // business name of the seller

    @Column(nullable = false, unique = true)
    private String email;  // email of the seller
    private String phone;  // phone number of the seller
    private String address;  // address of the seller

    @Column(name = "active")
    private Boolean active;  // if the seller is active or not

    @Column(name = "created_on")
    private LocalDateTime createdOn;  // date of creation

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;  // date of modification

    // products sold by the seller
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SellerItemMapping> items = new ArrayList<>();

    // orders associated with the seller
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemDetails> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Seller(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public List<SellerItemMapping> getItems() {
        return items;
    }

    public void setItems(List<SellerItemMapping> items) {
        this.items = items;
    }

    public List<OrderItemDetails> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDetails> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
