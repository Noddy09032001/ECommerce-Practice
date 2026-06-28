package com.code.ecommerce.dto.requests.orders;

import com.code.ecommerce.common.constants.PaymentMethods;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRequest {

    private LocalDateTime orderDate;  // date of the current order
    private List<OrderItemRequest> items;  // items in the request body
    private PaymentMethods paymentMode;  // mode of the payment
    private AddressDetails address;  // address of the user
    private OrderUserRequest user;  // details of the current user

    public OrderRequest(){}

    public OrderRequest(LocalDateTime orderDate, List<OrderItemRequest> items, PaymentMethods paymentMode,
                        AddressDetails address, OrderUserRequest user) {
        this.orderDate = orderDate;
        this.items = items;
        this.paymentMode = paymentMode;
        this.address = address;
        this.user = user;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public PaymentMethods getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMethods paymentMode) {
        this.paymentMode = paymentMode;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    public AddressDetails getAddress() {
        return address;
    }

    public void setAddress(AddressDetails address) {
        this.address = address;
    }

    public OrderUserRequest getUser() {
        return user;
    }

    public void setUser(OrderUserRequest user) {
        this.user = user;
    }
}
