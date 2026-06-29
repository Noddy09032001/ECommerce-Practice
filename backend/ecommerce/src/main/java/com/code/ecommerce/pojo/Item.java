package com.code.ecommerce.pojo;

import com.code.ecommerce.pojo.orders.OrderItemDetails;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item_details")
public class Item {

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

    @Column(name = "created_by")
    private String createdBy;  // data for the item creator

    @Column(name = "modified_on")
    private String modifiedBy;  // storing the data for item modifier

    private Float cgst;
    private Float sgst;
    private Float igst;
    private Float vat;
    private Float cess;

    @Column(name = "sku", unique = true)
    private String sku;  // the sku for inventory

    @Column(name = "is_active")
    private Boolean active = true;  // is the item active

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemDetails> orderItems = new ArrayList<>();

    // merchants or sellers associated with selling the item
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SellerItemMapping> sellers = new ArrayList<>();

    public Item(){}

    public Item(Long id, String itemName, String itemId, String itemDescription, LocalDateTime createdOn,
                LocalDateTime modifiedOn, String createdBy, String modifiedBy, Float sgst, Float cgst, Float igst,
                Float vat, Float cess, String sku, Boolean active, List<OrderItemDetails> orderItems,
                List<SellerItemMapping> sellers) {
        this.id = id;
        this.itemName = itemName;
        this.itemId = itemId;
        this.itemDescription = itemDescription;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.sgst = sgst;
        this.cgst = cgst;
        this.igst = igst;
        this.vat = vat;
        this.cess = cess;
        this.sku = sku;
        this.active = active;
        this.orderItems = orderItems;
        this.sellers = sellers;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public List<SellerItemMapping> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerItemMapping> sellers) {
        this.sellers = sellers;
    }

    public Float getCgst() {
        return cgst;
    }

    public void setCgst(Float cgst) {
        this.cgst = cgst;
    }

    public Float getSgst() {
        return sgst;
    }

    public void setSgst(Float sgst) {
        this.sgst = sgst;
    }

    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public Float getIgst() {
        return igst;
    }

    public void setIgst(Float igst) {
        this.igst = igst;
    }

    public Float getCess() {
        return cess;
    }

    public void setCess(Float cess) {
        this.cess = cess;
    }
}
