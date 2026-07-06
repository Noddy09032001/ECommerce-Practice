package com.code.ecommerce.dto.response.orderSearch;

import java.time.LocalDateTime;

// generates the response for the statuses of the current order
public class OrderStatusHistoryResponse {

    private String status;   // the current status of the order
    private String remarks;  // remarks if any
    private LocalDateTime createdOn;  // the date of the current status
    private String createdBy;

    public OrderStatusHistoryResponse() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
