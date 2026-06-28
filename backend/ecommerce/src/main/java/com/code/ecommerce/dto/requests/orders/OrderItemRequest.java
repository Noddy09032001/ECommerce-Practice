package com.code.ecommerce.dto.requests.orders;

// contains the details of the items in the order request body
public class OrderItemRequest {

    private Long id;  // the id of the item
    private double quantity;  // the total quantity of the item

    public OrderItemRequest(){}

    public OrderItemRequest(Long id, double quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
