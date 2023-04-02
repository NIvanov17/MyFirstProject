package com.example.myfirstproject.model.DTOs.brand;

import com.example.myfirstproject.model.DTOs.model.ModelDTO;

import java.util.ArrayList;
import java.util.List;

public class BrandDTO {

    private long id;

    private String name;

    private List<ModelDTO> models;

    public String getName() {
        return name;
    }

    public BrandDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public BrandDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BrandDTO setModels(List<ModelDTO> models) {
        this.models = models;
        return this;
    }

    public List<ModelDTO> getModels() {
        return models;
    }


    public long getId() {
        return id;
    }


    public BrandDTO addModel(ModelDTO model) {
        if (this.models == null) {
            this.models = new ArrayList<>();
        }
        this.models.add(model);
        return this;
    }
}
