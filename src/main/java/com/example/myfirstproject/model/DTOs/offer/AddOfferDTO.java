package com.example.myfirstproject.model.DTOs.offer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class AddOfferDTO {

    @NotNull
    private Long brand;

    @NotNull
    private Long model;


    @NotNull
    private BigDecimal price;

    @NotNull
    @Positive
    private int horsePower;


    private String engine;


    private String transmission;

    @NotNull
    @Positive
    private int year;

    @NotNull
    @Positive
    private int mileage;

    @NotBlank
    private String description;

    private String picture;

    public AddOfferDTO() {
    }

    public AddOfferDTO(long brand, Long model, BigDecimal price, int horsePower, String engine, String transmission, int year, int mileage, String description, String picture) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.horsePower = horsePower;
        this.engine = engine;
        this.transmission = transmission;
        this.year = year;
        this.mileage = mileage;
        this.description = description;
        this.picture = picture;
    }

    public Long getBrand() {
        return brand;
    }

    public AddOfferDTO setBrand(Long brand) {
        this.brand = brand;
        return this;
    }

    public Long getModel() {
        return model;
    }

    public void setModel(Long model) {
        this.model = model;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
