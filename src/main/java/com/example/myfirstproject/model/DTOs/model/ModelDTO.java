package com.example.myfirstproject.model.DTOs.model;

public class ModelDTO {

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public ModelDTO setId(long id) {
        this.id = id;
        return this;
    }

    public ModelDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }


}
