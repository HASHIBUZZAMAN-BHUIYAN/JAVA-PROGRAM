/**
 * INTERMEDIATE Day 07 — Testing Exercises
 * Write tests using the SimpleTestRunner below (no JUnit needed for these).
 */

class MathHelper {
    static int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) if (n % i == 0) return false;
        return true;
    }
    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

class SimpleRunner {
    static int passed=0, failed=0;
    static void check(boolean cond, String msg) {
        System.out.println(cond ? "  ✓ " + msg : "  ✗ " + msg); if(cond) passed++; else failed++;
    }
    static void summary() { System.out.printf("  %d passed, %d failed%n", passed, failed); }
}

public class Exercises {
    public static void main(String[] args) {
        System.out.println("=== Testing Exercises ===\n");

        // Exercise 1: Test MathHelper.factorial()
        // Write at least 4 test cases (factorial of 0, 1, 5, negative)
        System.out.println("-- factorial() tests --");
        // TODO: add your test cases using SimpleRunner.check(...)
        SimpleRunner.check(MathHelper.factorial(0) == 1, "factorial(0) == 1");
        // TODO: add tests for factorial(1), factorial(5), factorial(negative throws exception)

        // Exercise 2: Test MathHelper.isPrime()
        System.out.println("\n-- isPrime() tests --");
        // TODO: test with prime numbers (2, 7, 13), non-primes (1, 4, 9), edge case (0)

        // Exercise 3: Test MathHelper.gcd()
        System.out.println("\n-- gcd() tests --");
        // TODO: test gcd(12,8)=4, gcd(100,75)=25, gcd(7,3)=1

        System.out.println();
        SimpleRunner.summary();
    }
}
