/**
 * Day 03 — Exercises
 * Complete each TODO. Run: java Exercises
 */
public class Exercises {

    public static void main(String[] args) {
        System.out.println("=== Day 03 Exercises ===\n");
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        System.out.println("\n=== Done! See Solutions.java to compare. ===");
    }

    /**
     * Exercise 1 — Leap Year Checker
     * Given int year = 2024, determine if it's a leap year.
     * Rules:
     *   - Divisible by 4 AND (not divisible by 100 OR divisible by 400)
     * Print: "2024 is a leap year." or "2024 is not a leap year."
     */
    static void exercise1() {
        System.out.println("-- Exercise 1: Leap Year --");
        int year = 2024;
        // TODO: use if-else to check the leap year conditions
    }

    /**
     * Exercise 2 — Triangle Classifier
     * Given int a = 5, b = 5, c = 8, classify the triangle:
     *   - All three equal → "Equilateral"
     *   - Exactly two equal → "Isosceles"
     *   - No two equal → "Scalene"
     * Also print whether it's valid (sum of any two sides > third side).
     */
    static void exercise2() {
        System.out.println("-- Exercise 2: Triangle Classifier --");
        int a = 5, b = 5, c = 8;
        // TODO: classify the triangle
        // TODO: check if the triangle is valid
    }

    /**
     * Exercise 3 — Ternary Practice
     * Using only the ternary operator (no if/else), print:
     * (a) Whether int n = -7 is "positive" or "non-positive"
     * (b) Whether int x = 14 is "even" or "odd"
     * (c) The larger of int p = 33 and int q = 27
     */
    static void exercise3() {
        System.out.println("-- Exercise 3: Ternary --");
        int n = -7;
        int x = 14;
        int p = 33, q = 27;
        // TODO: (a) print "positive" or "non-positive" using ternary
        // TODO: (b) print "even" or "odd" using ternary
        // TODO: (c) print the larger value using ternary
    }

    /**
     * Exercise 4 — Month Details (switch expression)
     * Given String month = "July", use a modern switch expression to find:
     *   - numberOfDays (28/29/30/31)
     *   - quarter (Q1–Q4)
     * Print both. (Treat February as 28 days for simplicity.)
     */
    static void exercise4() {
        System.out.println("-- Exercise 4: Month Details --");
        String month = "July";
        // TODO: use switch expression to find numberOfDays
        // TODO: use switch expression to find quarter
        // TODO: print both results
    }

    /**
     * Exercise 5 — BMI Category
     * Given double bmi = 24.3, classify using if-else-if:
     *   < 18.5       → "Underweight"
     *   18.5 – 24.9  → "Normal weight"
     *   25.0 – 29.9  → "Overweight"
     *   >= 30.0      → "Obese"
     * Print: "BMI 24.3 → Normal weight"
     */
    static void exercise5() {
        System.out.println("-- Exercise 5: BMI Category --");
        double bmi = 24.3;
        // TODO: classify bmi with if-else-if chain
    }
}
