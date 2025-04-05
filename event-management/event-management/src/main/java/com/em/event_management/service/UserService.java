package com.em.event_management.service;

import com.em.event_management.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    List<User> findAllUsers();

    boolean usernameExists(String username);

    void deleteUser(Long id);
}
