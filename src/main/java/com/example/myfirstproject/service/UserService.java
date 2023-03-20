package com.example.myfirstproject.service;

import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.UserRoleEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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
            adminUser.setActive(true);

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
}
