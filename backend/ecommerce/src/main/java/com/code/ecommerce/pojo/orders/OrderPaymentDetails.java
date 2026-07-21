package com.code.ecommerce.pojo.orders;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_payment_details")
public class OrderPaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_id")
    private String paymentId;                 // internal payment id

    @Column(name = "stripe_payment_intent_id")
    private String stripePaymentIntentId;     // Stripe Payment Intent id

    @Column(name = "stripe_session_id")
    private String stripeSessionId;           // Stripe Checkout Session id

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;   // associated order

    private Double amount;                    // paid amount
    private String currency;                  // payment currency

    @Column(name = "payment_mode")
    private String paymentMode;               // CARD, UPI, COD

    @Column(name = "payment_status")
    private String paymentStatus;             // PENDING, SUCCESS, FAILED, REFUNDED

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;        // payment completion time

    @Column(name = "created_on")
    private LocalDateTime createdOn;          // creation timestamp

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;         // last modification timestamp

    public OrderPaymentDetails(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStripePaymentIntentId() {
        return stripePaymentIntentId;
    }

    public void setStripePaymentIntentId(String stripePaymentIntentId) {
        this.stripePaymentIntentId = stripePaymentIntentId;
    }

    public String getStripeSessionId() {
        return stripeSessionId;
    }

    public void setStripeSessionId(String stripeSessionId) {
        this.stripeSessionId = stripeSessionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
