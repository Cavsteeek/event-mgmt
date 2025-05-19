package com.em.event_management.controller;

import com.em.event_management.auth.AuthenticationService;
import com.em.event_management.dto.JwtAuthenticationResponse;
import com.em.event_management.dto.RefreshTokenRequest;
import com.em.event_management.dto.SignInRequest;
import com.em.event_management.dto.SignUpRequest;
import com.em.event_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        // Check your Patient-Mgmt-System for how to properly implement using DTO's
        try {
            if (userService.usernameExists(signUpRequest.getUsername())) {
                return ResponseEntity.badRequest().body("Username already exists");
            }
            return ResponseEntity.ok(service.signUp(signUpRequest));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest) {
        JwtAuthenticationResponse user = service.signIn(signInRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(service.refreshToken(refreshTokenRequest));
    }
}
