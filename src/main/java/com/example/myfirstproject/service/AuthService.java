package com.example.myfirstproject.service;


import com.example.myfirstproject.model.DTOs.UserRegisterDTO;
import com.example.myfirstproject.model.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;

    private final ModelMapper modelMapper;

    public AuthService(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;

        this.modelMapper = modelMapper;
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
        this.userService.register(user);

        return true;
    }
}
