package com.code.ecommerce.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class ItemResponse {

    private Long id;  // the id of the item
    private String name;  // the name of the item
    private String description;  // the description of the item
    private String category;  // the category under which the item is present
    private String sku;  // the stock keeping unit of the item for inventory
    private Boolean active;  // is the item currently active
    private LocalDateTime createdOn;  // item creation date
    private LocalDateTime modifiedOn;  // item modification date
    private Float cgst;  // the cgst tax value of the item
    private Float sgst;  // the sgst tax value of the item
    private Float igst;  // the igst tax value of the item
    private Float vat;  // the vat tax value of the item
    private Float cess;  // the cess tax value of the item
    private List<SellerItemResponse> merchants;  // the item merchant mapping details

    public ItemResponse(){}

    public ItemResponse(Long id, String name, String description, String category, String sku, Boolean active, LocalDateTime createdOn, LocalDateTime modifiedOn,
                        Float sgst, Float cgst, Float igst, Float vat, Float cess, List<SellerItemResponse> merchants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.sku = sku;
        this.active = active;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.sgst = sgst;
        this.cgst = cgst;
        this.igst = igst;
        this.vat = vat;
        this.cess = cess;
        this.merchants = merchants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public Float getCgst() {
        return cgst;
    }

    public void setCgst(Float cgst) {
        this.cgst = cgst;
    }

    public Float getSgst() {
        return sgst;
    }

    public void setSgst(Float sgst) {
        this.sgst = sgst;
    }

    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public Float getIgst() {
        return igst;
    }

    public void setIgst(Float igst) {
        this.igst = igst;
    }

    public Float getCess() {
        return cess;
    }

    public void setCess(Float cess) {
        this.cess = cess;
    }

    public List<SellerItemResponse> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<SellerItemResponse> merchants) {
        this.merchants = merchants;
    }
}
