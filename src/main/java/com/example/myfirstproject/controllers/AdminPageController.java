package com.example.myfirstproject.controllers;

import com.example.myfirstproject.model.DTOs.UsersDTO;
import com.example.myfirstproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminPageController {

    private final UserService userService;

    public AdminPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
