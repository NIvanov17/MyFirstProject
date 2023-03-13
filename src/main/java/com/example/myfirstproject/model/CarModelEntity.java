package com.example.myfirstproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "models")
public class CarModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private CarBrandEntity brand;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarBrandEntity getBrand() {
        return brand;
    }

    public void setBrand(CarBrandEntity brand) {
        this.brand = brand;
    }
}
