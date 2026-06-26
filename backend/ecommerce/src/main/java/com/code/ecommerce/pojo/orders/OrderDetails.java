package com.code.ecommerce.pojo.orders;

import com.code.ecommerce.common.constants.PaymentMethods;
import com.code.ecommerce.pojo.UserDetails;
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
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetails user;   // the order is for which user

    private double amount;   // the amount before the taxes
    private float cgst;
    private float sgst;
    private float igst;
    private float vat;
    private float cess;

    @Column(name = "tax_amount")
    private double totalTaxInAmount;   // the amount of the tax being paid

    @Column(name = "total_order_amount")
    private double totalOrderAmount;  // the total amount of the order including taxes

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

    public OrderDetails(Long id, String orderDetailsId, Order order, UserDetails user, double amount, float cgst,
                        float sgst, float vat, float igst, double totalTaxInAmount, float cess, double totalOrderAmount,
                        PaymentMethods paymentMode, LocalDateTime createdOn, LocalDateTime modifiedOn, String city,
                        String state, String address) {
        this.id = id;
        this.orderDetailsId = orderDetailsId;
        this.order = order;
        this.user = user;
        this.amount = amount;
        this.cgst = cgst;
        this.sgst = sgst;
        this.vat = vat;
        this.igst = igst;
        this.totalTaxInAmount = totalTaxInAmount;
        this.cess = cess;
        this.totalOrderAmount = totalOrderAmount;
        this.paymentMode = paymentMode;
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

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
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

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public float getIgst() {
        return igst;
    }

    public void setIgst(float igst) {
        this.igst = igst;
    }

    public float getCess() {
        return cess;
    }

    public void setCess(float cess) {
        this.cess = cess;
    }

    public double getTotalTaxInAmount() {
        return totalTaxInAmount;
    }

    public void setTotalTaxInAmount(double totalTaxInAmount) {
        this.totalTaxInAmount = totalTaxInAmount;
    }

    public double getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(double totalOrderAmount) {
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
}
