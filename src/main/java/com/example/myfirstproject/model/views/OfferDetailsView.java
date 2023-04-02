package com.example.myfirstproject.model.views;

import java.math.BigDecimal;

public class OfferDetailsView {

    private  long id;

    private String brand;

    private String model;

    private String picture;

    private String name;

    private String sellerFirstName;

    private String sellerLastName;

    private BigDecimal price;

    private int horsePower;

    private String engine;

    private String transmission;

    private int year;

    private int mileage;

    private String description;

    private String telephoneNumber;


    public OfferDetailsView(long id, String brand, String model, String picture, String sellerFirstName,String sellerLastName, BigDecimal price, int horsePower, String engine, String transmission, int year, int mileage, String description, String telephoneNumber) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.picture = picture;
        this.sellerFirstName = sellerFirstName;
        this.sellerLastName = sellerLastName;
        this.price = price;
        this.horsePower = horsePower;
        this.engine = engine;
        this.transmission = transmission;
        this.year = year;
        this.mileage = mileage;
        this.description = description;
        this.telephoneNumber = telephoneNumber;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public OfferDetailsView setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferDetailsView setModel(String model) {
        this.model = model;
        return this;
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

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public void setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public void setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
