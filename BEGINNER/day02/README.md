# Day 02 — Operators, Expressions, Type Casting & `final`

## Objectives
- Use arithmetic, comparison, and logical operators
- Understand operator precedence
- Cast between numeric types (widening and narrowing)
- Declare constants with the `final` keyword

## Concepts Covered
| Concept | Example |
|---|---|
| Arithmetic operators | `+  -  *  /  %` |
| Increment/decrement | `++  --` (prefix vs postfix) |
| Comparison operators | `==  !=  <  >  <=  >=` |
| Logical operators | `&&  \|\|  !` |
| Compound assignment | `+=  -=  *=  /=  %=` |
| Widening cast | `int → double` (automatic) |
| Narrowing cast | `(int) 3.9` → `3` (manual, truncates) |
| `final` keyword | `final double PI = 3.14159;` |

## Files
| File | Purpose |
|---|---|
| `Lesson.java` | Teaching walkthrough |
| `Exercises.java` | Practice problems |
| `Solutions.java` | Completed solutions |
| `MiniProject.java` | Mini-project: Simple Calculator |
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
1. Integer division truncates: `7 / 2 == 3`, not `3.5`.
2. Widening conversions are safe and automatic; narrowing requires an explicit cast.
3. `final` variables cannot be reassigned — they are constants.
4. `%` is the modulo (remainder) operator: `10 % 3 == 1`.
