package com.example.myfirstproject.service;


import com.example.myfirstproject.model.DTOs.AddOfferDTO;
import com.example.myfirstproject.model.OfferEntity;
import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.enums.EngineEnum;
import com.example.myfirstproject.model.enums.TransmissionEnum;
import com.example.myfirstproject.model.views.OfferDetailsView;
import com.example.myfirstproject.repository.OfferRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private UserService userService;

    public OfferService(OfferRepository offerRepository, ModelMapper modelMapper, UserService userService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    public void addOffer(Principal principal, AddOfferDTO addOfferDTO, MultipartFile file) throws IOException {

        addOfferDTO.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
        TransmissionEnum transmissionEnum = TransmissionEnum.valueOf(addOfferDTO.getTransmission());
        EngineEnum engineEnum = EngineEnum.valueOf(addOfferDTO.getEngine());

        OfferEntity offer = this.modelMapper.map(addOfferDTO, OfferEntity.class);

        offer.setSeller(this.userService.getUserByUsername(principal.getName()));
        offer.setEngine(engineEnum);
        offer.setTransmission(transmissionEnum);

        this.offerRepository.save(offer);
    }

    public List<OfferEntity> getAllOffers() {
        return this.offerRepository.findAll();

    }

    public OfferEntity getOfferById(Long id) {
        return this.offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unable to find offer!"));
    }


    public OfferDetailsView getOfferDetails(Long id) {

        OfferEntity offer = this.getOfferById(id);

        return new OfferDetailsView(
                offer.getId(),
                offer.getPicture(),
                offer.getName(),
                offer.getSeller().getFirstName(),
                offer.getSeller().getLastName(),
                offer.getPrice(),
                offer.getHorsePower(),
                offer.getEngine().toString(),
                offer.getTransmission().toString(),
                offer.getYear(),
                offer.getMileage(),
                offer.getDescription(),
                offer.getSeller().getTelephoneNumber());
    }

    @Transactional
    public void deleteOffer(Long id, Principal principal) {
        OfferEntity offer = this.getOfferById(id);

            userService.disLikeOffer(principal, offer);

        offerRepository.delete(offer);
    }
}
