package com.code.ecommerce.pojo.orders;

import com.code.ecommerce.common.constants.PaymentMethods;
import com.code.ecommerce.pojo.Seller;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_details_id", nullable = false, unique = true)
    private String orderDetailsId;  // the id of the order details

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;  // seller selling the item

    private Double amount;   // the amount before the taxes

    @Column(name = "tax_amount")
    private Double totalTaxInAmount;   // the amount of the tax being paid

    @Column(name = "total_order_amount")
    private Double totalOrderAmount;  // the total amount of the order including taxes

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode")
    private PaymentMethods paymentMode;  // the mode of payment being done (CASH, CARD, ONLINE)

    @Column(name = "created_on")
    private LocalDateTime createdOn;  // storing the creation date for the order

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;   // storing the modified date for the order

    private String city;
    private String state;
    private String address;

    public OrderDetails(){}

    public OrderDetails(Long id, String orderDetailsId, Order order, Seller seller, Double amount, Double totalTaxInAmount, PaymentMethods paymentMode,
                        Double totalOrderAmount, LocalDateTime createdOn, LocalDateTime modifiedOn, String city, String state, String address) {
        this.id = id;
        this.orderDetailsId = orderDetailsId;
        this.order = order;
        this.seller = seller;
        this.amount = amount;
        this.totalTaxInAmount = totalTaxInAmount;
        this.paymentMode = paymentMode;
        this.totalOrderAmount = totalOrderAmount;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.city = city;
        this.state = state;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Double getTotalTaxInAmount() {
        return totalTaxInAmount;
    }

    public void setTotalTaxInAmount(Double totalTaxInAmount) {
        this.totalTaxInAmount = totalTaxInAmount;
    }

    public Double getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(Double totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public PaymentMethods getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMethods paymentMode) {
        this.paymentMode = paymentMode;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
