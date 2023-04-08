package com.example.myfirstproject.model;

import com.example.myfirstproject.model.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "user_roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum name;


    public RoleEntity() {

    }

    public long getId() {
        return id;
    }

    public RoleEntity setId(long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getName() {
        return name;
    }

    public RoleEntity setName(UserRoleEnum name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return this.name.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id == that.id && name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

