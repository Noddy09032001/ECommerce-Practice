package com.code.ecommerce.pojo.orders;

import com.code.ecommerce.pojo.Item;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_item_details")
public class OrderItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private double amount;  // the amount of one item

    @Column(nullable = false)
    private Double quantity;  // the quantity purchased

    @Column(name = "total_amount")
    private double totalAmount;  // the total cost of one item

    @Column(name = "created_on")
    private LocalDateTime createdOn;  // storing the creation date for the order

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;   // storing the modified date for the order

    public OrderItemDetails(){}

    public OrderItemDetails(Long id, Order order, Item item, double amount, Double quantity, double totalAmount,
                            LocalDateTime createdOn, LocalDateTime modifiedOn) {
        this.id = id;
        this.order = order;
        this.item = item;
        this.amount = amount;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
