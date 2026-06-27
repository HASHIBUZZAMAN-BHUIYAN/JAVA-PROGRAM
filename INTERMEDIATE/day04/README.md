# INTERMEDIATE Day 04 — Streams API Deep Dive

## Objectives
- Master `collect()` with `Collectors`: `toList()`, `toMap()`, `groupingBy()`, `joining()`
- Use `reduce()` for custom aggregation
- Understand lazy evaluation in streams
- Explore parallel streams (and when to use them)

## Concepts
- **Intermediate ops** (lazy): `filter`, `map`, `flatMap`, `sorted`, `distinct`, `limit`, `skip`
- **Terminal ops** (trigger execution): `collect`, `forEach`, `reduce`, `count`, `sum`, `findFirst`, `anyMatch`
- **`Collectors.groupingBy()`** — group elements by a key → `Map<K, List<V>>`
- **`Collectors.groupingBy()` + downstream** — e.g. count, sum, average per group
- **Parallel streams** — `.parallelStream()` — useful for CPU-intensive work on large data; adds overhead for small data

## How to Compile and Run
```
javac *.java
java Lesson
```

## Time Estimate
60–75 minutes
