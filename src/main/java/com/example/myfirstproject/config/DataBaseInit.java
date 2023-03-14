package com.example.myfirstproject.config;

import com.example.myfirstproject.service.OfferService;
import com.example.myfirstproject.service.RoleService;
import com.example.myfirstproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {

    private final RoleService roleService;



    private final UserService userService;

    public DataBaseInit(RoleService roleService,  UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
        this.userService.initFirstUser();
    }
}
