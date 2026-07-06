package com.code.ecommerce.dto.requests;

import java.util.List;

public class ItemSearchRequest {

    private String searchText;
    private List<String> categories;
    private List<String> sellerIds;
    private Double minPrice;
    private Double maxPrice;
    private String sortBy; // PRICE_ASC, PRICE_DESC, NAME
    private Integer page = 0;
    private Integer size = 20;

    public ItemSearchRequest(){}

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getSellerIds() {
        return sellerIds;
    }

    public void setSellerIds(List<String> sellerIds) {
        this.sellerIds = sellerIds;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
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
