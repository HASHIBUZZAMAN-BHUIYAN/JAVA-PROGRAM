import java.util.concurrent.atomic.AtomicInteger;

/**
 * THREAD BASICS AND SYNCHRONIZATION
 *
 * Three-part program covering the fundamentals of Java multithreading:
 *   Part 1 — Creating threads (extends Thread vs implements Runnable)
 *   Part 2 — Race conditions: the problem and the fix (synchronized)
 *   Part 3 — Visibility with volatile, Thread.sleep(), Thread.join()
 */
public class ThreadBasicsAndSynchronization {

    // =========================================================================
    // PART 1: CREATING THREADS
    // =========================================================================

    // Approach 1: extends Thread
    // Simple but not recommended — you can't extend another class if you extend Thread
    static class CounterThread extends Thread {
        private String threadName;
        private int count;

        CounterThread(String name, int count) {
            super(name);
            this.threadName = name;
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 1; i <= count; i++) {
                System.out.println("  [" + threadName + "] count: " + i);
                try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }
    }

    // Approach 2: implements Runnable (PREFERRED)
    // Can extend other classes, separates task from thread management
    static class PrintTask implements Runnable {
        private String message;
        private int times;

        PrintTask(String message, int times) {
            this.message = message;
            this.times = times;
        }

        @Override
        public void run() {
            for (int i = 1; i <= times; i++) {
                System.out.println("  [" + Thread.currentThread().getName() + "] " + message + " #" + i);
                try { Thread.sleep(30); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }
    }

    static void part1() throws InterruptedException {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  PART 1: CREATING THREADS");
        System.out.println("=".repeat(60));

        System.out.println("\n-- Approach 1: extends Thread --");
        CounterThread t1 = new CounterThread("Thread-Alpha", 3);
        CounterThread t2 = new CounterThread("Thread-Beta", 3);
        t1.start();
        t2.start();
        t1.join(); // wait for t1 to finish
        t2.join(); // wait for t2 to finish

        System.out.println("\n-- Approach 2: implements Runnable (preferred) --");
        Runnable task1 = new PrintTask("Hello from Runnable", 3);
        Runnable task2 = new PrintTask("Greetings from Runnable", 3);
        Thread rt1 = new Thread(task1, "Runnable-One");
        Thread rt2 = new Thread(task2, "Runnable-Two");
        rt1.start();
        rt2.start();
        rt1.join();
        rt2.join();

        System.out.println("\n-- Approach 3: Lambda (Runnable as lambda) --");
        Thread lambdaThread = new Thread(() -> {
            System.out.println("  [" + Thread.currentThread().getName() + "] Lambda thread running!");
        }, "Lambda-Thread");
        lambdaThread.start();
        lambdaThread.join();

        System.out.println("\nWHY prefer Runnable over Thread extension?");
        System.out.println("  - Java supports single inheritance only.");
        System.out.println("  - Extending Thread prevents extending your own base class.");
        System.out.println("  - Runnable separates WHAT to do from HOW to run it.");
    }

    // =========================================================================
    // PART 2: RACE CONDITIONS — THE PROBLEM AND THE FIX
    // =========================================================================

    // Unsafe counter — no synchronization
    static class UnsafeCounter {
        int count = 0;
        void increment() { count++; } // NOT atomic: read-modify-write is 3 operations!
    }

    // Safe counter — synchronized method
    static class SynchronizedCounter {
        int count = 0;
        synchronized void increment() { count++; } // lock acquired before each call
        synchronized int getCount() { return count; }
    }

    // Safe counter — using AtomicInteger (even better, no lock needed)
    static class AtomicCounter {
        AtomicInteger count = new AtomicInteger(0);
        void increment() { count.incrementAndGet(); } // single hardware instruction
    }

    static void part2() throws InterruptedException {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  PART 2: RACE CONDITIONS");
        System.out.println("=".repeat(60));

        final int INCREMENTS_PER_THREAD = 100_000;
        final int EXPECTED = INCREMENTS_PER_THREAD * 2;

        // --- DEMONSTRATE THE RACE CONDITION ---
        System.out.println("\n-- WITHOUT synchronization (race condition) --");
        System.out.println("  Each thread increments counter " + INCREMENTS_PER_THREAD + " times.");
        System.out.println("  Expected total: " + EXPECTED);

        UnsafeCounter unsafeCounter = new UnsafeCounter();
        Thread u1 = new Thread(() -> {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) unsafeCounter.increment();
        });
        Thread u2 = new Thread(() -> {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) unsafeCounter.increment();
        });
        u1.start(); u2.start();
        u1.join(); u2.join();
        System.out.println("  Actual total:   " + unsafeCounter.count);
        System.out.println("  Lost updates:   " + (EXPECTED - unsafeCounter.count));
        System.out.println("  WHY? count++ compiles to 3 steps:");
        System.out.println("    1. READ count from memory");
        System.out.println("    2. ADD 1");
        System.out.println("    3. WRITE back to memory");
        System.out.println("  Thread A can read '5', Thread B reads '5', both write '6'");
        System.out.println("  — one increment is LOST.");

