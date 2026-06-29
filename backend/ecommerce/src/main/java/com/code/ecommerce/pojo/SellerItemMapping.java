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

    @Column(name = "other_charges")
    private Float otherCharges;  // if any other charges

    @Column(name = "transportation_charges")
    private Float transportationCharges;  // transportation charges specified by the merchant

    @Column(name = "total_cost", precision = 15, scale = 2)
    private Double totalCost;  // the total amount after all the taxes added up

    @Column(name = "available_quantity")
    private Integer availableQuantity;  // available quantity for the inventory

    @Column(name = "is_active")
    private Boolean active = true;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    public SellerItemMapping(){}

    public SellerItemMapping(Long id, Seller seller, Item item, Double amount, Float otherCharges,
                             Float transportationCharges, Double totalCost, Integer availableQuantity,
                             Boolean active, LocalDateTime createdOn, LocalDateTime modifiedOn) {
        this.id = id;
        this.seller = seller;
        this.item = item;
        this.amount = amount;
        this.otherCharges = otherCharges;
        this.transportationCharges = transportationCharges;
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

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
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
