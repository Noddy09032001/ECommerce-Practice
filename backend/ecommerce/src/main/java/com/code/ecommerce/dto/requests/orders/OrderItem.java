package com.code.ecommerce.dto.requests.orders;

// contains the details of the items in the order request body
public class OrderItem {

    private Long id;  // the id of the item
    private String name;  // the name of the item
    private String description;  // the item description
    private double quantity;  // the total quantity of the item
    private double amount;  // the total amount of the item

    public OrderItem(){}

    public OrderItem(Long id, String name, double quantity, String description, double amount) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.amount = amount;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