        // --- FIX 1: synchronized ---
        System.out.println("\n-- FIX 1: synchronized method --");
        SynchronizedCounter syncCounter = new SynchronizedCounter();
        Thread s1 = new Thread(() -> {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) syncCounter.increment();
        });
        Thread s2 = new Thread(() -> {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) syncCounter.increment();
        });
        s1.start(); s2.start();
        s1.join(); s2.join();
        System.out.println("  Synchronized result: " + syncCounter.getCount() + " (expected: " + EXPECTED + ")");
        System.out.println("  Correct: " + (syncCounter.getCount() == EXPECTED));

        // --- FIX 2: AtomicInteger ---
        System.out.println("\n-- FIX 2: AtomicInteger (preferred for simple counters) --");
        AtomicCounter atomicCounter = new AtomicCounter();
        Thread a1 = new Thread(() -> {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) atomicCounter.increment();
        });
        Thread a2 = new Thread(() -> {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) atomicCounter.increment();
        });
        a1.start(); a2.start();
        a1.join(); a2.join();
        System.out.println("  Atomic result: " + atomicCounter.count.get() + " (expected: " + EXPECTED + ")");
        System.out.println("  Correct: " + (atomicCounter.count.get() == EXPECTED));
        System.out.println("  AtomicInteger uses CPU-level CAS (Compare-And-Swap) — no lock needed!");
    }

    // =========================================================================
    // PART 3: volatile, Thread.sleep(), Thread.join()
    // =========================================================================

    // Without volatile: the JVM may cache 'running' in a CPU register.
    // The worker thread might never see the main thread's update to 'running = false'.
    static volatile boolean running = true; // volatile = always read from main memory

    static void part3() throws InterruptedException {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  PART 3: volatile, sleep(), join()");
        System.out.println("=".repeat(60));

        // --- volatile demo ---
        System.out.println("\n-- volatile flag for thread signaling --");
        Thread worker = new Thread(() -> {
            System.out.println("  [Worker] Starting. Watching 'running' flag...");
            int iterations = 0;
            while (running) {   // reads from main memory every time due to volatile
                iterations++;
                // tight loop — checking the flag
            }
            System.out.println("  [Worker] Stopped after " + iterations + " iterations.");
            System.out.println("  [Worker] 'running' was set to false by main thread.");
        });

        worker.start();
        Thread.sleep(10); // let worker run a bit
        System.out.println("  [Main] Setting running = false...");
        running = false;  // visible to worker thread immediately due to volatile
        worker.join();    // wait for worker to finish
        System.out.println("  [Main] Worker has stopped.");

        // --- Thread.sleep() ---
        System.out.println("\n-- Thread.sleep() --");
        System.out.println("  sleep() pauses the current thread for a given duration.");
        System.out.println("  It does NOT release any locks the thread holds.");
        System.out.println("  Always handle InterruptedException.");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(200); // sleep 200ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupt flag
            System.out.println("  Thread was interrupted during sleep!");
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("  Slept for ~" + elapsed + "ms (expected ~200ms)");

        // --- Thread.join() ---
        System.out.println("\n-- Thread.join() --");
        System.out.println("  join() makes the calling thread wait until target thread finishes.");
        Thread slowTask = new Thread(() -> {
            System.out.println("  [SlowTask] Starting long operation...");
            try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            System.out.println("  [SlowTask] Done!");
        });
        slowTask.start();
        System.out.println("  [Main] Waiting for SlowTask to finish...");
        slowTask.join(); // blocks until slowTask completes
        System.out.println("  [Main] SlowTask has finished. Continuing.");

        // --- join() with timeout ---
        System.out.println("\n-- Thread.join(timeout) --");
        Thread verySlowTask = new Thread(() -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        verySlowTask.start();
        System.out.println("  [Main] Waiting max 500ms for verySlowTask...");
        verySlowTask.join(500); // give up after 500ms
        System.out.println("  [Main] Done waiting. Task still alive: " + verySlowTask.isAlive());
        verySlowTask.interrupt(); // clean up the background thread

        System.out.println("\n" + "=".repeat(60));
        System.out.println("  SUMMARY");
        System.out.println("=".repeat(60));
        System.out.println("  Thread creation : Runnable/lambda > extends Thread");
        System.out.println("  Race conditions : Use synchronized or Atomic* classes");
        System.out.println("  volatile        : Ensures visibility across threads (not atomicity)");
        System.out.println("  sleep()         : Pause thread; always catch InterruptedException");
        System.out.println("  join()          : Wait for another thread to finish");
        System.out.println("\n  NEXT: See executor_service_threadpool.java for the modern approach.");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("THREAD BASICS AND SYNCHRONIZATION");
        part1();
        part2();
        part3();
    }
}
