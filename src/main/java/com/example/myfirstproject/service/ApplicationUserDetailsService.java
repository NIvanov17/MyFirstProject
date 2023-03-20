package com.example.myfirstproject.service;

import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.UserRoleEntity;
import com.example.myfirstproject.model.entity.Bri4kaUserDetails;
import com.example.myfirstproject.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return new Bri4kaUserDetails(
                userEntity.getId(),
                userEntity.getPassword(),
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getRoles().stream().map(this::mapRole).toList(),
                userEntity.isActive()

        ) {
        };
    }

    public List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
        return userEntity
                .getRoles()
                .stream()
                .map(this::mapRole)
                .toList();
    }

    private GrantedAuthority mapRole(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
    }
}
