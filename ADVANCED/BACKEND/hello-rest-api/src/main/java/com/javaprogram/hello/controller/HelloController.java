package com.javaprogram.hello.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    // GET /hello → "Hello, World!"
    @GetMapping
    public String hello() {
        return "Hello, World!";
    }

    // GET /hello/{name} → "Hello, Alice!"
    @GetMapping("/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    // POST /echo — echo the request body back
    @PostMapping("/echo")
    public Map<String, String> echo(@RequestBody String message) {
        return Map.of("echo", message, "length", String.valueOf(message.length()));
    }

    // GET /info — returns some server info
    @GetMapping("/info")
    public Map<String, String> info() {
        return Map.of(
            "app",     "hello-rest-api",
            "version", "1.0",
            "status",  "running"
        );
    }
}
