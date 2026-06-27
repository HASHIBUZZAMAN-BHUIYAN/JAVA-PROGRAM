# docker-ready-spring-app

This folder contains a `Dockerfile` and documentation for containerizing any Spring Boot application from this curriculum.

## What It Teaches
- Multi-stage Docker build (compile → minimal runtime image)
- Layer caching: dependencies downloaded separately from source code
- JDK vs JRE image choice (smaller production images)
- Passing Spring Boot config via environment variables

## Files
| File | Purpose |
|------|---------|
| `Dockerfile` | Multi-stage build: JDK/Maven stage → JRE runtime stage |
| `DOCKER.md` | Detailed instructions for building and running |

## Use with Any Project
To Dockerize one of the BACKEND projects (e.g., crud-api-h2-database):
1. Copy `Dockerfile` to that project directory
2. Run `docker build -t crud-api .` from that directory
3. Run `docker run -p 8080:8080 crud-api`

## Does Not Require Docker to Learn
Study the `Dockerfile` and `DOCKER.md` to understand the concepts.
You only need Docker installed to actually run it.
