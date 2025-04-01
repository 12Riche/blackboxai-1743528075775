package com.aidemenage.dto.response;

public record JwtResponse(
    String token,
    String type,
    Long id,
    String fullName, 
    String email,
    String phone
) {
    public JwtResponse(String token, Long id, String fullName, String email, String phone) {
        this(token, "Bearer", id, fullName, email, phone);
    }
}