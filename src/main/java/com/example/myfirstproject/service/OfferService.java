package com.example.myfirstproject.service;

import com.example.myfirstproject.model.OfferEntity;
import com.example.myfirstproject.model.enums.EngineEnum;
import com.example.myfirstproject.model.enums.TransmissionEnum;
import com.example.myfirstproject.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }



}
