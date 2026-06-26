package com.code.ecommerce.pojo;

import com.code.ecommerce.pojo.orders.OrderItemDetails;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item_details")
public class ItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_id", nullable = false, unique = true)
    private String itemId;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "created_on")
    private LocalDateTime createdOn;  // storing the creation date for the item

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;   // storing the modified date for the item

    private String createdBy;  // data for the item creator
    private String modifiedBy;  // storing the data for item modifier

    private double amount;   // the unit cost of the item before tax
    private float cgst;
    private float sgst;
    private float igst;
    private float vat;
    private float cess;

    @Column(name = "total_cost")
    private double totalCost;  // the total amount after all the taxes added up

    @Column(name = "available_quantity")
    private Integer availableQuantity;  // available quantity for the inventory

    @Column(name = "sku", unique = true)
    private String sku;  // the sku for inventory

    @Column(name = "is_active")
    private Boolean active = true;  // is the item active

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemDetails> orderItems = new ArrayList<>();

    public ItemDetails(){}

    public ItemDetails(Long id, String itemName, String itemId, String itemDescription, LocalDateTime createdOn,
                       LocalDateTime modifiedOn, String createdBy, String modifiedBy, double amount, float cgst,
                       float sgst, float igst, float vat, float cess, double totalCost, Integer availableQuantity,
                       String sku, Boolean active, List<OrderItemDetails> orderItems) {
        this.id = id;
        this.itemName = itemName;
        this.itemId = itemId;
        this.itemDescription = itemDescription;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.amount = amount;
        this.cgst = cgst;
        this.sgst = sgst;
        this.igst = igst;
        this.vat = vat;
        this.cess = cess;
        this.totalCost = totalCost;
        this.availableQuantity = availableQuantity;
        this.sku = sku;
        this.active = active;
        this.orderItems = orderItems;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public float getCgst() {
        return cgst;
    }

    public void setCgst(float cgst) {
        this.cgst = cgst;
    }

    public float getSgst() {
        return sgst;
    }

    public void setSgst(float sgst) {
        this.sgst = sgst;
    }

    public float getIgst() {
        return igst;
    }

    public void setIgst(float igst) {
        this.igst = igst;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public float getCess() {
        return cess;
    }

    public void setCess(float cess) {
        this.cess = cess;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<OrderItemDetails> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDetails> orderItems) {
        this.orderItems = orderItems;
    }
}
