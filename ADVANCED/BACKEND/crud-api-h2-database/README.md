# crud-api-h2-database

A complete CRUD REST API for a Task management system using Spring Boot + Spring Data JPA + H2 in-memory database. This is the **canonical modern Java backend portfolio project**.

## What It Teaches
- Spring Data JPA with `JpaRepository` — zero-boilerplate data access
- `@Entity` / `@Table` JPA annotations for ORM mapping
- CRUD HTTP endpoints: GET, POST, PUT, DELETE
- H2 embedded database — no separate server install needed
- Response status codes: 200 OK, 201 Created, 204 No Content, 404 Not Found

## How to Run
```
mvn spring-boot:run
```

## H2 Console (browser-based DB viewer)
Visit: **http://localhost:8080/h2-console**  
JDBC URL: `jdbc:h2:mem:taskdb`  
Username: `sa`  Password: (blank)

## Test Endpoints

```bash
# Create a task
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Buy groceries","description":"Milk, eggs, bread"}'

# List all tasks
curl http://localhost:8080/api/tasks

# Get one task
curl http://localhost:8080/api/tasks/1

# Update task
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Buy groceries","description":"Milk, eggs, bread","completed":true}'

# Delete task
curl -X DELETE http://localhost:8080/api/tasks/1

# Filter completed tasks
curl "http://localhost:8080/api/tasks?completed=false"
```

## Project Structure
```
src/main/java/com/javaprogram/crud/
├── CrudApiApplication.java          ← @SpringBootApplication
├── model/Task.java                  ← @Entity (maps to DB table)
├── repository/TaskRepository.java   ← extends JpaRepository (all CRUD free)
├── dto/TaskRequest.java             ← Request body shape
└── controller/TaskController.java   ← @RestController (HTTP endpoints)
```
