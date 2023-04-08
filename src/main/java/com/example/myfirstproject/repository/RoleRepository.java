package com.example.myfirstproject.repository;

import com.example.myfirstproject.model.RoleEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findRoleByName(UserRoleEnum role);
}
