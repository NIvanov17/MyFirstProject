package com.example.myfirstproject.service;

import com.example.myfirstproject.model.RoleEntity;
import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.repository.RoleRepository;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public void initRoles() {
        if (roleRepository.count() == 0) {

            Arrays.stream(UserRoleEnum.values()).forEach(role -> {
                RoleEntity currentRole = new RoleEntity();
                currentRole.setName(role);
                this.roleRepository.saveAndFlush(currentRole);
            });
        }
    }

    public Optional<RoleEntity> getRoleById(Long id) {
        return this.roleRepository.findById(id);
    }

    public RoleEntity findAdminRoleByName(UserRoleEnum admin) {
        return roleRepository.findRoleByName(UserRoleEnum.ADMIN);
    }

    public RoleEntity findUserRoleByName(UserRoleEnum user) {
        return roleRepository.findRoleByName(UserRoleEnum.USER);
    }

    public List<RoleEntity> getMissingRoles(UserEntity user) {
        List<RoleEntity> all = this.roleRepository.findAll();
        List<RoleEntity> currentRoles = user.getRoles();
        all.removeAll(currentRoles);
        return all;
    }

    public RoleEntity getRoleByName(UserRoleEnum role) {
        return this.roleRepository.findRoleByName(role);
    }

    public List<RoleEntity> getRolesToRemove(UserEntity user) {
       return user.getRoles();
    }
}
