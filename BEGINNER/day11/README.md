# Day 11 — Exception Handling

## Objectives
- Use try/catch/finally to handle exceptions
- Understand checked vs unchecked exceptions
- Create custom exception classes
- Use multi-catch and try-with-resources

## Concepts
- **Checked exception** — must be declared (`throws`) or caught (e.g. `IOException`)
- **Unchecked exception** — extends `RuntimeException`, no requirement to catch (e.g. `NullPointerException`)
- **`finally`** — always runs, even if exception was thrown (used for cleanup)
- **try-with-resources** — automatically closes `AutoCloseable` resources
- **Custom exception** — extend `Exception` (checked) or `RuntimeException` (unchecked)

## How to Compile and Run
```
javac *.java
java Lesson
```

## Time Estimate
60 minutes
