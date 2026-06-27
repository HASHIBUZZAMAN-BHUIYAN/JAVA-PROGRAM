# Java Program — Complete Curriculum

A full Java learning collection: 14-day beginner track, 10-day intermediate track, and a professional-level advanced section (Backend, Android, Concurrency, Trending).

## Quick Setup

> See [JAVA_SETUP.md](JAVA_SETUP.md) for JDK 21 + Maven installation details.

**Requirements:** JDK 21, Apache Maven 3.9.9

```bat
:: Verify environment
java -version           :: should say 21
mvn  -version           :: should say 3.9.x
```

---

## Repository Structure

```
JAVA-PROGRAM/
├── PRACTICE/
│   └── concurrency-circular-buffer/   ← Original exercise (producer-consumer)
│
├── BEGINNER/          14-day track — fundamentals to mini-projects
│   ├── day01/ … day14/
│   └── (each day: README.md + Lesson.java + Exercises.java + Solutions.java + MiniProject.java + build.bat)
│
├── INTERMEDIATE/      10-day track — generics through capstone Maven project
│   ├── day01/ … day10/
│   └── (same format; day06/07/10 are Maven projects)
│
├── ADVANCED/
│   ├── BACKEND/       6 Spring Boot REST API projects
│   ├── ANDROID/       Android concept demos in plain Java
│   ├── CONCURRENCY/   4 concurrency concept files
│   └── TRENDING/      Kafka, GraphQL, Docker, Testing
│
├── JAVA_SETUP.md      Environment setup guide
└── README.md          This file
```

---

## BEGINNER Track (14 Days)

Run any day: `cd BEGINNER\dayXX && build.bat`  
Or manually: `javac *.java && java Lesson`

| Day | Topic | Mini Project |
|-----|-------|--------------|
| [day01](BEGINNER/day01/) | Variables, primitives, print statements | Hello World variants |
| [day02](BEGINNER/day02/) | Operators, casting, `final` | Unit converter |
| [day03](BEGINNER/day03/) | Conditionals, switch expressions | Grade calculator |
| [day04](BEGINNER/day04/) | Loops: while, do-while, for, break/continue | Number guessing game |
| [day05](BEGINNER/day05/) | Methods, overloading, return types | Calculator |
| [day06](BEGINNER/day06/) | Arrays 1D/2D, `Arrays.sort` | Student grade tracker |
| [day07](BEGINNER/day07/) | Strings, StringBuilder, String methods | Word frequency counter |
| [day08](BEGINNER/day08/) | OOP I — classes, constructors, encapsulation | Contact book |
| [day09](BEGINNER/day09/) | OOP II — inheritance, `super`, polymorphism | Payroll system |
| [day10](BEGINNER/day10/) | OOP III — abstract classes, interfaces | Payment processing |
| [day11](BEGINNER/day11/) | Exceptions — try/catch/finally, custom exceptions | Form validator |
| [day12](BEGINNER/day12/) | Collections I — ArrayList, HashMap | Todo manager |
| [day13](BEGINNER/day13/) | Collections II — Set, Comparator, Streams intro | Student report card |
| [day14](BEGINNER/day14/) | Capstone: Library System (file I/O, full OOP) | Interactive CLI library |

---

## INTERMEDIATE Track (10 Days)

| Day | Topic | How to Run |
|-----|-------|-----------|
| [day01](INTERMEDIATE/day01/) | Generics — `<T>`, bounded wildcards, generic methods | `javac *.java && java Lesson` |
| [day02](INTERMEDIATE/day02/) | Advanced Collections — TreeMap, LinkedHashMap, PriorityQueue, Deque | `javac *.java && java Lesson` |
| [day03](INTERMEDIATE/day03/) | Lambdas & Functional Interfaces — `Function`, `Predicate`, `Consumer`, `Supplier` | `javac *.java && java Lesson` |
| [day04](INTERMEDIATE/day04/) | Streams deep dive — `collect`, `groupingBy`, `reduce`, `flatMap`, parallel | `javac *.java && java Lesson` |
| [day05](INTERMEDIATE/day05/) | File I/O with `java.nio.file` — `Path`, `Files`, walking directories | `javac *.java && java Lesson` |
| [day06](INTERMEDIATE/day06/) | Maven intro project — Guava dependency, `exec-maven-plugin` | `cd day06 && mvn compile exec:java` |
| [day07](INTERMEDIATE/day07/) | JUnit 5 testing — `@Test`, assertions, `maven-test-project` | `cd day07/maven-test-project && mvn test` |
| [day08](INTERMEDIATE/day08/) | Design Patterns I — Singleton, Factory, Builder | `javac *.java && java Lesson` |
| [day09](INTERMEDIATE/day09/) | Design Patterns II — Observer, Strategy, Decorator | `javac *.java && java Lesson` |
| [day10](INTERMEDIATE/day10/) | Capstone Maven Project — all patterns + JUnit 5 test suite | `cd day10 && mvn test && mvn exec:java` |

---

## ADVANCED — Backend (6 Spring Boot Projects)

All use Spring Boot 3.3.4 + Java 21. Start each with `mvn spring-boot:run` (or `build.bat`).

