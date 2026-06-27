# Producer-Consumer with Circular Buffer

A classic Java concurrency exercise implementing the **producer-consumer problem** using a shared circular buffer synchronized with Java's built-in monitor (`synchronized`, `wait()`, `notify()`).

## What This Demonstrates

- `synchronized` methods as mutual exclusion locks
- `wait()` / `notify()` for thread coordination (Java monitor pattern)
- A circular (ring) buffer as the shared data structure
- Producer and consumer threads running concurrently

## Files

| File | Role |
|------|------|
| `Buffer.java` | Interface defining `set(int)` and `get()` |
| `CircularBuffer.java` | Synchronized circular buffer implementation |
| `Producer.java` | Thread that writes 10 values into the buffer |
| `Consumer.java` | Thread that reads 10 values from the buffer |
| `CircularBufferTest.java` | Entry point wiring everything together |
| `MainCircularBufferTest.java` | Self-contained single-file version (all classes in one file) — useful for online compilers |

## How to Compile and Run

### Using build.bat (simplest)
```
build.bat
```

### Manual (multi-file version)
```
javac Buffer.java CircularBuffer.java Producer.java Consumer.java CircularBufferTest.java
java CircularBufferTest
```

### Manual (single-file version — no extra files needed)
```
javac MainCircularBufferTest.java
java CircularBufferTest
```

## What You'll See

The program prints each buffer operation showing:
- Which thread is acting (Producer/Consumer)
- Current buffer contents
- Read (R) and Write (W) pointer positions
- Wait events when the buffer is full or empty

## How This Relates to Modern Java Concurrency

This example uses the **low-level Java monitor** approach (`synchronized` + `wait/notify`).
Modern Java code typically uses higher-level abstractions:
- `BlockingQueue` (e.g., `ArrayBlockingQueue`) handles the full/empty waiting automatically
- `ExecutorService` manages thread lifecycle
- `CompletableFuture` for async pipelines

See `ADVANCED/CONCURRENCY/concurrent_collections_demo.java` for a `BlockingQueue`-based version
of this same producer-consumer pattern, and a direct comparison of the two approaches.

## Source

Based on Fig. 6.6 from *Java How to Program* (Deitel & Deitel).
