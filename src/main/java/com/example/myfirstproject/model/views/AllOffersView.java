package com.example.myfirstproject.model.views;

import java.math.BigDecimal;

public class AllOffersView {
    private Long id;

    private String name;

    private String picture;

    private BigDecimal price;

    private String description;

    public AllOffersView(Long id, String name, String picture, BigDecimal price, String description) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