| Project | Port | What It Teaches |
|---------|------|----------------|
| [hello-rest-api](ADVANCED/BACKEND/hello-rest-api/) | 8080 | `@RestController`, GET/POST, `@PathVariable`, `@RequestBody` |
| [crud-api-h2-database](ADVANCED/BACKEND/crud-api-h2-database/) | 8080 | Spring Data JPA, `@Entity`, full CRUD, H2 console |
| [layered-architecture-demo](ADVANCED/BACKEND/layered-architecture-demo/) | 8080 | Controller → Service interface → Repository → Entity |
| [rest-api-validation-errorhandling](ADVANCED/BACKEND/rest-api-validation-errorhandling/) | 8080 | `@Valid`, `@NotBlank`, `@Positive`, `@RestControllerAdvice` |
| [microservice-pair-demo](ADVANCED/BACKEND/microservice-pair-demo/) | 8081+8082 | Two services: products-service + orders-service with `RestTemplate` |
| [jwt-auth-demo](ADVANCED/BACKEND/jwt-auth-demo/) | 8080 | Spring Security 6, JWT (JJWT 0.12.3), `OncePerRequestFilter` |

---

## ADVANCED — Android (Concept Demos)

Plain-Java simulations of core Android patterns (no Android Studio required to study the code).

| File | Concept |
|------|---------|
| [activity_lifecycle_concept.java](ADVANCED/ANDROID/activity_lifecycle_concept.java) | Activity state machine |
| [android_mvvm_pattern_demo.java](ADVANCED/ANDROID/android_mvvm_pattern_demo.java) | MVVM: ViewModel, LiveData, Repository |
| [room_database_concept_demo.java](ADVANCED/ANDROID/room_database_concept_demo.java) | DAO/Repository/Entity pattern |

See [ADVANCED/ANDROID/README.md](ADVANCED/ANDROID/README.md) and `ANDROID_REAL_SETUP.md` for real Android Studio setup.

---

## ADVANCED — Concurrency

| File | Topic | Run |
|------|-------|-----|
| [thread_basics_and_synchronization.java](ADVANCED/CONCURRENCY/thread_basics_and_synchronization.java) | Thread creation, race conditions, `synchronized`, `wait`/`notify` | `javac thread_basics_and_synchronization.java && java ThreadBasicsAndSynchronization` |
| [executor_service_threadpool.java](ADVANCED/CONCURRENCY/executor_service_threadpool.java) | `ExecutorService`, `Callable`/`Future`, `ScheduledExecutorService` | `javac executor_service_threadpool.java && java ExecutorServiceThreadPool` |
| [concurrent_collections_demo.java](ADVANCED/CONCURRENCY/concurrent_collections_demo.java) | `ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue`, `AtomicInteger`, `Semaphore` | `javac concurrent_collections_demo.java && java ConcurrentCollectionsDemo` |
| [completablefuture_async.java](ADVANCED/CONCURRENCY/completablefuture_async.java) | `CompletableFuture`, async pipelines, `thenCompose`, `allOf`, error handling | `javac completablefuture_async.java && java CompletableFutureAsync` |

---

## ADVANCED — Trending

| Folder | What It Covers | Run |
|--------|---------------|-----|
| [kafka-style-messaging](ADVANCED/TRENDING/kafka-style-messaging/) | Topics, offsets, consumer groups — Kafka concepts in plain Java | `build.bat` |
| [graphql-concept](ADVANCED/TRENDING/graphql-concept/) | Field selection, nested queries — GraphQL concepts in plain Java | `build.bat` |
| [docker-ready-spring-app](ADVANCED/TRENDING/docker-ready-spring-app/) | Multi-stage Dockerfile for Spring Boot (JDK build → JRE runtime) | See `DOCKER.md` |
| [testing-best-practices](ADVANCED/TRENDING/testing-best-practices/) | JUnit 5 + Mockito: mocks, stubs, `ArgumentCaptor`, parameterized tests | `mvn test` |

---

## PRACTICE (Original Exercise)

| Folder | Description |
|--------|-------------|
| [concurrency-circular-buffer](PRACTICE/concurrency-circular-buffer/) | Producer-consumer with synchronized circular buffer (`wait`/`notify`) |

---

## Technology Reference

| Technology | Version | Used In |
|-----------|---------|---------|
| Java (Eclipse Temurin) | 21 LTS | Everything |
| Apache Maven | 3.9.9 | INTERMEDIATE day06+, all BACKEND |
| Spring Boot | 3.3.4 | All BACKEND projects |
| Spring Data JPA | (via Boot) | crud-api, layered-arch, validation |
| H2 Database | (via Boot) | crud-api, layered-arch, validation |
| Spring Security | (via Boot) | jwt-auth-demo |
| JJWT | 0.12.3 | jwt-auth-demo |
| JUnit 5 (Jupiter) | 5.10.2 | INTERMEDIATE day07, day10, testing-best-practices |
| Mockito | 5.11.0 | testing-best-practices |
| Google Guava | 33.0.0-jre | INTERMEDIATE day06 |

## Machine Notes (Ryzen 7 5000, 8GB RAM, Windows)
- All databases use H2 in-memory — no PostgreSQL/MySQL install required
- Maven preferred over Gradle (faster on limited RAM)
- No ML/AI libraries (no GPU)
- Spring Boot apps use ~300–400MB RAM each; don't run multiple simultaneously
