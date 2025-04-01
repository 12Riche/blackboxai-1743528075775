package com.aidemenage.dto.request;

public record ProfileUpdateRequest(
    String fullName,
    String phone
) {}