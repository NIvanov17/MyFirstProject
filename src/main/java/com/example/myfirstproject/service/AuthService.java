package com.example.myfirstproject.service;


import com.example.myfirstproject.model.DTOs.user.UserRegisterDTO;
import com.example.myfirstproject.model.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userService = userService;

        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        Optional<UserEntity> byUsername = this.userService.getByUsername(userRegisterDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        Optional<UserEntity> byEmail = this.userService.getByEmail(userRegisterDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }

        UserEntity user = this.modelMapper.map(userRegisterDTO, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        this.userService.register(user);

        return true;
    }
}
