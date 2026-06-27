/**
 * Day 04 — Loops: for, while, do-while, enhanced for, break/continue
 * Run: java Lesson
 */
public class Lesson {

    public static void main(String[] args) {

        System.out.println("=== Day 04: Loops ===\n");

        // ── 1. FOR LOOP ─────────────────────────────────────────────────────
        System.out.println("--- for loop ---");
        // Syntax: for (initializer; condition; update)
        // Runs while condition is true; update runs at the end of each iteration.
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
        System.out.println();

        // Counting DOWN
        System.out.println("Counting down:");
        for (int i = 5; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println(); // newline after the row
        System.out.println();

        // ── 2. WHILE LOOP ───────────────────────────────────────────────────
        System.out.println("--- while loop ---");
        // Use while when you don't know the exact number of iterations.
        int n = 1;
        while (n <= 32) {    // double n until it exceeds 32
            System.out.print(n + " ");
            n *= 2;
        }
        System.out.println();
        System.out.println("Final n = " + n);
        System.out.println();

        // ── 3. DO-WHILE LOOP ────────────────────────────────────────────────
        System.out.println("--- do-while loop ---");
        // The body runs AT LEAST ONCE because the condition is checked AFTER.
        int x = 100;
        do {
            System.out.println("do-while body executed, x = " + x);
            x++;
        } while (x < 100);   // condition is false immediately, but body ran once
        System.out.println();

        // ── 4. ENHANCED FOR-LOOP (for-each) ────────────────────────────────
        System.out.println("--- enhanced for (for-each) ---");
        int[] numbers = {10, 20, 30, 40, 50};
        int sum = 0;
        for (int num : numbers) {   // reads as "for each num in numbers"
            System.out.print(num + " ");
            sum += num;
        }
        System.out.println("\nSum = " + sum);
        System.out.println();

        String[] fruits = {"apple", "banana", "cherry", "date"};
        for (String fruit : fruits) {
            System.out.println("Fruit: " + fruit);
        }
        System.out.println();

        // ── 5. BREAK ────────────────────────────────────────────────────────
        System.out.println("--- break ---");
        // break exits the loop immediately
        for (int i = 1; i <= 10; i++) {
            if (i == 6) {
                System.out.println("  Breaking at i = " + i);
                break;
            }
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();

        // ── 6. CONTINUE ─────────────────────────────────────────────────────
        System.out.println("--- continue (skip even numbers) ---");
        // continue skips the rest of the current iteration
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) continue;  // skip even numbers
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();

        // ── 7. NESTED LOOPS ─────────────────────────────────────────────────
        System.out.println("--- nested loops (3x3 multiplication) ---");
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                System.out.printf("%4d", row * col);
            }
            System.out.println();  // new line after each row
        }
        System.out.println();

        // ── 8. ACCUMULATOR PATTERN ──────────────────────────────────────────
        System.out.println("--- accumulator: sum of 1 to 100 ---");
        int total = 0;
        for (int i = 1; i <= 100; i++) {
            total += i;
        }
        System.out.println("Sum(1..100) = " + total);  // 5050
        System.out.println("Formula check: " + (100 * 101 / 2));
        System.out.println();

        // ── 9. FINDING A VALUE (search pattern) ─────────────────────────────
        System.out.println("--- search pattern ---");
        int[] data = {4, 7, 2, 9, 1, 5, 8};
        int   target = 9;
        int   foundAt = -1;

        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                foundAt = i;
                break;   // no need to keep searching
            }
        }

        if (foundAt >= 0) {
            System.out.println("Found " + target + " at index " + foundAt);
        } else {
            System.out.println(target + " not found.");
        }
        System.out.println();

        System.out.println("=== End of Day 04 Lesson ===");
    }
}
