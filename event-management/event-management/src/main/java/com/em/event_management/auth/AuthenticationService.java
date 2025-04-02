package com.em.event_management.auth;

import com.em.event_management.dto.JwtAuthenticationResponse;
import com.em.event_management.dto.RefreshTokenRequest;
import com.em.event_management.dto.SignInRequest;
import com.em.event_management.dto.SignUpRequest;
import com.em.event_management.model.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
