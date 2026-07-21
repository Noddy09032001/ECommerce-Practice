package com.code.ecommerce.pojo.orders;

import com.code.ecommerce.pojo.UserDetails;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false, unique = true)
    private String orderId;  // the order id being generated

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetails user;   // the order is for which user

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderDetails orderDetails;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItemDetails> orderItems = new ArrayList<>();  // getting the order items associated

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderStatusHistory> orderStatuses = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderPaymentDetails orderPaymentDetails;

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
    private Double grandTotal;  // the grand total amount for the order

    public Order(){}

    public Order(Long id, String orderId, UserDetails user, List<OrderItemDetails> orderItems, OrderDetails orderDetails,
                 List<OrderStatusHistory> orderStatuses, OrderPaymentDetails orderPaymentDetails, LocalDateTime createdOn, LocalDateTime modifiedOn,
                 String createdBy, String modifiedBy, String currentStatus, Integer totalItems, Double grandTotal) {
        this.id = id;
        this.orderId = orderId;
        this.user = user;
        this.orderItems = orderItems;
        this.orderDetails = orderDetails;
        this.orderStatuses = orderStatuses;
        this.orderPaymentDetails = orderPaymentDetails;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
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

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
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

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<OrderItemDetails> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDetails> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderStatusHistory> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatusHistory> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public OrderPaymentDetails getOrderPaymentDetails() {
        return orderPaymentDetails;
    }

    public void setOrderPaymentDetails(OrderPaymentDetails orderPaymentDetails) {
        this.orderPaymentDetails = orderPaymentDetails;
    }

    /**
     * Add an item to the order items list for the existing order
     * @param item the details of the item to be added for the order
     */
    public void addOrderItem(OrderItemDetails item) {
        orderItems.add(item);  // adding the item to the list
        item.setOrder(this);  // setting the order id for the item
    }

    /**
     * Remove an item from the items list for the existing order
     * @param item the details of the item to be removed for the order
     */
    public void removeOrderItem(OrderItemDetails item) {
        orderItems.remove(item);   // removing the item from the list
        item.setOrder(null);  // deleting the order id for the item
    }
}
