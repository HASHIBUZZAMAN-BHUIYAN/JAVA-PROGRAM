/**
 * Day 02 — Exercises
 * Complete each TODO. Run: java Exercises
 */
public class Exercises {

    public static void main(String[] args) {
        System.out.println("=== Day 02 Exercises ===\n");
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        System.out.println("\n=== Done! See Solutions.java to compare. ===");
    }

    /**
     * Exercise 1 — Arithmetic Practice
     * Given int a = 23 and int b = 7, print the result of all five
     * arithmetic operations (+, -, *, /, %) with a label.
     */
    static void exercise1() {
        System.out.println("-- Exercise 1: Arithmetic --");
        int a = 23, b = 7;
        // TODO: print a+b, a-b, a*b, a/b, a%b with labels
    }

    /**
     * Exercise 2 — Remainder Magic
     * Use the % operator to determine:
     * (a) whether 144 is even or odd  → print "144 is even" or "144 is odd"
     * (b) what the last digit of 3847 is  → print "Last digit of 3847: X"
     * Hint: n % 2 == 0 means even; n % 10 gives the last digit.
     */
    static void exercise2() {
        System.out.println("-- Exercise 2: Remainder --");
        // TODO: (a) even/odd check for 144
        // TODO: (b) last digit of 3847
    }

    /**
     * Exercise 3 — Type Casting
     * (a) Start with double celsius = 36.6. Cast to int and print both values.
     * (b) You have int items = 7 and int boxes = 2. Print the exact (double)
     *     number of items per box.
     */
    static void exercise3() {
        System.out.println("-- Exercise 3: Type Casting --");
        double celsius = 36.6;
        int items = 7, boxes = 2;
        // TODO: (a) print celsius and its int cast
        // TODO: (b) print items / boxes as a double result
    }

    /**
     * Exercise 4 — Constants
     * Declare final constants for:
     *   SPEED_OF_LIGHT = 299_792_458  (int, metres per second)
     *   GRAVITY = 9.81 (double, m/s^2)
     *   PLANET = "Earth" (String)
     * Print each constant with a descriptive label.
     * Note the underscore in 299_792_458 — Java allows _ inside numeric literals!
     */
    static void exercise4() {
        System.out.println("-- Exercise 4: Constants --");
        // TODO: declare the three final constants
        // TODO: print each with a label
    }

    /**
     * Exercise 5 — Compound Assignment
     * Start with int score = 50.
     * Apply these operations in order using compound assignment:
     *   +30, *2, -10, /3, %7
     * Print the value of score after each step.
     */
    static void exercise5() {
        System.out.println("-- Exercise 5: Compound Assignment --");
        int score = 50;
        System.out.println("Start: score = " + score);
        // TODO: score += 30; then print
        // TODO: score *= 2;  then print
        // TODO: score -= 10; then print
        // TODO: score /= 3;  then print
        // TODO: score %= 7;  then print
    }
}
