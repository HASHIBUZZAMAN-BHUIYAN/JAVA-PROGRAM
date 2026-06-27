/**
 * Day 11 — Exception Handling — Solutions
 */

class NegativeAmountExceptionSol extends RuntimeException {
    NegativeAmountExceptionSol(double amount) {
        super("Amount cannot be negative: " + amount);
    }
}

public class Solutions {

    static int safeParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static double safeDivide(double a, double b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            return 0.0;
        }
    }

    static double addInterest(double amount) throws NegativeAmountExceptionSol {
        if (amount < 0) throw new NegativeAmountExceptionSol(amount);
        return amount * 1.1;
    }

    static void demonstrateFinally() {
        System.out.println("demonstrateFinally:");
        try {
            System.out.println("  In try block");
            // no exception here
        } finally {
            System.out.println("  cleanup");
        }
    }

    public static void main(String[] args) {
        System.out.println("safeParseInt(\"42\"): " + safeParseInt("42"));
        System.out.println("safeParseInt(\"abc\"): " + safeParseInt("abc"));
        System.out.println("safeDivide(10,2): " + safeDivide(10, 2));
        System.out.println("safeDivide(10,0): " + safeDivide(10, 0));

        try {
            System.out.println("addInterest(100): " + addInterest(100));
            System.out.println("addInterest(-5): " + addInterest(-5));
        } catch (NegativeAmountExceptionSol e) {
            System.out.println("Caught: " + e.getMessage());
        }

        demonstrateFinally();
    }
}
