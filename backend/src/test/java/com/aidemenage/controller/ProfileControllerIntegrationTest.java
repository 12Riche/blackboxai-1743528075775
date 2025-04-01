package com.aidemenage.controller;

import com.aidemenage.dto.response.ProfileResponse;
import com.aidemenage.model.User;
import com.aidemenage.repository.UserRepository;
import com.aidemenage.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProfileControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    private String testToken;
    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .fullName("Test User")
                .email("test@example.com")
                .password("password")
                .phone("+1234567890")
                .build();
        
        userRepository.save(testUser);
        
        testToken = "Bearer " + jwtService.generateToken(
            userDetailsService.loadUserByUsername(testUser.getEmail())
        );
    }

    @Test
    void getProfile_ShouldReturnProfile() throws Exception {
        mockMvc.perform(get("/api/profile")
                .header("Authorization", testToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is(testUser.getFullName())))
                .andExpect(jsonPath("$.email", is(testUser.getEmail())))
                .andExpect(jsonPath("$.phone", is(testUser.getPhone())));
    }

    @Test
    void updateProfile_ShouldUpdateAndReturnProfile() throws Exception {
        String updateJson = """
        {
            "fullName": "Updated Name",
            "phone": "+9876543210"
        }
        """;

        mockMvc.perform(put("/api/profile")
                .header("Authorization", testToken)
                .contentType(APPLICATION_JSON)
                .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is("Updated Name")))
                .andExpect(jsonPath("$.phone", is("+9876543210")));
    }
}