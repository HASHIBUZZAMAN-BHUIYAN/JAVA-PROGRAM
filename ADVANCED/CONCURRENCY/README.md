# ADVANCED — Concurrency

Java concurrency from fundamentals to modern async patterns.

## Files

| File | Topic | Compile & Run |
|------|-------|--------------|
| `thread_basics_and_synchronization.java` | Thread creation, race conditions, `synchronized`, `wait`/`notify` | `javac thread_basics_and_synchronization.java && java ThreadBasicsAndSynchronization` |
| `executor_service_threadpool.java` | `ExecutorService`, `Callable`/`Future`, `ScheduledExecutorService` | `javac executor_service_threadpool.java && java ExecutorServiceThreadPool` |
| `concurrent_collections_demo.java` | `ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue`, `AtomicInteger`, `Semaphore` | `javac concurrent_collections_demo.java && java ConcurrentCollectionsDemo` |
| `completablefuture_async.java` | `CompletableFuture`: `supplyAsync`, `thenApply`, `thenCompose`, `thenCombine`, `allOf`, `exceptionally` | `javac completablefuture_async.java && java CompletableFutureAsync` |

## Learning Progression

1. **Thread basics** — raw threads, `synchronized`, `wait`/`notify`
2. **Thread pools** — `ExecutorService` manages thread lifecycle, `Callable` returns results
3. **Concurrent collections** — thread-safe data structures, `BlockingQueue` for producer-consumer
4. **CompletableFuture** — composable async pipelines, parallel execution, error handling

## See Also
`PRACTICE/concurrency-circular-buffer/` — the original exercise that motivated this section, using `synchronized`/`wait`/`notify` for a bounded buffer.
