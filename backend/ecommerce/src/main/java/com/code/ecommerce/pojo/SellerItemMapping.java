package com.code.ecommerce.pojo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "seller_item_mapping")
public class SellerItemMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;  // id of the merchant associated

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;  // id of the item associated

    @Column(name = "amount", precision = 15, scale = 2)
    private Double amount;   // the unit cost of the item before tax
    private Float cgst;
    private Float sgst;
    private Float igst;
    private Float vat;
    private Float cess;

    @Column(name = "total_cost", precision = 15, scale = 2)
    private Double totalCost;  // the total amount after all the taxes added up

    @Column(name = "available_quantity")
    private Double availableQuantity;  // available quantity for the inventory

    @Column(name = "is_active")
    private Boolean active = true;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    public SellerItemMapping(){}

    public SellerItemMapping(Long id, Seller seller, Item item, Double amount, Float cgst,
                             Float sgst, Float igst, Float vat, Float cess,
                             Double totalCost, Double availableQuantity, Boolean active,
                             LocalDateTime createdOn, LocalDateTime modifiedOn) {
        this.id = id;
        this.seller = seller;
        this.item = item;
        this.amount = amount;
        this.cgst = cgst;
        this.sgst = sgst;
        this.igst = igst;
        this.vat = vat;
        this.cess = cess;
        this.totalCost = totalCost;
        this.availableQuantity = availableQuantity;
        this.active = active;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Double availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
