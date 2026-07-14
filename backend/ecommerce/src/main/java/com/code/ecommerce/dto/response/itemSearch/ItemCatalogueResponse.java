package com.code.ecommerce.dto.response.itemSearch;

public class ItemCatalogueResponse {

    private Long itemPk;
    private String itemId;
    private String itemName;
    private String category;
    private Long sellerPk;
    private String sellerId;
    private String sellerName;
    private Double price;
    private Integer quantity;

    public ItemCatalogueResponse(){}

    public ItemCatalogueResponse(Long itemPk, String itemId, String itemName, String category, Long sellerPk, String sellerId,
                                 String sellerName, Double price, Integer quantity) {
        this.itemPk = itemPk;
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.sellerPk = sellerPk;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getItemPk() {
        return itemPk;
    }

    public void setItemPk(Long itemPk) {
        this.itemPk = itemPk;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getSellerPk() {
        return sellerPk;
    }

    public void setSellerPk(Long sellerPk) {
        this.sellerPk = sellerPk;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}
