package com.javaprogram.testing;

import java.util.Optional;

/**
 * Interface for data access — Mockito will mock this in tests.
 */
public interface UserRepository {
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    User save(User user);
    boolean existsByEmail(String email);
}
