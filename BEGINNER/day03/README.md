# Day 03 — Conditionals: if/else & switch

## Objectives
- Write if, if-else, and if-else-if chains
- Use the ternary operator `? :`
- Write traditional `switch` statements
- Use modern switch expressions with `->` (Java 14+)

## Concepts Covered
| Concept | Syntax |
|---|---|
| if | `if (condition) { }` |
| if-else | `if (...) { } else { }` |
| if-else-if chain | `if (...) { } else if (...) { } else { }` |
| Ternary operator | `condition ? valueIfTrue : valueIfFalse` |
| switch statement | `switch (x) { case 1: ... break; default: ... }` |
| switch expression (Java 14+) | `String s = switch (x) { case 1 -> "one"; default -> "?"; };` |

## Files
| File | Purpose |
|---|---|
| `Lesson.java` | Teaching walkthrough |
| `Exercises.java` | Practice problems |
| `Solutions.java` | Completed solutions |
| `MiniProject.java` | Mini-project: Grade & Season Classifier |
| `build.bat` | Compiles and runs Lesson |

## Compile & Run
```
build.bat
java MiniProject
java Solutions
```

## Time Estimate
60–90 minutes

## Key Takeaways
1. The condition in `if` must be a `boolean` expression.
2. Switch expressions (with `->`) are cleaner and don't need `break`.
3. The ternary `? :` is great for short one-line decisions.
4. Always include a `default` case in switch statements/expressions.
