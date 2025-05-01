package com.em.event_management;

import com.em.event_management.model.Role;
import com.em.event_management.model.User;
import com.em.event_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
@RequiredArgsConstructor
public class EventManagementApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        try {
            SpringApplication.run(EventManagementApplication.class, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        User adminAccount = userRepository.findByRole(Role.ADMIN);
        if (null == adminAccount) {
            User admin = new User();
            admin.setFirstName("Hober");
            admin.setLastName("Malo");
            admin.setUsername("admin");
            admin.setEmail("admin@email.com");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            admin.setRole(Role.ADMIN);
            admin.setAccountCreatedAt(LocalDateTime.now());
            userRepository.save(admin);
            System.out.println("New Admin Created Successfully");
        } else {
            System.out.println("Admin User Already Exists");
        }
    }
}
