package com.example.myfirstproject.service;

import com.example.myfirstproject.model.DTOs.UsersDTO;
import com.example.myfirstproject.model.OfferEntity;
import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.UserRoleEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public void initFirstUser() {
        if (userRepository.count() == 0) {
            UserEntity adminUser = new UserEntity();

            UserRoleEntity userRoleEntity = roleService.findAdminRoleByName(UserRoleEnum.ADMIN);

            List<UserRoleEntity> roles = new ArrayList<>();
            roles.add(userRoleEntity);

            adminUser.setUsername("Kalin4");
            adminUser.setFirstName("Kalin");
            adminUser.setLastName("Krumov");
            adminUser.setEmail("kalin_krumov@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("1234"));
            adminUser.setTelephoneNumber("0896464970");
            adminUser.setRoles(roles);

            this.userRepository.save(adminUser);
        }

    }


    public Optional<UserEntity> getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public Optional<UserEntity> getByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void register(UserEntity user) {

        UserRoleEntity roleByName = this.roleService.findUserRoleByName(UserRoleEnum.USER);
        user.setRoles(List.of(roleByName));

        this.userRepository.save(user);
    }

    public UserEntity getUserByUsername(String name) {
        return this.userRepository.findUserByUsername(name);
    }

    public void likeOffer(Principal principal, OfferEntity offer) {
        UserEntity currentUser = this.userRepository.findUserByUsername(principal.getName());

        List<OfferEntity> likedOffers = currentUser.getLikedOffers();

        likedOffers.add(offer);

        currentUser.setLikedOffers(likedOffers);
        this.userRepository.save(currentUser);
    }


    public List<UsersDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UsersDTO.class))
                .collect(Collectors.toList());
    }

}
