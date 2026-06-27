# Java Toolchain Setup

## What Was Installed

| Tool | Version | How Installed |
|------|---------|--------------|
| Eclipse Temurin JDK 21 (LTS) | 21.0.11+10 | `winget install EclipseAdoptium.Temurin.21.JDK` |
| Apache Maven | 3.9.9 | Downloaded from archive.apache.org, extracted to `%LOCALAPPDATA%\Maven\` |

**Why these versions:**
- JDK 21 is the current LTS release and the minimum required for Spring Boot 3.x.
- Maven 3.9.x is the current stable release; simpler than Gradle for learning.

---

## Verify Your Setup

Open a **new** terminal (not one that was open before install) and run:

```
java -version
javac -version
mvn -version
```

Expected output (versions may differ slightly):
```
openjdk version "21.0.11" 2026-04-21 LTS
javac 21.0.11
Apache Maven 3.9.9 (...)
Java version: 21.0.11
```

If `mvn` is not found, check that `%LOCALAPPDATA%\Maven\apache-maven-3.9.9\bin` is in your PATH
and that `JAVA_HOME` points to `C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot`.

---

## Environment Variables (Windows)

These are set in your **User** environment (Control Panel > System > Advanced > Environment Variables):

| Variable | Value |
|----------|-------|
| `JAVA_HOME` | `C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot` |
| `PATH` (added) | `%LOCALAPPDATA%\Maven\apache-maven-3.9.9\bin` |

---

## Command Patterns

### Single-file Java (BEGINNER / CONCURRENCY / ANDROID concept files)

```batch
# Compile
javac MyClass.java

# Run (class name, not filename — no .java extension)
java MyClass

# Example for Day 01
cd BEGINNER\day01
javac Lesson.java
java Lesson
```

Or just double-click `build.bat` in each day folder — it runs the right commands automatically.

### Maven project (INTERMEDIATE day06+ and all ADVANCED/BACKEND)

A Maven project has `pom.xml` at its root and sources under `src/main/java/`.

```batch
# Compile
mvn compile

# Run tests
mvn test

# Run the main class (non-Spring)
mvn exec:java -Dexec.mainClass="com.example.Main"

# Run a Spring Boot app
mvn spring-boot:run

# Package into a runnable JAR
mvn package
java -jar target/myapp-1.0.jar
```

### Compile + run a multi-file package (when no build.bat exists)

```batch
# From the folder containing the .java files
javac -d out *.java
java -cp out MainClassName
```

---

## Useful Java CLI flags

| Flag | What it does |
|------|-------------|
| `javac -d outdir File.java` | Compile into a specific output directory |
| `java -cp path:path Main` | Set classpath (use `;` on Windows instead of `:`) |
| `java -ea Main` | Enable assertions |
| `javac --release 17 *.java` | Compile targeting Java 17 compatibility |

---

## IDE Setup (Optional)

- **VS Code**: Install the "Extension Pack for Java" from Microsoft (bundles language server, debugger, Maven support).
- **IntelliJ IDEA Community**: Free, best Java IDE — download from jetbrains.com. Point it at `JAVA_HOME` above.
- **Eclipse IDE**: Traditional Java IDE — download from eclipse.org.

For this repo's BEGINNER days, a plain text editor + terminal is enough.
For ADVANCED/BACKEND Spring Boot projects, VS Code or IntelliJ will give you much better autocomplete.
