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
public class LogInControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginPageTest() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void loginSuccessTest() throws Exception {
        mockMvc.perform(post("/login")
                .param("username","Kalin4")
                .param("password","1234")
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void loginErrorTest() throws Exception {
        mockMvc.perform(post("/login-error")
                .param("username","test")
                .param("password","test123")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
