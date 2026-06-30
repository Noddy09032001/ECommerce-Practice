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

    @Column(name = "amount_without_tax")
    private Double amountWithoutTax;  // the amount of one item

    @Column(name = "cgst_amount")
    private Double cgstAmount;  // the amount of the cgst tax

    @Column(name = "sgst_amount")
    private Double sgstAmount;  // the amount of the sgst tax

    @Column(name = "igst_amount")
    private Double igstAmount;  // the amount of the igst tax

    @Column(name = "vat_amount")
    private Double vatAmount;  // the amount of the vat tax

    @Column(name = "cess_amount")
    private Double cessAmount;  // the amount of the cess tax

    @Column(nullable = false)
    private Double quantity;  // the quantity purchased

    @Column(name = "total_amount")
    private Double totalAmount;  // the total cost of one item

    @Column(name = "created_on")
    private LocalDateTime createdOn;  // storing the creation date for the order

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;   // storing the modified date for the order

    public OrderItemDetails(){}

    public OrderItemDetails(Long id, Order order, Item item, Double amountWithoutTax, Double cgstAmount, Double sgstAmount,
                            Double igstAmount, Double cessAmount, Double vatAmount, Double quantity, LocalDateTime createdOn,
                            Double totalAmount, LocalDateTime modifiedOn) {
        this.id = id;
        this.order = order;
        this.item = item;
        this.amountWithoutTax = amountWithoutTax;
        this.cgstAmount = cgstAmount;
        this.sgstAmount = sgstAmount;
        this.igstAmount = igstAmount;
        this.cessAmount = cessAmount;
        this.vatAmount = vatAmount;
        this.quantity = quantity;
        this.createdOn = createdOn;
        this.totalAmount = totalAmount;
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

    public Double getAmountWithoutTax() {
        return amountWithoutTax;
    }

    public void setAmountWithoutTax(Double amountWithoutTax) {
        this.amountWithoutTax = amountWithoutTax;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
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

    public Double getCgstAmount() {
        return cgstAmount;
    }

    public void setCgstAmount(Double cgstAmount) {
        this.cgstAmount = cgstAmount;
    }

    public Double getSgstAmount() {
        return sgstAmount;
    }

    public void setSgstAmount(Double sgstAmount) {
        this.sgstAmount = sgstAmount;
    }

    public Double getIgstAmount() {
        return igstAmount;
    }

    public void setIgstAmount(Double igstAmount) {
        this.igstAmount = igstAmount;
    }

    public Double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public Double getCessAmount() {
        return cessAmount;
    }

    public void setCessAmount(Double cessAmount) {
        this.cessAmount = cessAmount;
    }
}
