package com.example.myfirstproject.service;

import com.example.myfirstproject.repository.OfferRepository;
import com.example.myfirstproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @BeforeEach
    void setUp() {
        toTest = new UserService(mockUserRepository, modelMapper, mockRoleService, mockPasswordEncoder, mockOfferRepository);
    }

    @Test
    void testUserRegistration(){
    }
}
