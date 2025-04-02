package com.em.event_management.jwt_and_security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

public interface JWTService {
    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    Date extractExpiration(String token);

    String generateToken(UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
