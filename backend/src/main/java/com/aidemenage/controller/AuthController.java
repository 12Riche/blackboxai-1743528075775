package com.aidemenage.controller;

import com.aidemenage.dto.request.LoginRequest;
import com.aidemenage.dto.request.RegisterRequest;
import com.aidemenage.dto.response.JwtResponse;
import com.aidemenage.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentification", description = "API pour l'inscription et la connexion des utilisateurs")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "Inscription utilisateur",
            description = "Crée un nouveau compte utilisateur et retourne un token JWT",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Inscription réussie",
                            content = @Content(schema = @Schema(implementation = JwtResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Données invalides")
            })
    public ResponseEntity<JwtResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Connexion utilisateur",
            description = "Authentifie un utilisateur et retourne un token JWT",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Connexion réussie",
                            content = @Content(schema = @Schema(implementation = JwtResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Non autorisé")
            })
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.authenticateUser(request));
    }
}