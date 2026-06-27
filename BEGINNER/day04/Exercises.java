/**
 * Day 04 — Exercises
 * Complete each TODO. Run: java Exercises
 */
public class Exercises {

    public static void main(String[] args) {
        System.out.println("=== Day 04 Exercises ===\n");
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        System.out.println("\n=== Done! See Solutions.java to compare. ===");
    }

    /**
     * Exercise 1 — FizzBuzz
     * Print numbers 1 to 30.
     *   - If divisible by 3 AND 5 → print "FizzBuzz"
     *   - If divisible by 3 only  → print "Fizz"
     *   - If divisible by 5 only  → print "Buzz"
     *   - Otherwise               → print the number
     */
    static void exercise1() {
        System.out.println("-- Exercise 1: FizzBuzz (1-30) --");
        // TODO: use a for loop from 1 to 30 with the FizzBuzz rules
    }

    /**
     * Exercise 2 — Sum of Digits
     * Given int number = 94827, use a while loop to compute the sum of its digits.
     * Print: "Sum of digits of 94827 = 30"
     * Hint: repeatedly do number % 10 to get the last digit, then number /= 10.
     */
    static void exercise2() {
        System.out.println("-- Exercise 2: Sum of Digits --");
        int number = 94827;
        int original = number;
        // TODO: use a while loop to extract and sum the digits
        // TODO: print the result
    }

    /**
     * Exercise 3 — Array Statistics
     * Given the array: {14, 3, 77, 22, 51, 9, 45}
     * Use an enhanced for-loop to find:
     *   - sum, min, max, and average
     * Print each value.
     */
    static void exercise3() {
        System.out.println("-- Exercise 3: Array Statistics --");
        int[] values = {14, 3, 77, 22, 51, 9, 45};
        // TODO: find sum, min, max using enhanced for-loop
        // TODO: compute and print average (as double)
    }

    /**
     * Exercise 4 — Star Triangle
     * Use nested for-loops to print a right-angled triangle of stars:
     *   *
     *   **
     *   ***
     *   ****
     *   *****
     * (5 rows)
     */
    static void exercise4() {
        System.out.println("-- Exercise 4: Star Triangle --");
        // TODO: nested loops to print the triangle
    }

    /**
     * Exercise 5 — Prime Number Finder
     * Print all prime numbers between 2 and 50 on one line.
     * A prime has no divisors other than 1 and itself.
     * Hint: for each candidate n, check if any number from 2 to sqrt(n) divides it.
     */
    static void exercise5() {
        System.out.println("-- Exercise 5: Primes up to 50 --");
        // TODO: outer loop 2..50; inner loop checks divisibility; print primes
    }
}
