package com.aidemenage.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private SecurityConfig securityConfig;

    @Test
    void passwordEncoderBeanShouldBeBCrypt() {
        PasswordEncoder encoder = securityConfig.passwordEncoder();
        assertTrue(encoder instanceof BCryptPasswordEncoder);
    }

    @Test
    void authenticationManagerBeanShouldExist() throws Exception {
        assertNotNull(securityConfig.authenticationManager(null));
    }
}