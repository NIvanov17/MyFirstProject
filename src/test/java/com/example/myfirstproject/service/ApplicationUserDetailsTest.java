package com.example.myfirstproject.service;

import com.example.myfirstproject.model.UserEntity;

import static org.mockito.Mockito.when;

import com.example.myfirstproject.model.UserRoleEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsTest {

    private ApplicationUserDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setup() {
        toTest = new ApplicationUserDetailsService(mockUserRepository);
    }

    @Test
    void userFound() {

        UserEntity testUserEntity = new UserEntity()
                .setId(1)
                .setPassword("1234")
                .setUsername("Kalin4")
                .setFirstName("Kalin")
                .setLastName("Krumov")
                .setRoles(
                        List.of(new UserRoleEntity().setRole(UserRoleEnum.ADMIN))
                );

        when(mockUserRepository.findByUsername("Kalin4"))
                .thenReturn(Optional.of(testUserEntity));

        UserDetails testDetails = toTest.loadUserByUsername("Kalin4");

        Assertions.assertNotNull(testDetails);
        Assertions.assertEquals("Kalin4",testDetails.getUsername());
        Assertions.assertEquals("1234",testDetails.getPassword());
        Assertions.assertEquals(1,testDetails.getAuthorities().size());
        assertRole(testDetails.getAuthorities(),"ROLE_ADMIN");
    }

    private void assertRole(Collection<? extends GrantedAuthority> authorities,
                            String role){
        authorities
                .stream()
                .filter(r -> role.equals(r.getAuthority()))
                .findAny()
                .orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
    }

    @Test
    void userNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("notExisting"));
    }
}
