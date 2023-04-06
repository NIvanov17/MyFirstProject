package com.example.myfirstproject.model.views;

import java.math.BigDecimal;

public class OfferDetailsView {

    private  long id;

    private String brand;

    private String model;

    private String picture;

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

    public OfferDetailsView() {
    }

    public OfferDetailsView(long id, String brand, String model, String picture, String sellerFirstName, String sellerLastName, BigDecimal price, int horsePower, String engine, String transmission, int year, int mileage, String description, String telephoneNumber) {
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

    public OfferDetailsView setId(long id) {
        this.id = id;
        return this;
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

    public OfferDetailsView setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public OfferDetailsView setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
        return this;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public OfferDetailsView setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDetailsView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public OfferDetailsView setHorsePower(int horsePower) {
        this.horsePower = horsePower;
        return this;
    }

    public String getEngine() {
        return engine;
    }

    public OfferDetailsView setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    public String getTransmission() {
        return transmission;
    }

    public OfferDetailsView setTransmission(String transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferDetailsView setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public OfferDetailsView setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public OfferDetailsView setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
        return this;
    }
}
