package com.example.myfirstproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MaintenaceController {

    @GetMapping("/maintenance")
    public String maintenance() {
        return "maintenance";
    }
}
