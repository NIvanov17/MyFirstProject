package com.example.myfirstproject.controllers;

import com.example.myfirstproject.model.*;
import com.example.myfirstproject.model.enums.EngineEnum;
import com.example.myfirstproject.model.enums.TransmissionEnum;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.repository.OfferRepository;
import com.example.myfirstproject.repository.UserRepository;
import com.example.myfirstproject.service.BrandService;
import com.example.myfirstproject.service.OfferService;
import com.example.myfirstproject.service.UserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerIT {

    @Autowired
    private MockMvc mockMvc;



    @Mock
    private UserRepository userRepository;
    @Mock
    private OfferService offerService;
    @Mock
    private UserEntity user;
    @Mock
    private OfferEntity offer;
    @Mock
    private OfferRepository offerRepository;

    @BeforeEach
    void setup() {
        user = new UserEntity()
                .setId(1)
                .setUsername("Kalin4")
                .setPassword("123")
                .setEmail("kalin_krumov@gmail.com")
                .setFirstName("Kalin")
                .setLastName("Krumov")
                .setRoles(List.of(new UserRoleEntity().setRole(UserRoleEnum.ADMIN)));

        offer = new OfferEntity()
                .setBrand(new BrandEntity().setId(1))
                .setModel(new ModelEntity().setId(1))
                .setEngine(EngineEnum.PETROL)
                .setTransmission(TransmissionEnum.AUTOMATIC)
                .setSeller(user)
                .setDescription("new")
                .setPrice(BigDecimal.valueOf(10000))
                .setMileage(100000)
                .setPicture("blabla")
                .setYear(2000)
                .setHorsePower(200);

        this.offerRepository.save(offer);
        this.userRepository.save(user);

    }

    @AfterEach
    void cleanUp(){
        this.userRepository.deleteAll();
        this.offerRepository.deleteAll();
    }


    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    void addOfferPageTest() throws Exception {
        mockMvc.perform(get("/add-offer"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-offer"));
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    void allOfferPageTest() throws Exception {
        mockMvc.perform(get("/all-offers"))
                .andExpect(status().isOk())
                .andExpect(view().name("allOffers"));
    }
//
//    @Test
//    @WithMockUser(username = "Kalin4",roles = {"USER", "ADMIN"})
//    void likedOffersPageTest() throws Exception {
//        mockMvc.perform(get("/liked-offers"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("likedOffers"));
//    }

//    @Test
//    @WithMockUser(roles = {"USER", "ADMIN"})
//    void offerDetailsPageTest() throws Exception {
//        mockMvc.perform(get("/offer/{id}",offer.getId()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("offer"));
//    }

//    @Test
//    @WithMockUser(roles = {"USER","ADMIN"})
//    void addOfferSuccessTest() throws Exception {
//        mockMvc.perform(post("/add-offer")
//                        .param("brand","1")
//                        .param("model","1")
//                        .param("price","10000")
//                        .param("horsePower","180")
//                        .param("engine","PETROL")
//                        .param("transmission","MANUAL")
//                        .param("year","2002")
//                        .param("mileage","100000")
//                        .param("description","soo nice :-)")
//                        .param("pricture","img")
//                        .with(csrf())
//                ).andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/all-offers"));
//    }


//test za offer ako ne si lognat
//    @Test
//    void addOfferPageFailure() throws Exception {
//        mockMvc.perform(get("/add-offer"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/login"));
//    }
}
