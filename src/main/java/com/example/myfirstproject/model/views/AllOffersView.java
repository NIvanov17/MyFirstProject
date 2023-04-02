package com.example.myfirstproject.model.views;

import java.math.BigDecimal;

public class AllOffersView {
    private Long id;

    private String brand;

    private String model;

    private String picture;

    private BigDecimal price;

    private String description;

    public AllOffersView(Long id, String brand, String model, String picture, BigDecimal price, String description) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.picture = picture;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public AllOffersView setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public AllOffersView setModel(String model) {
        this.model = model;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
