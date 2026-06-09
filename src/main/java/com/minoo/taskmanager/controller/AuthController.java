package com.minoo.taskmanager.controller;

import com.minoo.taskmanager.dto.AuthResponse;
import com.minoo.taskmanager.dto.LoginRequest;
import com.minoo.taskmanager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        authService.login(request);

        AuthResponse response = new AuthResponse("fake-jwt-token");

        return ResponseEntity.ok(response);
    }
}