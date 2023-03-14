package com.example.myfirstproject.service;

import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.UserRoleEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;


    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public void initFirstUser() {
        if (userRepository.count() == 0) {
            UserEntity adminUser = new UserEntity();

            UserRoleEntity userRoleEntity = roleService.findRoleByName(UserRoleEnum.ADMIN);

            List<UserRoleEntity> role = new ArrayList<>();
            role.add(userRoleEntity);

            adminUser.setUsername("Bari10");
            adminUser.setFirstName("Bari");
            adminUser.setLastName("Papazov");
            adminUser.setEmail("bari_papazov@gmail.com");
            adminUser.setPassword("1234");
            adminUser.setTelephoneNumber("0896464970");
            adminUser.setRoles(role);

            this.userRepository.saveAndFlush(adminUser);
        }

    }
}
