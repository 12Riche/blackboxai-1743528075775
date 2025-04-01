package com.aidemenage.service;

import com.aidemenage.dto.request.ProfileUpdateRequest;
import com.aidemenage.dto.response.ProfileResponse;
import com.aidemenage.exception.ResourceNotFoundException;
import com.aidemenage.model.User;
import com.aidemenage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ProfileResponse getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        return new ProfileResponse(
                user.getFullName(),
                user.getEmail(),
                user.getPhone()
        );
    }

    @Transactional
    public ProfileResponse updateProfile(String email, ProfileUpdateRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        user.setFullName(request.fullName());
        user.setPhone(request.phone());

        userRepository.save(user);

        return new ProfileResponse(
                user.getFullName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}