package com.example.myfirstproject.model.DTOs.offer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class AddOfferDTO {

    @NotNull
    private long brand;

    @NotNull
    private long model;


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

    public AddOfferDTO setBrand(long brand) {
        this.brand = brand;
        return this;
    }

    public Long getModel() {
        return model;
    }

    public AddOfferDTO setModel(long model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public AddOfferDTO setHorsePower(int horsePower) {
        this.horsePower = horsePower;
        return this;
    }

    public String getEngine() {
        return engine;
    }

    public AddOfferDTO setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    public String getTransmission() {
        return transmission;
    }

    public AddOfferDTO setTransmission(String transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public AddOfferDTO setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public AddOfferDTO setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public AddOfferDTO setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}
