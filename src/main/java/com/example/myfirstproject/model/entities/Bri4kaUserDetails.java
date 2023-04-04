package com.example.myfirstproject.model.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class Bri4kaUserDetails implements UserDetails {


    private final long id;
    private final String password;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final Collection<GrantedAuthority> authorities;


    public Bri4kaUserDetails(long id, String password, String username, String firstName, String lastName, Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
    }


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
