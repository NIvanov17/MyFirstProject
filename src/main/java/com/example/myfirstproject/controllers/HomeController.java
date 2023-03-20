package com.example.myfirstproject.controllers;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(auth.getName().equals("anonymousUser")){
//            return "index";
//        }
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
