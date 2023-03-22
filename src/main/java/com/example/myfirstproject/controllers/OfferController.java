package com.example.myfirstproject.controllers;

import com.example.myfirstproject.model.DTOs.AddOfferDTO;
import com.example.myfirstproject.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/add-offer")
    public String addOffer() {
        return "add-offer";
    }

    @PostMapping("add-offer")
    public String addOffer(@Valid AddOfferDTO addOfferDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @RequestParam("file")MultipartFile file) throws IOException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);

            return "redirect:/add-offer";
        }

        this.offerService.addOffer(addOfferDTO, file);

        return "redirect:/all-offers";
    }

    @ModelAttribute
    public AddOfferDTO addOfferDTO() {
        return new AddOfferDTO();
    }

    @GetMapping("/offer/{id}")
    public String offerDetails() {
        return "offer";
    }

    @GetMapping("/all-offers")
    public String allOffers() {
        return "all-offers";
    }

}
