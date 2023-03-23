package com.example.myfirstproject.controllers;

import com.example.myfirstproject.model.DTOs.AddOfferDTO;
import com.example.myfirstproject.model.OfferEntity;
import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.views.AllOffersView;
import com.example.myfirstproject.model.views.OfferDetailsView;
import com.example.myfirstproject.service.OfferService;
import com.example.myfirstproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OfferController {

    private final OfferService offerService;

    private final UserService userService;

    @Autowired
    public OfferController(OfferService offerService, UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @GetMapping("/add-offer")
    public String addOffer() {
        return "add-offer";
    }

    @PostMapping("add-offer")
    public String addOffer(Principal principal,
                           @Valid AddOfferDTO addOfferDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @RequestParam("file") MultipartFile file) throws IOException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);

            return "redirect:/add-offer";
        }

        this.offerService.addOffer(principal,addOfferDTO, file);

        return "redirect:/all-offers";
    }

    @ModelAttribute
    public AddOfferDTO addOfferDTO() {
        return new AddOfferDTO();
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(@PathVariable("id") Long id,
                               Model model) {
        OfferEntity offer = this.offerService.getOfferById(id);
        OfferDetailsView offerDetailsView = new OfferDetailsView(
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

        model.addAttribute("offer",offerDetailsView);

        return "offer";
    }

    @GetMapping("/all-offers")
    public String allOffers(Model model) {

        List<OfferEntity> allOffers = this.offerService.getAllOffers();

        allOffers.stream().map(o -> new AllOffersView(
                o.getId(),
                o.getName(),
                o.getPicture(),
                o.getPrice(),
                o.getDescription()
        )).collect(Collectors.toList());

        model.addAttribute("allOffers", allOffers);

        return "allOffers";
    }

    @GetMapping("/liked-offers")
    public String likedOffers(Principal principal,Model model){

        List<OfferEntity> likedOffers = this.userService.getUserByUsername(principal.getName()).getLikedOffers();

        likedOffers.stream().map(l->new AllOffersView(
                l.getId(),
                l.getName(),
                l.getPicture(),
                l.getPrice(),
                l.getDescription()
        )).collect(Collectors.toList());

        model.addAttribute("likedOffers",likedOffers);

        return "likedOffers";
    }

    @GetMapping("offer/like/{id}")
    public String likeOffer(Principal principal,
                          @PathVariable("id")Long id){
        OfferEntity offer = offerService.getOfferById(id);

        this.userService.likeOffer(principal,offer);

        return "redirect:/liked-offers";

    }
}
