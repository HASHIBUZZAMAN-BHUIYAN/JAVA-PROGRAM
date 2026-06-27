/**
 * INTERMEDIATE Day 07 — Testing Solutions
 */

class MathHelperSol {
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
    static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
}

class Runner {
    static int p=0, f=0;
    static void check(boolean c, String m) { System.out.println(c?"  ✓ "+m:"  ✗ "+m); if(c)p++;else f++; }
    static void checkThrows(Runnable r, String m) {
        try { r.run(); System.out.println("  ✗ "+m+" (no exception)"); f++; }
        catch (Exception e) { System.out.println("  ✓ "+m); p++; }
    }
    static void summary() { System.out.printf("  %d passed, %d failed%n",p,f); }
}

public class Solutions {
    public static void main(String[] args) {
        System.out.println("-- factorial() --");
        Runner.check(MathHelperSol.factorial(0) == 1,   "factorial(0) == 1");
        Runner.check(MathHelperSol.factorial(1) == 1,   "factorial(1) == 1");
        Runner.check(MathHelperSol.factorial(5) == 120, "factorial(5) == 120");
        Runner.checkThrows(() -> MathHelperSol.factorial(-1), "factorial(-1) throws");

        System.out.println("-- isPrime() --");
        Runner.check(MathHelperSol.isPrime(2),     "isPrime(2) true");
        Runner.check(MathHelperSol.isPrime(7),     "isPrime(7) true");
        Runner.check(MathHelperSol.isPrime(13),    "isPrime(13) true");
        Runner.check(!MathHelperSol.isPrime(1),    "isPrime(1) false");
        Runner.check(!MathHelperSol.isPrime(4),    "isPrime(4) false");
        Runner.check(!MathHelperSol.isPrime(9),    "isPrime(9) false");
        Runner.check(!MathHelperSol.isPrime(0),    "isPrime(0) false");

        System.out.println("-- gcd() --");
        Runner.check(MathHelperSol.gcd(12,8)   == 4,  "gcd(12,8) == 4");
        Runner.check(MathHelperSol.gcd(100,75) == 25, "gcd(100,75) == 25");
        Runner.check(MathHelperSol.gcd(7,3)    == 1,  "gcd(7,3) == 1");

        System.out.println();
        Runner.summary();
    }
}
