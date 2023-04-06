package com.example.myfirstproject.model.views;

import java.math.BigDecimal;

public class AllOffersView {
    private long id;

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

    public AllOffersView() {
    }

    public Long getId() {
        return id;
    }

    public AllOffersView setId(long id) {
        this.id = id;
        return this;
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

    public AllOffersView setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AllOffersView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AllOffersView setDescription(String description) {
        this.description = description;
        return this;
    }
}
