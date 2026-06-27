# layered-architecture-demo

Demonstrates the standard 4-layer Spring Boot architecture: Controller → Service → Repository → Entity.

## Architecture

```
HTTP Request
     ↓
[Controller]   ← Handles HTTP, delegates to service
     ↓
[Service]      ← Business logic, interface + implementation separate
     ↓
[Repository]   ← Database access via Spring Data JPA
     ↓
[Entity]       ← Maps to database table via JPA annotations
```

## Key Concepts
- **BookService interface** — controller depends on abstraction, not implementation (Dependency Inversion)
- **BookServiceImpl @Service** — concrete implementation injected by Spring
- **Record DTO** — `BookRequest` record (Java 16+) as clean immutable request body
- **Exception to HTTP status** — `NoSuchElementException` → 404 Not Found

## Run
```
mvn spring-boot:run
```

## Endpoints
```
GET    /api/books               list all books
GET    /api/books/{id}          get one book
POST   /api/books               create book
PUT    /api/books/{id}          update book
DELETE /api/books/{id}          delete book
GET    /api/books/search?q=...  search by title
GET    /api/books/author/{name} filter by author
GET    /api/books/affordable?max=30 filter by price
```
