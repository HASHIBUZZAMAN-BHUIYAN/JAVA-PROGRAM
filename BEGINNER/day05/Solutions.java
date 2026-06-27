/**
 * Day 05 — Methods — Solutions
 */
public class Solutions {

    static int max(int a, int b) {
        return a > b ? a : b;
    }

    static double max(double a, double b) {
        return a > b ? a : b;
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    static long factorial(int n) {
        if (n <= 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    public static void main(String[] args) {
        System.out.println("max(3,7): " + max(3, 7));
        System.out.println("max(1.5,2.5): " + max(1.5, 2.5));
        System.out.println("isPrime(7): " + isPrime(7));
        System.out.println("isPrime(9): " + isPrime(9));
        System.out.println("reverse(\"Java\"): " + reverse("Java"));
        System.out.println("factorial(5): " + factorial(5));
    }
}
