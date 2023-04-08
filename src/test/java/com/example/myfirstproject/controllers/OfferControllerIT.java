package com.example.myfirstproject.controllers;

import com.example.myfirstproject.model.*;
import com.example.myfirstproject.model.DTOs.brand.BrandDTO;
import com.example.myfirstproject.model.DTOs.offer.AddOfferDTO;
import com.example.myfirstproject.model.enums.EngineEnum;
import com.example.myfirstproject.model.enums.TransmissionEnum;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.model.views.AllOffersView;
import com.example.myfirstproject.model.views.OfferDetailsView;
import com.example.myfirstproject.repository.OfferRepository;
import com.example.myfirstproject.repository.UserRepository;
import com.example.myfirstproject.service.BrandService;
import com.example.myfirstproject.service.OfferService;
import com.example.myfirstproject.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

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

    @MockBean
    private BrandService brandService;
    @MockBean
    private UserService userService;
    @MockBean
    private AllOffersView allOffersView;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private OfferService offerService;
    @MockBean
    private OfferRepository offerRepository;
    @Mock
    private Pageable pageable;

    private UserEntity user;

    private OfferEntity offer;

    private AddOfferDTO addOfferDTO;

    private OfferDetailsView offerDetailsView;

    private BrandDTO brandDTO;

    private Principal principal;

    @BeforeEach
    void setup() {
        user = new UserEntity()
                .setId(1)
                .setUsername("Kalin4")
                .setPassword("123")
                .setEmail("kalin_krumov@gmail.com")
                .setFirstName("Kalin")
                .setLastName("Krumov")
                .setRoles(List.of(new RoleEntity().setName(UserRoleEnum.ADMIN)));

        offer = new OfferEntity()
                .setId(1)
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

        allOffersView = new AllOffersView()
                .setId(1)
                .setBrand("BMW")
                .setModel("4-series")
                .setDescription("new")
                .setPicture("blabla")
                .setPrice(BigDecimal.valueOf(10000));

        addOfferDTO = new AddOfferDTO()
                .setBrand(1)
                .setModel(1)
                .setPrice(BigDecimal.valueOf(10000))
                .setHorsePower(100)
                .setEngine("PETROL")
                .setTransmission("MANUAL")
                .setYear(2000)
                .setMileage(200000)
                .setDescription("blabla")
                .setPicture("img");

        offerDetailsView = new OfferDetailsView()
                .setId(1)
                .setBrand("BMW")
                .setModel("4-series")
                .setPicture("img")
                .setSellerFirstName("Kalin")
                .setSellerLastName("Krumov")
                .setPrice(BigDecimal.valueOf(10000))
                .setHorsePower(100)
                .setEngine("PETROL")
                .setTransmission("MANUAL")
                .setYear(2000)
                .setMileage(200000)
                .setDescription("blabla")
                .setTelephoneNumber("0896464970");

        brandDTO = new BrandDTO()
                .setId(1)
                .setName("BMW");

        this.offerRepository.save(offer);
        this.userRepository.save(user);

    }

    @AfterEach
    void cleanUp() {
        this.userRepository.deleteAll();
        this.offerRepository.deleteAll();
    }


    @Test
    @WithMockUser(username = "Kalin4", roles = {"ADMIN"})
    void addOfferPageTest() throws Exception {

        when(brandService.getAllBrands())
                .thenReturn(List.of(brandDTO));

        mockMvc.perform(get("/add-offer"))
                .andExpect(model().attributeExists("brands"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-offer"));
    }



    @Test
    @WithMockUser(username = "Kalin4", roles = {"ADMIN"})
    void likedOffersPageTest() throws Exception {

        when(userService.getUserByUsername("Kalin4"))
                .thenReturn(user);

        when(userService.getLikedOffers(user))
                .thenReturn(List.of(allOffersView));

        mockMvc.perform(get("/liked-offers"))
                .andExpect(model().attributeExists("likedOffers"))
                .andExpect(status().isOk())
                .andExpect(view().name("likedOffers"));
    }


    @Test
    @WithMockUser(username = "Kalin4", roles = {"USER", "ADMIN"})
    void offerDetailsPageTest() throws Exception {

        when(offerService.getOfferDetails(1L))
                .thenReturn(offerDetailsView);

        mockMvc.perform(get("/offer/{id}", offer.getId()))
                .andExpect(model().attributeExists("offer"))
                .andExpect(status().isOk())
                .andExpect(view().name("offer"));
    }

    @Test
    @WithMockUser(username = "Kalin4", roles = {"ADMIN"})
    void likeOffer() throws Exception {
        when(offerService.getOfferById(1L))
                .thenReturn(offer);

        userService.likeOffer(principal,offer);

        mockMvc.perform(get("/offer/like/{id}",offer.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/liked-offers"));
    }

    @Test
    @WithMockUser(username = "Kalin4", roles = {"ADMIN"})
    void deleteOffer() throws Exception {
        offerService.deleteOffer(offer.getId(),principal);

        mockMvc.perform(get("/offer/delete/{id}",offer.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/all-offers"));
    }
}
