package com.example.myfirstproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfferController {

    @GetMapping("/add-offer")
    public String addOffer(){
        return "add-offer";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(){
        return "offer";
    }

}
