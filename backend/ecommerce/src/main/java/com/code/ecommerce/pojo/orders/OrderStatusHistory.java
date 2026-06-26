package com.code.ecommerce.pojo.orders;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_status_history")
public class OrderStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;   // the id of the associated order

    private String status;  // the status related to the order

    @Column(name = "status_date")
    private LocalDateTime statusDate;  // the dates of the status being recorded

    private String remarks;  // any remarks

    @Column(name = "modified_by")
    private String modifiedBy;

    public OrderStatusHistory(){}

    public OrderStatusHistory(Long id, Order order, LocalDateTime statusDate, String status, String remarks, String modifiedBy) {
        this.id = id;
        this.order = order;
        this.statusDate = statusDate;
        this.status = status;
        this.remarks = remarks;
        this.modifiedBy = modifiedBy;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(LocalDateTime statusDate) {
        this.statusDate = statusDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}