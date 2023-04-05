package com.example.myfirstproject.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;




@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void registrationSuccessTest() throws Exception {
        mockMvc.perform(post("/register").
                param("email", "ivan@example.com").
                param("firstName", "Ivan").
                param("lastName", "Ivanov").
                param("username", "Ivan123").
                param("password", "123").
                param("confirmPassword", "123").
                param("telephoneNumber","0888765").
                with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void registrationPageTest() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void registrationErrorTest() throws Exception {
        mockMvc.perform(post("/register")
                .param("email", "").
                param("firstName", "Ivan").
                param("lastName", "Ivanov").
                param("username", "Ivan123").
                param("password", "123").
                param("confirmPassword", "123").
                param("telephoneNumber","0888765").
                with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"));

    }
}
