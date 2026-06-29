package com.code.ecommerce.dto.requests;

import java.util.List;

public class ItemRequest {

    private String name;  // the name of the item
    private String description;  // the description of the item
    private String category;  // the category of the item
    private List<SellerItemRequest> sellers;  // list of sellers and the corresponding amounts

    private Float cgst;
    private Float sgst;
    private Float igst;
    private Float vat;
    private Float cess;

    private Integer quantity;  // the quantity of the item available

    public ItemRequest(){}

    public ItemRequest(String name, String description, String category, List<SellerItemRequest> sellers,
                       Float cgst, Float sgst, Float vat, Float igst, Float cess, Integer quantity) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.sellers = sellers;
        this.cgst = cgst;
        this.sgst = sgst;
        this.vat = vat;
        this.igst = igst;
        this.cess = cess;
        this.quantity = quantity;
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

    public List<SellerItemRequest> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerItemRequest> sellers) {
        this.sellers = sellers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Float getIgst() {
        return igst;
    }

    public void setIgst(Float igst) {
        this.igst = igst;
    }

    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public Float getCess() {
        return cess;
    }

    public void setCess(Float cess) {
        this.cess = cess;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
