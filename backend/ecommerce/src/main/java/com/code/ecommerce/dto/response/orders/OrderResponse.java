package com.code.ecommerce.dto.response.orders;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {

    private String orderId;
    private LocalDateTime orderDate;
    private String currency;
    private Double grandTotal;
    private List<OrderItemPayResponse> items;

    public OrderResponse(){}

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<OrderItemPayResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemPayResponse> items) {
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
