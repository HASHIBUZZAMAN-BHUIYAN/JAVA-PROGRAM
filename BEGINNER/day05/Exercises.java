/**
 * Day 05 — Methods — Exercises
 * Complete each TODO. Run: java Exercises
 */
public class Exercises {

    // Exercise 1: Write a method that returns the larger of two integers.
    static int max(int a, int b) {
        // TODO: return the larger of a or b
        return 0;
    }

    // Exercise 2: Overload max for doubles.
    static double max(double a, double b) {
        // TODO: return the larger of a or b
        return 0.0;
    }

    // Exercise 3: Write a method isPrime(int n) that returns true if n is prime.
    static boolean isPrime(int n) {
        // TODO: return true if n is a prime number (hint: try divisors 2..sqrt(n))
        return false;
    }

    // Exercise 4: Write a method that reverses a String.
    static String reverse(String s) {
        // TODO: return the reversed version of s
        return "";
    }

    // Exercise 5: Write a method factorial(int n) returning n! (n factorial).
    // factorial(5) = 5 * 4 * 3 * 2 * 1 = 120
    static long factorial(int n) {
        // TODO: calculate and return n!
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Exercise 1 - max(3,7): " + max(3, 7));          // expect 7
        System.out.println("Exercise 2 - max(1.5,2.5): " + max(1.5, 2.5));  // expect 2.5
        System.out.println("Exercise 3 - isPrime(7): " + isPrime(7));        // expect true
        System.out.println("Exercise 3 - isPrime(9): " + isPrime(9));        // expect false
        System.out.println("Exercise 4 - reverse(\"Java\"): " + reverse("Java")); // expect avaJ
        System.out.println("Exercise 5 - factorial(5): " + factorial(5));    // expect 120
    }
}
