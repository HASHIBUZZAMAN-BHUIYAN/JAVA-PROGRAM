# hello-rest-api

The "Hello World" of modern Java backend development — a minimal Spring Boot REST API.

## What It Teaches
- Spring Boot application structure (`@SpringBootApplication`)
- REST controller with `@RestController` and `@RequestMapping`
- HTTP methods: GET, POST
- Path variables (`@PathVariable`) and request bodies (`@RequestBody`)

## How to Run
```
mvn spring-boot:run
```
Or double-click `build.bat`.

The server starts on **http://localhost:8080**.

## Test Endpoints

```bash
# GET /hello → "Hello, World!"
curl http://localhost:8080/hello

# GET /hello/{name}
curl http://localhost:8080/hello/Alice

# POST /echo
curl -X POST http://localhost:8080/hello/echo \
  -H "Content-Type: text/plain" \
  -d "Testing 123"

# GET /info
curl http://localhost:8080/hello/info
```

## Project Structure
```
hello-rest-api/
├── pom.xml
└── src/main/
    ├── java/com/javaprogram/hello/
    │   ├── HelloRestApiApplication.java   ← @SpringBootApplication entry point
    │   └── controller/
    │       └── HelloController.java       ← @RestController with endpoints
    └── resources/
        └── application.properties         ← app config (port, name, etc.)
```

## Spring Boot Key Annotations
| Annotation | Purpose |
|-----------|---------|
| `@SpringBootApplication` | Enables auto-configuration, component scanning |
| `@RestController` | Marks class as REST controller (returns data, not HTML) |
| `@GetMapping("/path")` | Maps GET requests to a method |
| `@PostMapping("/path")` | Maps POST requests to a method |
| `@PathVariable` | Extracts `{variable}` from URL |
| `@RequestBody` | Binds HTTP request body to method parameter |
