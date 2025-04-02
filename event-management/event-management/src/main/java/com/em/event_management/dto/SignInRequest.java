package com.em.event_management.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
