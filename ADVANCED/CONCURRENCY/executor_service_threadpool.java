/**
 * ADVANCED/CONCURRENCY — ExecutorService & Thread Pool
 *
 * Compile: javac executor_service_threadpool.java
 * Run:     java ExecutorServiceThreadPool
 */
import java.util.concurrent.*;
import java.util.*;
import java.util.stream.*;

class ExecutorServiceThreadPool {

    // ── 1. Fixed Thread Pool ──────────────────────────────────────────────────
    static void demoFixedThreadPool() throws InterruptedException {
        System.out.println("\n--- Fixed Thread Pool (4 threads) ---");
        ExecutorService pool = Executors.newFixedThreadPool(4);

        List<Runnable> tasks = IntStream.rangeClosed(1, 8).mapToObj(i -> (Runnable) () -> {
            String name = Thread.currentThread().getName();
            System.out.printf("  Task %d executed by %s%n", i, name);
            try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }).collect(Collectors.toList());

        tasks.forEach(pool::submit);
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
    }

    // ── 2. Callable + Future (return a result) ────────────────────────────────
    static void demoCallableFuture() throws Exception {
        System.out.println("\n--- Callable + Future ---");
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // Callable returns a value; Future holds the eventual result
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final int n = i;
            futures.add(pool.submit(() -> {
                Thread.sleep(50);
                return n * n;  // compute square
            }));
        }

        // Collect results — .get() blocks until result is ready
        for (int i = 0; i < futures.size(); i++) {
            System.out.printf("  Square of %d = %d%n", i+1, futures.get(i).get());
        }
        pool.shutdown();
    }

    // ── 3. invokeAll — submit many callables and wait for all ────────────────
    static void demoInvokeAll() throws Exception {
        System.out.println("\n--- invokeAll ---");
        ExecutorService pool = Executors.newFixedThreadPool(4);

        List<Callable<String>> tasks = List.of(
            () -> { Thread.sleep(200); return "Task A done"; },
            () -> { Thread.sleep(100); return "Task B done"; },
            () -> { Thread.sleep(300); return "Task C done"; }
        );

        List<Future<String>> results = pool.invokeAll(tasks);
        results.forEach(f -> {
            try { System.out.println("  " + f.get()); }
            catch (Exception e) { e.printStackTrace(); }
        });
        pool.shutdown();
    }

    // ── 4. ScheduledExecutorService ──────────────────────────────────────────
    static void demoScheduled() throws InterruptedException {
        System.out.println("\n--- ScheduledExecutorService ---");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Run once after 200ms
        scheduler.schedule(() -> System.out.println("  One-shot task fired!"), 200, TimeUnit.MILLISECONDS);

        // Run repeatedly every 150ms, starting after 100ms
        int[] counter = new int[]{0};
        ScheduledFuture<?> handle = scheduler.scheduleAtFixedRate(() -> {
            System.out.println("  Heartbeat #" + ++counter[0]);
        }, 100, 150, TimeUnit.MILLISECONDS);

        Thread.sleep(700);
        handle.cancel(false);
        scheduler.shutdown();
        scheduler.awaitTermination(1, TimeUnit.SECONDS);
    }

    // ── 5. Work-Stealing Pool (ForkJoinPool) ─────────────────────────────────
    static void demoWorkStealing() throws InterruptedException {
        System.out.println("\n--- Work-Stealing Pool ---");
        ExecutorService pool = Executors.newWorkStealingPool(); // one thread per CPU core

        long count = IntStream.rangeClosed(1, 10_000)
            .parallel()
            .filter(n -> {
                if (n < 2) return false;
                for (int i = 2; i * i <= n; i++) if (n % i == 0) return false;
                return true;
            })
            .count();

        System.out.println("  Primes up to 10,000: " + count);
        pool.shutdown();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== ExecutorService & Thread Pool ===");
        demoFixedThreadPool();
        demoCallableFuture();
        demoInvokeAll();
        demoScheduled();
        demoWorkStealing();
        System.out.println("\n=== Done ===");
    }
}
