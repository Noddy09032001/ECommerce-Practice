package com.code.ecommerce.dto.requests.payments;

import java.util.List;

public class PaymentRequest {
    private String orderId;   // order id for the current order
    private List<PaymentItem> items;  // the list of the items inside the current order

    public PaymentRequest(){}

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<PaymentItem> getItems() {
        return items;
    }

    public void setItems(List<PaymentItem> items) {
        this.items = items;
    }
}
