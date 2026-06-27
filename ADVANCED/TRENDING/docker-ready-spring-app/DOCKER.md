# Docker-Ready Spring App

## Prerequisites
Install Docker Desktop: https://www.docker.com/products/docker-desktop/

## Build and Run

```bash
# Build the Docker image (takes ~2 min first time, cached after)
docker build -t java-demo-app .

# Run on port 8080
docker run -p 8080:8080 java-demo-app

# Run in background (detached)
docker run -d -p 8080:8080 --name myapp java-demo-app

# Stop and remove
docker stop myapp && docker rm myapp
```

## How the Multi-Stage Dockerfile Works

```
┌─────────────────────────────────────────────────────────┐
│ Stage 1: builder (eclipse-temurin:21-jdk-alpine)         │
│   - Full JDK + Maven                                     │
│   - Downloads dependencies (cached in layer)             │
│   - Compiles source: mvn package -DskipTests             │
│   - Produces: target/app.jar                             │
└────────────────────┬────────────────────────────────────┘
                     │ COPY --from=builder target/*.jar
┌────────────────────▼────────────────────────────────────┐
│ Stage 2: final (eclipse-temurin:21-jre-alpine)           │
│   - JRE only (no compiler) → ~200MB smaller image        │
│   - Just copies the compiled JAR                         │
│   - ENTRYPOINT: java -jar app.jar                        │
└─────────────────────────────────────────────────────────┘
```

## Why Alpine?
`-alpine` variants use Alpine Linux (~5MB) instead of Ubuntu/Debian (~100MB),
making the final image significantly smaller.

## Common Docker Commands
```bash
docker images           # list all images
docker ps               # list running containers
docker ps -a            # list all containers (including stopped)
docker logs myapp       # view container output
docker exec -it myapp sh  # open shell inside running container
docker pull eclipse-temurin:21-jre-alpine  # download base image
```

## Environment Variables
Pass config to the Spring app via `-e`:
```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:h2:mem:mydb \
  -e SERVER_PORT=8080 \
  java-demo-app
```
