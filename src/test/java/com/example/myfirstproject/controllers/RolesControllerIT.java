package com.example.myfirstproject.controllers;

import com.example.myfirstproject.model.DTOs.roles.RoleDTO;
import com.example.myfirstproject.model.RoleEntity;
import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.service.RoleService;
import com.example.myfirstproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class RolesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleService roleService;

    private RoleDTO roleDTO;

    private UserEntity user;

    private RoleEntity role;

    @BeforeEach
    void setUp() {
        roleDTO = new RoleDTO()
                .setId(1)
                .setUsername("Kalin4")
                .setRole("ADMIN");

//        user = new UserEntity()
//                .setId(1)
//                .setUsername("Kalin4")
//                .setPassword("123")
//                .setEmail("kalin_krumov@gmail.com")
//                .setFirstName("Kalin")
//                .setLastName("Krumov")
//                .setRoles(List.of(new RoleEntity().setName(UserRoleEnum.ADMIN)));
//
//        role = new RoleEntity()
//                .setId(1)
//                .setName(UserRoleEnum.ADMIN);
    }

//    @Test
//    @WithMockUser(username = "Kalin4", roles = {"ADMIN"})
//    void changeRoleTest() throws Exception {
//        when(userService.getAddRoleDTO(1L))
//                .thenReturn(Optional.of(roleDTO));
//
//        when(userService.getUserById(1L))
//                .thenReturn(Optional.of(user));
//
//        when(roleService.getMissingRoles(user))
//                .thenReturn(List.of(role));
//
//        mockMvc.perform(get("/admin/addRole/{id}",user.getId()))
//                .andExpect(model().attributeExists("userModel"))
//                .andExpect(model().attributeExists("missingRoles"))
//                .andExpect(view().name("addRole"));
//    }
//
//    @Test
//    @WithMockUser(username = "Kalin4", roles = {"ADMIN"})
//    void removeRoleTest() throws Exception {
//        when(userService.getAddRoleDTO(1L))
//                .thenReturn(Optional.of(roleDTO));
//
//        when(userService.getUserById(1L))
//                .thenReturn(Optional.of(user));
//
//        when(roleService.getRolesToRemove(user))
//                .thenReturn(List.of(role));
//
//        mockMvc.perform(get("/admin/removeRole/{id}",user.getId()))
//                .andExpect(model().attributeExists("userModel"))
//                .andExpect(model().attributeExists("toRemove"))
//                .andExpect(view().name("removeRole"));
//    }


}
