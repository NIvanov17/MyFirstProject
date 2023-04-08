package com.example.myfirstproject.model.DTOs.roles;

import jakarta.validation.constraints.NotBlank;

public class RoleDTO {

    private long id;

    private String username;

    @NotBlank
    private String role;

    public String getUsername() {
        return username;
    }

    public RoleDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public long getId() {
        return id;
    }

    public RoleDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public RoleDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
