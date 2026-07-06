package com.code.ecommerce.dto.response.orderSearch;

import com.code.ecommerce.dto.response.UserDetailsResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderSearchResponse {

    private String orderId;   // the id of the current order
    private String currentStatus;  // the recent status of the order

    private Integer totalItems;  // the total items inside the order
    private Double grandTotal;  // the grand total amount of the order

    private LocalDateTime createdOn;  // the date on which order was created
    private LocalDateTime modifiedOn;  // the date on which order was modified

    private String createdBy;
    private String modifiedBy;

    private UserDetailsResponse customer; // customer information
    private OrderDetailsResponse orderDetails; // order details
    private List<SellerOrderResponse> sellerOrders = new ArrayList<>();
    private List<OrderStatusHistoryResponse> orderStatuses = new ArrayList<>(); // status history

    public OrderSearchResponse(){}

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public UserDetailsResponse getCustomer() {
        return customer;
    }

    public void setCustomer(UserDetailsResponse customer) {
        this.customer = customer;
    }

    public OrderDetailsResponse getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetailsResponse orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<OrderStatusHistoryResponse> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatusHistoryResponse> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public List<SellerOrderResponse> getSellerOrders() {
        return sellerOrders;
    }

    public void setSellerOrders(List<SellerOrderResponse> sellerOrders) {
        this.sellerOrders = sellerOrders;
    }
}
