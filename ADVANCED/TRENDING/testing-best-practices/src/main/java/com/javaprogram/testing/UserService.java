package com.javaprogram.testing;

import java.util.Optional;

/**
 * Business logic layer — the class we're testing.
 * Depends on UserRepository and EmailService (both will be mocked).
 */
public class UserService {

    private final UserRepository userRepo;
    private final EmailService   emailService;

    public UserService(UserRepository userRepo, EmailService emailService) {
        this.userRepo     = userRepo;
        this.emailService = emailService;
    }

    public User register(String name, String email) {
        if (name == null || name.isBlank())   throw new IllegalArgumentException("Name is required");
        if (email == null || email.isBlank())  throw new IllegalArgumentException("Email is required");
        if (!email.contains("@"))              throw new IllegalArgumentException("Invalid email: " + email);

        if (userRepo.existsByEmail(email)) {
            throw new IllegalStateException("Email already registered: " + email);
        }

        User newUser = new User(0, name.trim(), email.toLowerCase(), "USER");
        User saved   = userRepo.save(newUser);
        emailService.sendWelcomeEmail(saved.getEmail(), saved.getName());
        return saved;
    }

    public Optional<User> findById(int id) {
        return userRepo.findById(id);
    }

    public User getByIdOrThrow(int id) {
        return userRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    public boolean isAdmin(int userId) {
        return userRepo.findById(userId)
            .map(u -> "ADMIN".equals(u.getRole()))
            .orElse(false);
    }
}
