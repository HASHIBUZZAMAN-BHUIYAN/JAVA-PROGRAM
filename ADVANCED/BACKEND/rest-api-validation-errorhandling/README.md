# rest-api-validation-errorhandling

Demonstrates production-grade input validation and centralized error handling in Spring Boot.

## What It Teaches
- **Bean Validation** (`@NotBlank`, `@Positive`, `@Size`, `@PositiveOrZero`) on request DTOs
- **`@Valid`** in the controller to trigger validation automatically
- **`@RestControllerAdvice`** — one place to handle all exceptions across all controllers
- Custom exceptions (`ProductNotFoundException`) with semantic HTTP status codes
- Structured JSON error responses with timestamp, status, and field-level error details

## How to Run
```
mvn spring-boot:run
```

## Test Validation

```bash
# Valid request — succeeds
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Laptop","price":999.99,"stock":10}'

# Missing name — 400 Bad Request with field errors
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"","price":-5,"stock":-1}'

# Not found — 404 with structured error
curl http://localhost:8080/api/products/999
```

## Error Response Format
```json
{
  "timestamp": "2024-01-01T12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "fieldErrors": {
    "name": "Product name is required",
    "price": "Price must be greater than zero"
  }
}
```
