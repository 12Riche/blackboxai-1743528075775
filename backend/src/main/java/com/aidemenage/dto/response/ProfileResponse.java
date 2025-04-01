package com.aidemenage.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileResponse {
    private String fullName;
    private String email;
    private String phone;
    private String role;
}