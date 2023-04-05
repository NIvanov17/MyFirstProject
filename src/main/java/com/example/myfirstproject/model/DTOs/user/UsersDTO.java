package com.example.myfirstproject.model.DTOs.user;

import com.example.myfirstproject.model.UserRoleEntity;

import java.util.List;

public class UsersDTO {

    private long id;

    private String username;

    private List<UserRoleEntity> roles;

    public UsersDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "AdminPageDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
