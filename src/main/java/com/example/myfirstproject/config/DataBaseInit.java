package com.example.myfirstproject.config;

import com.example.myfirstproject.service.OfferService;
import com.example.myfirstproject.service.RoleService;
import com.example.myfirstproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {

    private final RoleService roleService;

    private final OfferService offerService;

    private final UserService userService;

    public DataBaseInit(RoleService roleService, OfferService offerService, UserService userService) {
        this.roleService = roleService;
        this.offerService = offerService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
//        this.offerService.initTransmission();
//        this.offerService.initEngine();
//        this.userService.initFirstUser();
    }
}
