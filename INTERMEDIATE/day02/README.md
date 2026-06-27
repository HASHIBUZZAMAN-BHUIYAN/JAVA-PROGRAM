# Day 02 — Advanced Collections

## Learning Objectives
- Use `LinkedList` as a doubly-linked list and as a `Deque`
- Understand sorted collections: `TreeMap` and `TreeSet`
- Work with `Queue`, `Deque`, `ArrayDeque` and `PriorityQueue`
- Know WHEN to choose each collection type

## Concepts Covered
| Collection | When to use |
|------------|-------------|
| `ArrayList` | Fast random access, frequent reads |
| `LinkedList` | Frequent insert/delete at head or middle |
| `TreeSet` | Sorted unique elements, range queries |
| `TreeMap` | Sorted key-value pairs, ceiling/floor keys |
| `ArrayDeque` | Stack or Queue use-case (faster than `Stack`/`LinkedList`) |
| `PriorityQueue` | Process items by priority (min-heap by default) |
| `HashMap` | Fastest key lookup, no ordering needed |

## Files
| File | Purpose |
|------|---------|
| `Lesson.java` | Annotated teaching program |
| `Exercises.java` | 4 TODO exercises |
| `Solutions.java` | Reference solutions |
| `MiniProject.java` | Task scheduler using PriorityQueue |
| `build.bat` | Compile and run |

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
~90 minutes
