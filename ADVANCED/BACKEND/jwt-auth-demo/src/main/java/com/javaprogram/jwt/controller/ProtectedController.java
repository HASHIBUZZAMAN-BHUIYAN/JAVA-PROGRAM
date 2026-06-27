package com.javaprogram.jwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProtectedController {

    // Requires valid JWT — if no token, Spring Security returns 403
    @GetMapping("/me")
    public Map<String, Object> whoAmI(Authentication auth) {
        return Map.of(
            "username", auth.getName(),
            "roles",    auth.getAuthorities().stream().map(Object::toString).toList(),
            "message",  "You are authenticated!"
        );
    }

    @GetMapping("/data")
    public Map<String, String> getData() {
        return Map.of("secret", "This is protected data — only visible with a valid JWT");
    }
}
