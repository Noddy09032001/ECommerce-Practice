package com.code.ecommerce.dto.requests.orders;

// contains the details of the items in the order request body
public class OrderItemRequest {

    private Long id;  // the id of the item
    private Double quantity;  // the total quantity of the item
    private Long sellerId;   // the id of the seller who is selling the item

    public OrderItemRequest(){}

    public OrderItemRequest(Long id, Double quantity, Long sellerId) {
        this.id = id;
        this.quantity = quantity;
        this.sellerId = sellerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
