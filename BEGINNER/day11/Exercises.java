/**
 * Day 11 — Exception Handling — Exercises
 */

class NegativeAmountException extends RuntimeException {
    NegativeAmountException(double amount) {
        super("Amount cannot be negative: " + amount);
    }
}

public class Exercises {

    // Exercise 1: Parse a String to int safely. Return -1 if parsing fails.
    static int safeParseInt(String s) {
        // TODO: use try/catch for NumberFormatException
        return 0;
    }

    // Exercise 2: Divide a by b. Throw ArithmeticException if b==0.
    // Return 0.0 if exception is caught internally (handle it here, don't re-throw).
    static double safeDivide(double a, double b) {
        // TODO
        return 0;
    }

    // Exercise 3: Throw NegativeAmountException if amount < 0, else return amount * 1.1.
    static double addInterest(double amount) throws NegativeAmountException {
        // TODO
        return 0;
    }

    // Exercise 4: Demonstrate finally — print "cleanup" in finally block.
    static void demonstrateFinally() {
        // TODO: try block that may or may not throw, finally prints "cleanup"
    }

    public static void main(String[] args) {
        System.out.println("safeParseInt(\"42\"): " + safeParseInt("42"));    // 42
        System.out.println("safeParseInt(\"abc\"): " + safeParseInt("abc")); // -1

        System.out.println("safeDivide(10,2): " + safeDivide(10, 2));     // 5.0
        System.out.println("safeDivide(10,0): " + safeDivide(10, 0));     // 0.0

        try {
            System.out.println("addInterest(100): " + addInterest(100));  // 110.0
            System.out.println("addInterest(-5): " + addInterest(-5));    // throws
        } catch (NegativeAmountException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        demonstrateFinally();
    }
}
