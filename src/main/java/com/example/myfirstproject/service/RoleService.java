package com.example.myfirstproject.service;

import com.example.myfirstproject.model.UserRoleEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.repository.RoleRepository;
import org.springframework.stereotype.Service;


import java.util.Arrays;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public void initRoles() {
        if (roleRepository.count() == 0) {

            Arrays.stream(UserRoleEnum.values()).forEach(role -> {
                UserRoleEntity currentRole = new UserRoleEntity();
                currentRole.setRole(role);
                this.roleRepository.saveAndFlush(currentRole);
            });
        }
    }
}
