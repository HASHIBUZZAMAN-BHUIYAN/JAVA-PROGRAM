# Day 01 — Generics

## Learning Objectives
- Understand why generics exist and what problems they solve
- Write generic classes and generic methods
- Use bounded type parameters (`<T extends Comparable<T>>`)
- Understand wildcards (`<?>`, `<? extends T>`, `<? super T>`)

## Concepts Covered
| Concept | Description |
|---------|-------------|
| Generic class | `class Box<T> { T value; }` |
| Generic method | `<T> T identity(T t)` |
| Bounded type | `<T extends Comparable<T>>` restricts what T can be |
| Upper bounded wildcard | `<? extends Number>` — read-only list of Numbers or subtypes |
| Lower bounded wildcard | `<? super Integer>` — write list of Integers or supertypes |
| Unbounded wildcard | `<?>` — unknown type, most restrictive |

## Files
| File | Purpose |
|------|---------|
| `Lesson.java` | Annotated teaching program — run this first |
| `Exercises.java` | 4 TODO exercises for you to complete |
| `Solutions.java` | Reference solutions |
| `MiniProject.java` | Generic typed stack implementation |
| `build.bat` | Compile and run commands |

## Compile & Run
```
build.bat
```
Or manually:
```
javac *.java
java Lesson
java MiniProject
```

## Time Estimate
~90 minutes (30 min lesson + 40 min exercises + 20 min mini-project)
