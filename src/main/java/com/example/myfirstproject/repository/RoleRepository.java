package com.example.myfirstproject.repository;

import com.example.myfirstproject.model.UserRoleEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findRoleByName(UserRoleEnum admin);
}
