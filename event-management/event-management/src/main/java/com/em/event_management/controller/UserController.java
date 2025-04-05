package com.em.event_management.controller;

import com.em.event_management.model.User;
import com.em.event_management.repository.UserRepository;
import com.em.event_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/admin/all-users")
    public ResponseEntity<?> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        List<User> users = userService.findAllUsers();
//        return ResponseEntity.ok(users);

        try {
            return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            // Return an internal server error status if an exception occurs
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
