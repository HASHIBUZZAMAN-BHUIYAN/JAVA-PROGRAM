# Day 03 — Lambda Expressions & Functional Interfaces

## Learning Objectives
- Write lambda expressions for single-abstract-method (SAM) interfaces
- Use the four core functional interfaces: `Function`, `Predicate`, `Consumer`, `Supplier`
- Chain functions with `andThen()`, `compose()`, `and()`, `or()`, `negate()`
- Use method references (`Class::method`, `instance::method`, `Class::new`)

## Concepts Covered
| Interface | Signature | Use case |
|-----------|-----------|----------|
| `Function<T,R>` | `R apply(T t)` | Transform a value |
| `Predicate<T>` | `boolean test(T t)` | Test a condition |
| `Consumer<T>` | `void accept(T t)` | Side-effect action |
| `Supplier<T>` | `T get()` | Produce a value lazily |
| `BiFunction<T,U,R>` | `R apply(T t, U u)` | Two inputs, one output |
| `UnaryOperator<T>` | `T apply(T t)` | Same type in and out |

## Files
| File | Purpose |
|------|---------|
| `Lesson.java` | Annotated teaching program |
| `Exercises.java` | 4 TODO exercises |
| `Solutions.java` | Reference solutions |
| `MiniProject.java` | Composable data pipeline using functional interfaces |
| `build.bat` | Compile and run |

## Compile & Run
```
build.bat
```
Or manually:
```
javac *.java
java Lesson
```

## Time Estimate
~90 minutes
