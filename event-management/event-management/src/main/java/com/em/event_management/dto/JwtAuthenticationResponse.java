package com.em.event_management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String role;
    private String username;
    private String refreshToken;
    private Long userId;
    private Date expirationTime;
}
