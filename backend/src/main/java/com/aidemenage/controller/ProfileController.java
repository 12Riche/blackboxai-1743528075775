package com.aidemenage.controller;

import com.aidemenage.dto.request.ProfileUpdateRequest;
import com.aidemenage.dto.response.ProfileResponse;
import com.aidemenage.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    @Operation(summary = "Récupérer le profil",
            description = "Retourne les informations du profil de l'utilisateur connecté",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Profil récupéré avec succès",
                            content = @Content(schema = @Schema(implementation = ProfileResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Non autorisé")
            })
    public ProfileResponse getProfile(Authentication authentication) {
        String email = authentication.getName();
        return profileService.getProfile(email);
    }

    @PutMapping
    @Operation(summary = "Mettre à jour le profil",
            description = "Met à jour les informations du profil utilisateur",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Profil mis à jour avec succès",
                            content = @Content(schema = @Schema(implementation = ProfileResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Données invalides"),
                    @ApiResponse(responseCode = "401", description = "Non autorisé")
            })
    public ProfileResponse updateProfile(
            Authentication authentication,
            @Valid @RequestBody ProfileUpdateRequest request
    ) {
        String email = authentication.getName();
        return profileService.updateProfile(email, request);
    }
}