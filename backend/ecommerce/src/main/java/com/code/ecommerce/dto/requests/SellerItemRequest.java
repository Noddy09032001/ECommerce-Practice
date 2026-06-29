package com.code.ecommerce.dto.requests;

public class SellerItemRequest {
    private Long sellerId;   // the id of the merchant or the seller
    private Double amount;  // the initial amount before taxes
    private Float otherCharges;  // if any other charges
    private Float transportationCharges;  // transportation charges specified by the merchant

    public SellerItemRequest(){}

    public SellerItemRequest(Long sellerId, Double amount, Float transportationCharges, Float otherCharges) {
        this.sellerId = sellerId;
        this.amount = amount;
        this.transportationCharges = transportationCharges;
        this.otherCharges = otherCharges;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Float getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Float otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Float getTransportationCharges() {
        return transportationCharges;
    }

    public void setTransportationCharges(Float transportationCharges) {
        this.transportationCharges = transportationCharges;
    }
}
