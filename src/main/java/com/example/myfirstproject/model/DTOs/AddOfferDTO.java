package com.example.myfirstproject.model.DTOs;

import com.example.myfirstproject.model.enums.EngineEnum;
import com.example.myfirstproject.model.enums.TransmissionEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class AddOfferDTO {

    @NotNull
    @Size(min = 2)
    private String brand;

    @NotBlank
    private String name;

    @NotNull
    @Size(min = 1)
    private String model;

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

    public AddOfferDTO(String brand, String model, BigDecimal price, int horsePower, String engine, String transmission, int year, int mileage, String description, String picture) {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
