# Day 07 — Strings

## Objectives
- Use common String methods: `length()`, `charAt()`, `substring()`, `indexOf()`, `toUpperCase()`, `trim()`, `split()`, `replace()`
- Understand String immutability (every operation creates a new String)
- Use `StringBuilder` for efficient string building
- Compare strings correctly with `.equals()` vs `==`

## Concepts
- `String` is a class (capital S), not a primitive
- String literals are interned — `"hello" == "hello"` may be true, but never rely on it; always use `.equals()`
- `StringBuilder` is mutable — `append()` modifies in place without creating new objects
- `String.format()` / `printf` for formatted output

## How to Compile and Run
```
javac *.java
java Lesson
```

## Time Estimate
45–60 minutes
