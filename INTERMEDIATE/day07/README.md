# INTERMEDIATE Day 07 — Unit Testing with JUnit 5

## Objectives
- Write JUnit 5 tests: `@Test`, `@BeforeEach`, `@AfterEach`, `@DisplayName`
- Use assertions: `assertEquals`, `assertThrows`, `assertTrue`, `assertNull`
- Organize tests in a Maven project under `src/test/java`
- Run tests with `mvn test`

## Why Testing Matters
Untested code is broken code you haven't found yet. Tests:
- Catch regressions when you change code
- Document expected behavior
- Give you confidence to refactor

## Maven Test Project
The `maven-test-project/` folder is a full Maven project with JUnit 5:
```
maven-test-project/
├── pom.xml
└── src/
    ├── main/java/.../Calculator.java    ← code to test
    └── test/java/.../CalculatorTest.java ← the tests
```

Run tests: `cd maven-test-project && mvn test`

## Lesson Files (plain-javac)
The `Lesson.java`, `Exercises.java`, `Solutions.java` in this folder demonstrate testing concepts conceptually (you can't run JUnit tests without Maven/JUnit JAR, so these show the patterns via plain Java).

## Time Estimate
60–90 minutes
