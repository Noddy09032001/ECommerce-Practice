package com.code.ecommerce.dto.response.orders;

import com.code.ecommerce.dto.response.orderSearch.OrderItemResponse;

public class OrderItemPayResponse {

    private String itemId;
    private String itemName;
    private Double baseAmount;
    private Double totalAmount;
    private Double taxAmount;
    private Double quantity;
    private Double transportationCharges;
    private Double otherCharges;

    public OrderItemPayResponse(){}

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getTransportationCharges() {
        return transportationCharges;
    }

    public void setTransportationCharges(Double transportationCharges) {
        this.transportationCharges = transportationCharges;
    }

    public Double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Double otherCharges) {
        this.otherCharges = otherCharges;
    }
}
