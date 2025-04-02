package com.em.event_management.auth.impl;

import com.em.event_management.auth.AuthenticationService;
import com.em.event_management.dto.JwtAuthenticationResponse;
import com.em.event_management.dto.RefreshTokenRequest;
import com.em.event_management.dto.SignInRequest;
import com.em.event_management.dto.SignUpRequest;
import com.em.event_management.jwt_and_security.JWTService;
import com.em.event_management.model.Role;
import com.em.event_management.model.User;
import com.em.event_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    private String capitalize(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setFirstName(capitalize(signUpRequest.getFirstName()));
        user.setLastName(capitalize(signUpRequest.getLastName()));
        user.setEmail(signUpRequest.getEmail());
        user.setRole(Role.USER);
        user.setAccountCreatedAt(LocalDateTime.now());
        user.setPassword(
                passwordEncoder.encode(signUpRequest.getPassword())
        );
        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getUsername(),
                    signInRequest.getPassword()));
            var user = userRepository.findByUsername(signInRequest.getUsername())
                    .orElseThrow(
                            () -> new IllegalArgumentException("Invalid Username or Password")
                    );
            var jwt = jwtService.generateToken(user);
            var username = jwtService.extractUsername(jwt);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
            var expirationTime = jwtService.extractExpiration(jwt);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setUsername(username);
            jwtAuthenticationResponse.setRole(user.getRole().toString());
            jwtAuthenticationResponse.setRefreshToken(refreshToken);
            jwtAuthenticationResponse.setUserId(user.getId());
            jwtAuthenticationResponse.setExpirationTime(expirationTime);
            return jwtAuthenticationResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during sign-in", e);
        }
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userName = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByUsername(userName).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

}
