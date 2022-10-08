package com.ironhack.finalProject.config.security;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomUserDetailsServiceTest {

    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private Credentials cred;

    @BeforeEach
    void setUp() {
        Credentials c = new Credentials();
        c.setUsername("alexandraK9");
        c.setSecretPass(passwordEncoder.encode("0909k"));
        cred = credentialsRepository.save(c);
    }

    @AfterEach
    void tearDown() {
        credentialsRepository.delete(cred);
    }

    @Test
    void loadUserByUsername_worksFine() {
        UserDetails ud = customUserDetailsService.loadUserByUsername("alexandraK9");
        assertEquals("alexandraK9", ud.getUsername());
    }
    @Test
    void loadUserByUsername_throwsException() {
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("alex");
        });
    }
}