package com.em.event_management.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    boolean usernameExists(String username);

    void deleteUser(Long id);
}
