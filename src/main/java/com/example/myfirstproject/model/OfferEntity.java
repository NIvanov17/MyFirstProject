package com.example.myfirstproject.model;

import com.example.myfirstproject.model.enums.EngineEnum;
import com.example.myfirstproject.model.enums.TransmissionEnum;
import com.example.myfirstproject.repository.OfferRepository;
import com.example.myfirstproject.service.OfferService;
import jakarta.persistence.*;


import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "offers")
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    private BrandEntity brand;

    @ManyToOne
    private ModelEntity model;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "horse-power", nullable = false)
    private int horsePower;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineEnum engine;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionEnum transmission;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int mileage;

    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "LONGTEXT")
    private String picture;

    @ManyToOne
    private UserEntity seller;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
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

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
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

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }
}
