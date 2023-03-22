package com.example.myfirstproject.service;


import com.example.myfirstproject.model.DTOs.AddOfferDTO;
import com.example.myfirstproject.model.OfferEntity;
import com.example.myfirstproject.model.enums.EngineEnum;
import com.example.myfirstproject.model.enums.TransmissionEnum;
import com.example.myfirstproject.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    public OfferService(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }


    public void addOffer(AddOfferDTO addOfferDTO, MultipartFile file) throws IOException {

        addOfferDTO.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
        TransmissionEnum transmissionEnum = TransmissionEnum.valueOf(addOfferDTO.getTransmission());
        EngineEnum engineEnum = EngineEnum.valueOf(addOfferDTO.getEngine());

        OfferEntity offer = this.modelMapper.map(addOfferDTO, OfferEntity.class);

        offer.setEngine(engineEnum);
        offer.setTransmission(transmissionEnum);

        this.offerRepository.save(offer);
    }
}
