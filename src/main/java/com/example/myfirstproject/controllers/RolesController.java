package com.example.myfirstproject.controllers;

import com.example.myfirstproject.model.DTOs.roles.RoleDTO;
import com.example.myfirstproject.model.RoleEntity;
import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.service.RoleService;
import com.example.myfirstproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RolesController {

    private final UserService userService;

    private final RoleService roleService;

    public RolesController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/addRole/{id}")
    public String changeRoles(@PathVariable("id") Long id,
                              Model model) {
        RoleDTO userModel = this.userService.getAddRoleDTO(id).get();
        UserEntity user = this.userService.getUserById(id).get();
        List<RoleEntity> missingRoles = this.roleService.getMissingRoles(user);

        model.addAttribute("userModel", userModel);
        model.addAttribute("missingRoles", missingRoles);

        return "addRole";

    }

    @PostMapping("/admin/addRole/{id}")
    public String addRole(@PathVariable("id") Long id,
                          @Valid RoleDTO userModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/admin/addRole/{id}";
        }
        this.userService.addRole(id, userModel);
        return "redirect:/home";
    }

    @GetMapping("/admin/removeRole/{id}")
    public String removeRole(@PathVariable("id") Long id,
                             Model model) {
        RoleDTO userModel = this.userService.getAddRoleDTO(id).get();
        UserEntity user = this.userService.getUserById(id).get();
        List<RoleEntity> toRemove = this.roleService.getRolesToRemove(user);

        model.addAttribute("userModel", userModel);
        model.addAttribute("toRemove", toRemove);

        return "removeRole";
    }

    @PostMapping("/admin/removeRole/{id}")
    public String removeRole(@PathVariable("id") Long id,
                             @Valid RoleDTO userModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/admin/addRole/{id}";
        }
        if (this.userService.removeRole(id, userModel)) {
            return "redirect:/home";
        }
        return "redirect:/admin/removeRole/{id}";
    }


    @ModelAttribute
    public RoleDTO userModel() {
        return new RoleDTO();
    }

}
