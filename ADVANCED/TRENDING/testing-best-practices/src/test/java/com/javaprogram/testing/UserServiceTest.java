package com.javaprogram.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for UserService using JUnit 5 + Mockito.
 * @ExtendWith(MockitoExtension.class) auto-injects mocks.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock   UserRepository userRepo;       // Mockito creates a mock
    @Mock   EmailService   emailService;   // Mockito creates a mock
    @InjectMocks UserService service;      // Mockito injects mocks into this

    User sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = new User(1, "Alice", "alice@example.com", "USER");
    }

    // ── register() ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("register — success: saves user and sends welcome email")
    void testRegisterSuccess() {
        // Arrange: stub the mock behavior
        when(userRepo.existsByEmail("alice@example.com")).thenReturn(false);
        when(userRepo.save(any(User.class))).thenReturn(sampleUser);

        // Act
        User result = service.register("Alice", "alice@example.com");

        // Assert: correct return value
        assertEquals("Alice", result.getName());
        assertEquals("alice@example.com", result.getEmail());

        // Verify: save was called exactly once
        verify(userRepo, times(1)).save(any(User.class));

        // Verify: welcome email was sent
        verify(emailService, times(1)).sendWelcomeEmail("alice@example.com", "Alice");
    }

    @Test
    @DisplayName("register — email already exists throws IllegalStateException")
    void testRegisterDuplicateEmail() {
        when(userRepo.existsByEmail("alice@example.com")).thenReturn(true);

        assertThrows(IllegalStateException.class,
            () -> service.register("Alice", "alice@example.com"));

        // Verify save was never called (short-circuited by duplicate check)
        verify(userRepo, never()).save(any());
        // Verify no welcome email sent
        verifyNoInteractions(emailService);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    @DisplayName("register — blank name throws IllegalArgumentException")
    void testRegisterBlankName(String name) {
        assertThrows(IllegalArgumentException.class,
            () -> service.register(name, "alice@example.com"));
        verifyNoInteractions(userRepo);  // repo should never be touched
    }

    @Test
    @DisplayName("register — invalid email throws IllegalArgumentException")
    void testRegisterInvalidEmail() {
        assertThrows(IllegalArgumentException.class,
            () -> service.register("Alice", "not-an-email"));
    }

    // ── findById() ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("findById — returns user when found")
    void testFindByIdFound() {
        when(userRepo.findById(1)).thenReturn(Optional.of(sampleUser));

        Optional<User> result = service.findById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    @DisplayName("findById — returns empty when not found")
    void testFindByIdNotFound() {
        when(userRepo.findById(99)).thenReturn(Optional.empty());
        assertTrue(service.findById(99).isEmpty());
    }

    @Test
    @DisplayName("getByIdOrThrow — throws RuntimeException when not found")
    void testGetByIdOrThrow() {
        when(userRepo.findById(99)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.getByIdOrThrow(99));
    }

    // ── isAdmin() ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("isAdmin — returns false for USER role")
    void testIsAdminUser() {
        when(userRepo.findById(1)).thenReturn(Optional.of(sampleUser));
        assertFalse(service.isAdmin(1));
    }

    @Test
    @DisplayName("isAdmin — returns true for ADMIN role")
    void testIsAdminAdmin() {
        User admin = new User(2, "Admin", "admin@example.com", "ADMIN");
        when(userRepo.findById(2)).thenReturn(Optional.of(admin));
        assertTrue(service.isAdmin(2));
    }

    @Test
    @DisplayName("isAdmin — returns false when user not found")
    void testIsAdminNotFound() {
        when(userRepo.findById(99)).thenReturn(Optional.empty());
        assertFalse(service.isAdmin(99));
    }

    // ── Argument Captors ─────────────────────────────────────────────────────

    @Test
    @DisplayName("ArgumentCaptor — capture what was passed to save()")
    void testArgumentCaptor() {
        when(userRepo.existsByEmail("bob@example.com")).thenReturn(false);
        when(userRepo.save(any())).thenReturn(new User(2,"Bob","bob@example.com","USER"));

        service.register("  Bob  ", "BOB@EXAMPLE.COM");

        // Capture the exact argument passed to save()
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepo).save(captor.capture());

        User captured = captor.getValue();
        assertEquals("Bob", captured.getName());      // trimmed
        assertEquals("bob@example.com", captured.getEmail()); // lowercased
    }
}
