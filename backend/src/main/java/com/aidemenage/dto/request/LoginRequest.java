package com.aidemenage.dto.request;

public record LoginRequest(
    String email,
    String password
) {}