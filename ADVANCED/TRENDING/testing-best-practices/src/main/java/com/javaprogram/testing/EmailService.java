package com.javaprogram.testing;

/**
 * External email service — we mock this in tests (we don't want real emails sent).
 */
public interface EmailService {
    void sendWelcomeEmail(String to, String name);
    void sendPasswordResetEmail(String to, String resetToken);
}
