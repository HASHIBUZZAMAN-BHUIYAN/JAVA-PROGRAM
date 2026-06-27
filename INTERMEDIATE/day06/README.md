# INTERMEDIATE Day 06 — Introduction to Maven

## What is Maven?
Maven is a build tool and dependency manager for Java projects. It:
- Downloads dependencies (libraries) automatically from Maven Central
- Defines a standard project structure every Java developer recognizes
- Provides a lifecycle: `compile → test → package → install → deploy`

## Standard Maven Directory Layout
```
project/
├── pom.xml                          ← Project Object Model: config + dependencies
└── src/
    ├── main/
    │   └── java/                    ← Your production source code
    │       └── com/example/pkg/
    │           └── Main.java
    └── test/
        └── java/                    ← Your test code (Day 07)
            └── com/example/pkg/
                └── MainTest.java
```

## How to Build and Run
```
mvn compile                                                      # Compile
mvn exec:java -Dexec.mainClass="com.javaprogram.mavenintro.Main" # Run
mvn package                                                      # Create JAR
```

Or double-click `build.bat`.

## What This Project Does
Uses Google Guava (a popular Java library) to demonstrate how Maven dependencies work.
The `pom.xml` declares the dependency; Maven downloads it automatically on first build.

## Time Estimate
60 minutes
