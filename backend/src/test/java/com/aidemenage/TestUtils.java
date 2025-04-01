package com.aidemenage;

import com.aidemenage.dto.request.LoginRequest;
import com.aidemenage.dto.request.ProfileUpdateRequest;
import com.aidemenage.dto.request.RegisterRequest;
import com.aidemenage.model.User;

public class TestUtils {
    
    public static RegisterRequest createValidRegisterRequest() {
        return new RegisterRequest(
            "Test User",
            "test@example.com",
            "password123",
            "+1234567890"
        );
    }

    public static LoginRequest createValidLoginRequest() {
        return new LoginRequest(
            "test@example.com",
            "password123"
        );
    }

    public static ProfileUpdateRequest createValidProfileUpdateRequest() {
        return new ProfileUpdateRequest(
            "Updated Name",
            "+9876543210"
        );
    }

    public static User createTestUser() {
        User user = new User();
        user.setId(1L);
        user.setFullName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setPhone("+1234567890");
        return user;
    }
}