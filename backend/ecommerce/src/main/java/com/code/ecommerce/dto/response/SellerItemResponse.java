package com.code.ecommerce.dto.response;

public class SellerItemResponse {

    private Long id;  // the id of the merchant
    private String name;   // name of the merchant
    private Double amount;  // the amount before taxes
    private Double finalAmount;  // the amount after taxes
    private Float otherCharges;  // other charges
    private Float transportationCharges;  // transportation charges for the merchant
    private Integer quantity;  // item quantity available at the merchant

    public SellerItemResponse(){}

    public SellerItemResponse(Long id, String name, Double amount, Double finalAmount, Float otherCharges,
                              Float transportationCharges, Integer quantity) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.finalAmount = finalAmount;
        this.otherCharges = otherCharges;
        this.transportationCharges = transportationCharges;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
