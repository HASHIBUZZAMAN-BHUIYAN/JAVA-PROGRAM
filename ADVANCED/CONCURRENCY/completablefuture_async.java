/**
 * ADVANCED/CONCURRENCY — CompletableFuture and Async Pipelines
 * Modern way to write non-blocking async code in Java.
 *
 * Compile: javac completablefuture_async.java
 * Run:     java CompletableFutureAsync
 */
import java.util.concurrent.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class CompletableFutureAsync {

    // Simulate async operations with delay
    static String fetchUser(int id) throws InterruptedException {
        Thread.sleep(200);
        return "User-" + id;
    }

    static List<String> fetchOrders(String user) throws InterruptedException {
        Thread.sleep(150);
        return List.of(user + ":Order1", user + ":Order2");
    }

    static double calculateTotal(List<String> orders) throws InterruptedException {
        Thread.sleep(100);
        return orders.size() * 49.99;
    }

    // ── 1. Basic supplyAsync ──────────────────────────────────────────────────
    static void demoBasic() throws Exception {
        System.out.println("\n--- Basic supplyAsync ---");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try { return fetchUser(42); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        });

        // Non-blocking — main thread continues while async work runs
        System.out.println("  Async task submitted, doing other work...");
        String result = cf.get(); // block here when needed
        System.out.println("  Result: " + result);
    }

    // ── 2. Chaining: thenApply / thenCompose ─────────────────────────────────
    static void demoChaining() throws Exception {
        System.out.println("\n--- Chaining (thenApply, thenCompose) ---");

        CompletableFuture<Double> pipeline = CompletableFuture
            // Step 1: fetch user (async)
            .supplyAsync(() -> {
                try { return fetchUser(7); } catch (InterruptedException e) { throw new RuntimeException(e); }
            })
            // Step 2: transform result synchronously (thenApply = map)
            .thenApply(user -> {
                System.out.println("  Got user: " + user);
                return user.toUpperCase();
            })
            // Step 3: another async step (thenCompose = flatMap for CompletableFuture)
            .thenCompose(user -> CompletableFuture.supplyAsync(() -> {
                try { return fetchOrders(user); } catch (InterruptedException e) { throw new RuntimeException(e); }
            }))
            // Step 4: compute total
            .thenApply(orders -> {
                System.out.println("  Orders: " + orders);
                try { return calculateTotal(orders); } catch (InterruptedException e) { throw new RuntimeException(e); }
            });

        System.out.printf("  Total: $%.2f%n", pipeline.get());
    }

    // ── 3. thenCombine — run two futures in parallel, combine results ─────────
    static void demoCombine() throws Exception {
        System.out.println("\n--- Parallel futures + thenCombine ---");

        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            try { return fetchUser(1); } catch (InterruptedException e) { throw new RuntimeException(e); }
        });

        CompletableFuture<String> profileFuture = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(180); return "Profile[age=30, city=Seoul]"; }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        });

        // Both run in parallel; combined when BOTH complete
        String combined = userFuture.thenCombine(profileFuture,
            (user, profile) -> user + " | " + profile).get();
        System.out.println("  Combined: " + combined);
    }

    // ── 4. allOf — wait for multiple futures ─────────────────────────────────
    static void demoAllOf() throws Exception {
        System.out.println("\n--- allOf (parallel tasks) ---");

        List<CompletableFuture<String>> futures = IntStream.rangeClosed(1, 5).mapToObj(i ->
            CompletableFuture.supplyAsync(() -> {
                try { Thread.sleep(100 + i * 50); return "Result-" + i; }
                catch (InterruptedException e) { throw new RuntimeException(e); }
            })
        ).collect(Collectors.toList());

        // Wait for all to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // Collect results
        List<String> results = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println("  All results: " + results);
    }

    // ── 5. exceptionally — error handling ────────────────────────────────────
    static void demoErrorHandling() throws Exception {
        System.out.println("\n--- Error handling (exceptionally) ---");

        CompletableFuture<String> cf = CompletableFuture
            .supplyAsync(() -> {
                if (true) throw new RuntimeException("Simulated service failure!");
                return "data";
            })
            .exceptionally(ex -> {
                System.out.println("  Caught: " + ex.getMessage());
                return "fallback-data";
            });

        System.out.println("  Result: " + cf.get());

        // handle() — runs whether success or failure, can inspect result + exception
        CompletableFuture<String> handled = CompletableFuture
            .supplyAsync(() -> "success")
            .handle((result, ex) -> {
                if (ex != null) return "error: " + ex.getMessage();
                return result.toUpperCase();
            });
        System.out.println("  Handled: " + handled.get());
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== CompletableFuture Async Demo ===");
        demoBasic();
        demoChaining();
        demoCombine();
        demoAllOf();
        demoErrorHandling();
        System.out.println("\n=== Done ===");
    }
}
