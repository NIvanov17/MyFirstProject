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

    public OfferEntity setId(long id) {
        this.id = id;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public OfferEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }

    public ModelEntity getModel() {
        return model;
    }

    public OfferEntity setModel(ModelEntity model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public OfferEntity setHorsePower(int horsePower) {
        this.horsePower = horsePower;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferEntity setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferEntity setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferEntity setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public OfferEntity setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public OfferEntity setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }
}
