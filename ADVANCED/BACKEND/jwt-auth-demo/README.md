# jwt-auth-demo

Spring Boot REST API with JWT-based stateless authentication.

## What It Teaches
- Spring Security 6 configuration (`SecurityFilterChain` bean)
- JWT generation and validation with JJWT 0.12.3
- `OncePerRequestFilter` — intercepts every request to validate the token
- Stateless sessions (`SessionCreationPolicy.STATELESS`)
- In-memory users (BCrypt-hashed passwords); swap for DB-backed `UserDetailsService` in production

## How to Run
```
mvn spring-boot:run
```

## Demo Credentials (in-memory)
| Username | Password    | Role  |
|----------|-------------|-------|
| alice    | password123 | USER  |
| admin    | admin123    | ADMIN |

## Test Authentication

**Step 1 — Login (get token):**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"password123"}'
# Response: {"token":"eyJ...", "type":"Bearer"}
```

**Step 2 — Use token to access protected endpoints:**
```bash
TOKEN="eyJ..."   # paste the token from step 1

curl http://localhost:8080/api/me -H "Authorization: Bearer $TOKEN"
curl http://localhost:8080/api/data -H "Authorization: Bearer $TOKEN"
```

**Step 3 — Without token (403 Forbidden):**
```bash
curl http://localhost:8080/api/me
# {"error":"Access Denied"}
```

## Request Flow
```
Client → POST /api/auth/login → AuthController
  → AuthenticationManager.authenticate()
  → JwtService.generateToken() → returns JWT

Client → GET /api/me  with "Authorization: Bearer <jwt>"
  → JwtAuthenticationFilter extracts + validates JWT
  → SecurityContext set with authenticated user
  → ProtectedController.whoAmI() executes
```
