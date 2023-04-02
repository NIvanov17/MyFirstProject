package com.example.myfirstproject.service;


import com.example.myfirstproject.model.BrandEntity;
import com.example.myfirstproject.model.DTOs.offer.AddOfferDTO;
import com.example.myfirstproject.model.ModelEntity;
import com.example.myfirstproject.model.OfferEntity;
import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.enums.EngineEnum;
import com.example.myfirstproject.model.enums.TransmissionEnum;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.model.views.OfferDetailsView;
import com.example.myfirstproject.repository.BrandRepository;
import com.example.myfirstproject.repository.ModelRepository;
import com.example.myfirstproject.repository.OfferRepository;
import com.example.myfirstproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private UserService userService;
    private final UserRepository userRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public OfferService(OfferRepository offerRepository, ModelMapper modelMapper, UserService userService, UserRepository userRepository, BrandRepository brandRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }


    public void addOffer(Principal principal, AddOfferDTO addOfferDTO, MultipartFile file) throws IOException {

        addOfferDTO.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
        TransmissionEnum transmissionEnum = TransmissionEnum.valueOf(addOfferDTO.getTransmission());
        EngineEnum engineEnum = EngineEnum.valueOf(addOfferDTO.getEngine());
        ModelEntity modelById = this.modelRepository.findById(addOfferDTO.getModel()).orElseThrow();

        OfferEntity offer = this.modelMapper.map(addOfferDTO, OfferEntity.class);

        offer.setSeller(this.userService.getUserByUsername(principal.getName()));
        offer.setEngine(engineEnum);
        offer.setTransmission(transmissionEnum);
        offer.setModel(modelById);
        offer.setBrand(modelById.getBrand());

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
                offer.getBrand().getName(),
                offer.getModel().getName(),
                offer.getPicture(),
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
        UserEntity owner = offer.getSeller();

        if (!isOwner(id, principal)) {
            return;
        }
        userService.disLikeOffer(principal, offer);

        offerRepository.delete(offer);
    }

    public boolean isOwner(Long id, Principal principal) {
        UserEntity currentUser = userService.getUserByUsername(principal.getName());

        boolean isOwner = this.offerRepository.findById(id)
                .filter(o -> o.getSeller().equals(currentUser))
                .isPresent();

        if (isOwner) {
            return true;
        }

        return userRepository
                .findByUsername(principal.getName())
                .filter(this::isAdmin)
                .isPresent();
    }

    public boolean isAdmin(UserEntity user) {
        return user.getRoles()
                .stream()
                .anyMatch(r -> r.getRole() == UserRoleEnum.ADMIN);
    }
}
