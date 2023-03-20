package com.example.myfirstproject.service;


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
