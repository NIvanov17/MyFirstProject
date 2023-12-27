package com.example.myfirstproject.service;

import com.example.myfirstproject.model.RoleEntity;
import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.repository.OfferRepository;
import com.example.myfirstproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RoleService mockRoleService;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private OfferRepository mockOfferRepository;

    private UserService toTest;

    private UserEntity user;

//    @BeforeEach
//    void setUp() {
//        toTest = new UserService(mockUserRepository, modelMapper, mockRoleService, mockPasswordEncoder, mockOfferRepository);
//        user = new UserEntity()
//                .setId(1)
//                .setUsername("Kalin4")
//                .setPassword("123")
//                .setEmail("kalin_krumov@gmail.com")
//                .setFirstName("Kalin")
//                .setLastName("Krumov")
//                .setRoles(List.of(new RoleEntity().setName(UserRoleEnum.ADMIN)));
//    }
//
//    @Test
//    void getUserByUsernameTest(){
//        when(toTest.getUserByUsername("Kalin4"))
//                .thenReturn(user);
//
//        UserEntity userEntity = toTest.getUserByUsername("Kalin4");
//
//        Assertions.assertNotNull(userEntity);
//        Assertions.assertEquals("Kalin4",userEntity.getUsername());
//    }
}
