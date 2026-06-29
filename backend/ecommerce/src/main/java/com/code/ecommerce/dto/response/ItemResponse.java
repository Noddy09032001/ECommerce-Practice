package com.code.ecommerce.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class ItemResponse {

    private String name;
    private String description;
    private String category;
    private String sku;
    private Boolean active;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private Float cgst;
    private Float sgst;
    private Float igst;
    private Float vat;
    private Float cess;
    private List<SellerItemResponse> merchants;

    public ItemResponse(){}

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
