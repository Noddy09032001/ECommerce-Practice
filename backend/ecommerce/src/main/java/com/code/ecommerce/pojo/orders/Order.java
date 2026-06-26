package com.code.ecommerce.pojo.orders;

import com.code.ecommerce.pojo.UserDetails;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false, unique = true)
    private String orderId;  // the order id being generated

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderDetails orderDetails;  // one to one association with the order details

    @Column(name = "created_on")
    private LocalDateTime createdOn;  // time of the order creation

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;   // time of the order modification

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "current_status")
    private String currentStatus;  // current status of the order

    @Column(name = "total_items")
    private Integer totalItems;   // the total items in the order

    @Column(name = "grand_total")
    private double grandTotal;  // the grand total amount for the order

    public Order(){}

    public Order(Long id, String orderId, OrderDetails orderDetails, LocalDateTime modifiedOn, LocalDateTime createdOn,
                 String createdBy, String modifiedBy, String currentStatus, Integer totalItems, double grandTotal) {
        this.id = id;
        this.orderId = orderId;
        this.orderDetails = orderDetails;
        this.modifiedOn = modifiedOn;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.currentStatus = currentStatus;
        this.totalItems = totalItems;
        this.grandTotal = grandTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
}
