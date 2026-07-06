package com.code.ecommerce.dto.requests.orderSearch;

import java.time.LocalDateTime;

public class OrderSearchRequest {

    private String userName;
    private String email;
    private String searchText;   // contains the order id to be search
    private Double minOrderAmount;  // contains the min order amount
    private Double maxOrderAmount;   // contains the max order amount
    private LocalDateTime fromDate;   // contains the from date
    private LocalDateTime toDate;  // contains the to date
    private String sortBy; // PRICE_ASC, PRICE_DESC, NAME
    private Integer page = 0;  // current page
    private Integer size = 20;  // max items per page

    public OrderSearchRequest(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Double getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(Double minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }

    public Double getMaxOrderAmount() {
        return maxOrderAmount;
    }

    public void setMaxOrderAmount(Double maxOrderAmount) {
        this.maxOrderAmount = maxOrderAmount;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
