package com.example.myfirstproject.service;

import com.example.myfirstproject.model.UserRoleEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.repository.RoleRepository;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<UserRoleEntity> getRoleById(Long id){
        return this.roleRepository.findById(id);
    }

    public UserRoleEntity findAdminRoleByName(UserRoleEnum admin) {
        return roleRepository.findRoleByName(UserRoleEnum.ADMIN);
    }

    public UserRoleEntity findUserRoleByName(UserRoleEnum user) {
        return roleRepository.findRoleByName(UserRoleEnum.USER);
    }

    public List<UserRoleEntity> getMissingRoles(List<UserRoleEntity> roles) {
        List<UserRoleEntity> all = this.roleRepository.findAll();

        return all.stream()
                .filter(r -> !roles.contains(r))
                .collect(Collectors.toList());
    }
}
