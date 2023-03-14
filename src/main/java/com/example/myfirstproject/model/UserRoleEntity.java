package com.example.myfirstproject.model;

import com.example.myfirstproject.model.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum name;



    public UserRoleEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserRoleEnum getRole() {
        return name;
    }

    public void setRole(UserRoleEnum name) {
        this.name = name;
    }


}
