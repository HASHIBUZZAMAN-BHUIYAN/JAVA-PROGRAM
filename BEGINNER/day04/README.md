# Day 04 — Loops: for, while, do-while, enhanced for, break/continue

## Objectives
- Write counted loops with `for`
- Write condition-based loops with `while` and `do-while`
- Iterate over arrays/collections with the enhanced for-loop
- Control loop flow with `break` and `continue`

## Concepts Covered
| Concept | Syntax |
|---|---|
| for loop | `for (int i = 0; i < n; i++) { }` |
| while loop | `while (condition) { }` |
| do-while loop | `do { } while (condition);` |
| enhanced for | `for (int x : array) { }` |
| break | Exits the loop immediately |
| continue | Skips the rest of the current iteration |
| nested loops | A loop inside another loop |

## Files
| File | Purpose |
|---|---|
| `Lesson.java` | Teaching walkthrough |
| `Exercises.java` | Practice problems |
| `Solutions.java` | Completed solutions |
| `MiniProject.java` | Mini-project: Multiplication Table & Pattern Printer |
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
1. Use `for` when you know the number of iterations; `while` when you don't.
2. `do-while` always executes the body at least once.
3. The enhanced for-loop is read-only — you can't modify the array with it.
4. `break` and `continue` work with the innermost enclosing loop by default.
