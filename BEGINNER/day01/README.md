# Day 01 — Java Basics

## Objectives
- Understand the structure of a Java program
- Write and run your first `public static void main` method
- Declare and use primitive types: `int`, `double`, `boolean`, `char`
- Print output with `System.out.println` and `System.out.print`

## Concepts Covered
| Concept | Description |
|---|---|
| `public static void main(String[] args)` | Entry point of every Java program |
| `int` | 32-bit integer (-2,147,483,648 to 2,147,483,647) |
| `double` | 64-bit floating-point number |
| `boolean` | `true` or `false` |
| `char` | Single Unicode character, written in single quotes |
| `String` | Text (not a primitive — a class) |
| `System.out.println` | Prints a line and moves to the next line |
| `System.out.print` | Prints without a newline |

## Files
| File | Purpose |
|---|---|
| `Lesson.java` | Teaching walkthrough — run this first |
| `Exercises.java` | Practice problems with TODO stubs |
| `Solutions.java` | Completed solutions to the exercises |
| `MiniProject.java` | Mini-project: Personal Info Card |
| `build.bat` | Compiles and runs Lesson |

## Compile & Run
```
build.bat          # compiles everything, then runs Lesson
javac *.java       # compile manually
java Lesson        # run the lesson
java MiniProject   # run the mini-project
java Solutions     # see exercise solutions
```

## Time Estimate
60–90 minutes (including exercises and mini-project)

## Key Takeaways
1. Every Java program needs a class whose name matches the filename.
2. Execution always starts at `public static void main(String[] args)`.
3. Java is **strongly typed** — you must declare a variable's type before using it.
4. Primitive types hold values directly; `String` is an object.
