package com.example.myfirstproject.controllers;

import com.example.myfirstproject.model.DTOs.user.UsersDTO;
import com.example.myfirstproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class AdminPageController {

    private final UserService userService;

    public AdminPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/all-users")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/admin/users/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable("id") Long id) {
        Optional<UsersDTO> user = this.userService.getUserDTO(id);

        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<UsersDTO> deleteUserById(@PathVariable("id") Long id) {
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
