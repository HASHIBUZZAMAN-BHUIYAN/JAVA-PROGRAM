/**
 * ADVANCED/CONCURRENCY — Concurrent Collections
 * Thread-safe data structures from java.util.concurrent.
 *
 * Compile: javac concurrent_collections_demo.java
 * Run:     java ConcurrentCollectionsDemo
 */
import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.atomic.*;

class ConcurrentCollectionsDemo {

    // ── 1. ConcurrentHashMap ─────────────────────────────────────────────────
    static void demoConcurrentHashMap() throws InterruptedException {
        System.out.println("\n--- ConcurrentHashMap ---");
        ConcurrentHashMap<String, AtomicInteger> wordCount = new ConcurrentHashMap<>();

        String[] words = {"java", "python", "java", "go", "java", "python", "rust", "go"};

        // Multiple threads increment counts safely — no external synchronization needed
        List<Thread> threads = new ArrayList<>();
        for (String word : words) {
            threads.add(new Thread(() ->
                wordCount.computeIfAbsent(word, k -> new AtomicInteger(0)).incrementAndGet()
            ));
        }
        threads.forEach(Thread::start);
        for (Thread t : threads) t.join();

        System.out.println("  Word counts: " + wordCount);
    }

    // ── 2. CopyOnWriteArrayList ───────────────────────────────────────────────
    static void demoCopyOnWriteList() throws InterruptedException {
        System.out.println("\n--- CopyOnWriteArrayList ---");
        // Each write creates a snapshot copy — safe to iterate while other threads write
        // Best for read-heavy workloads with rare writes (e.g., event listener lists)
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                list.add("Item-" + i);
                try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        });

        writer.start();

        // Reader iterates safely without ConcurrentModificationException
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("  Snapshot: " + list);
                try { Thread.sleep(60); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        });
        reader.start();
        writer.join(); reader.join();
    }

    // ── 3. BlockingQueue (ArrayBlockingQueue) ────────────────────────────────
    static void demoBlockingQueue() throws InterruptedException {
        System.out.println("\n--- BlockingQueue (producer-consumer) ---");
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3); // capacity 3

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    queue.put(i);  // blocks when queue is full
                    System.out.println("  Produced: " + i + "  (queue size: " + queue.size() + ")");
                    Thread.sleep(80);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 6; i++) {
                    int val = queue.take();  // blocks when queue is empty
                    System.out.println("  Consumed: " + val);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        producer.start(); consumer.start();
        producer.join();  consumer.join();
    }

    // ── 4. AtomicInteger / AtomicLong ────────────────────────────────────────
    static void demoAtomicInteger() throws InterruptedException {
        System.out.println("\n--- AtomicInteger vs non-atomic race ─--");
        AtomicInteger atomicCounter = new AtomicInteger(0);
        int[] unsafeCounter = {0};

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(() -> {
                atomicCounter.incrementAndGet();
                unsafeCounter[0]++;  // race condition!
            }));
        }
        threads.forEach(Thread::start);
        for (Thread t : threads) t.join();

        System.out.println("  AtomicInteger (correct): " + atomicCounter.get());
        System.out.println("  Unsafe int[] (racy, may lose updates): " + unsafeCounter[0]);
    }

    // ── 5. Semaphore (limit concurrency) ─────────────────────────────────────
    static void demoSemaphore() throws InterruptedException {
        System.out.println("\n--- Semaphore (max 2 concurrent) ---");
        Semaphore semaphore = new Semaphore(2); // at most 2 threads in critical section

        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final int id = i;
            threads.add(new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.printf("  Thread %d acquired semaphore (permits left: %d)%n",
                                      id, semaphore.availablePermits());
                    Thread.sleep(150);
                    System.out.printf("  Thread %d releasing%n", id);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    semaphore.release();
                }
            }));
        }
        threads.forEach(Thread::start);
        for (Thread t : threads) t.join();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Concurrent Collections Demo ===");
        demoConcurrentHashMap();
        demoCopyOnWriteList();
        demoBlockingQueue();
        demoAtomicInteger();
        demoSemaphore();
        System.out.println("\n=== Done ===");
    }
}
