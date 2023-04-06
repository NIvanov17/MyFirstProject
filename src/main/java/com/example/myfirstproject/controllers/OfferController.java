package com.example.myfirstproject.controllers;

import com.example.myfirstproject.model.DTOs.offer.AddOfferDTO;
import com.example.myfirstproject.model.OfferEntity;
import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.views.AllOffersView;
import com.example.myfirstproject.model.views.OfferDetailsView;
import com.example.myfirstproject.service.BrandService;
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
    private final BrandService brandService;
    private final UserService userService;

    @Autowired
    public OfferController(OfferService offerService, BrandService brandService, UserService userService) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.userService = userService;
    }

    @GetMapping("/add-offer")
    public String addOffer(Model model) {

        model.addAttribute("brands", brandService.getAllBrands());

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

        this.offerService.addOffer(principal, addOfferDTO, file);

        return "redirect:/all-offers";
    }

    @ModelAttribute
    public AddOfferDTO addOfferDTO() {
        return new AddOfferDTO();
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(@PathVariable("id") Long id,
                               Model model, Principal principal) {

        OfferDetailsView offerDetails = offerService.getOfferDetails(id);

        model.addAttribute("offer", offerDetails);
        model.addAttribute("canDelete", offerService.isOwner(id, principal));
        model.addAttribute("isLiked", offerService.isLiked(id, principal));

        return "offer";
    }

    @GetMapping("/all-offers")
    public String allOffers(Model model, Principal principal) {

        List<AllOffersView> allOffers = this.offerService.getAllOffers();

        model.addAttribute("allOffers", allOffers);

        return "allOffers";
    }

    @GetMapping("/liked-offers")
    public String likedOffers(Principal principal, Model model) {

        UserEntity user = this.userService.getUserByUsername(principal.getName());

        List<AllOffersView> likedOffers = userService.getLikedOffers(user);

        model.addAttribute("likedOffers", likedOffers);

        return "likedOffers";
    }

    @GetMapping("/offer/like/{id}")
    public String likeOffer(@PathVariable("id") Long id,
                            Principal principal,
                            Model model) {

        OfferEntity offer = offerService.getOfferById(id);
        this.userService.likeOffer(principal, offer);


        return "redirect:/liked-offers";

    }

    @GetMapping("/offer/disLike/{id}")
    public String disLikeOffer(@PathVariable("id") Long id,
                               Principal principal,
                               Model model) {

        OfferEntity offer = offerService.getOfferById(id);
        this.userService.disLikeOffer(principal, offer);


        return "redirect:/liked-offers";
    }

    @GetMapping("/offer/delete/{id}")
    public String deleteOffer(@PathVariable("id") Long id,
                              Principal principal) {

        offerService.deleteOffer(id, principal);
        return "redirect:/all-offers";
    }

}
