package com.example.myfirstproject.controllers;

import com.example.myfirstproject.model.DTOs.UsersDTO;
import com.example.myfirstproject.model.UserRoleEntity;
import com.example.myfirstproject.service.RoleService;
import com.example.myfirstproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class RolesController {

    private final UserService userService;

    private final RoleService roleService;

    public RolesController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/change/{id}")
    public String changeRoles(@PathVariable("id") Long id,
                              Model model) {
        UsersDTO userDTO = this.userService.getUserDTO(id).get();
        List<UserRoleEntity> roles = userDTO.getRoles();
        List<UserRoleEntity> missingRoles = this.roleService.getMissingRoles(roles);

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("roles", roles);
        model.addAttribute("missingRoles", missingRoles);

        return "changeRoles";

    }

    @GetMapping("/admin/addRole/{id}/{id2}")
    public String addRole(@PathVariable("id") Long roleId,
                          @PathVariable("id2") Long userID) {

        this.userService.addRole(roleId, userID);

        return "adminPage";
    }

    @GetMapping("/admin/removeRole/{id}/{id2}")
    public String removeRole(@PathVariable("id") Long roleId,
                             @PathVariable("id2") Long userId) {

        this.userService.removeRole(roleId,userId);

        return "adminPage";
    }

}
