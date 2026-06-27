# Multi-stage build: stage 1 compiles, stage 2 creates minimal runtime image
# Stage 1: Build with Maven (includes full JDK + Maven)
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY pom.xml .
# Download dependencies first (cached if pom.xml unchanged)
RUN mvn dependency:resolve -q || true
COPY src ./src
RUN mvn package -DskipTests -q

# Stage 2: Run with just JRE (smaller image — ~200MB vs ~500MB)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
