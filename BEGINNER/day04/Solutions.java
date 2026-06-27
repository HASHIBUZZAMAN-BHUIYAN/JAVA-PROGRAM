/**
 * Day 04 — Solutions
 * Run: java Solutions
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== Day 04 Solutions ===\n");
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        System.out.println("\n=== All solutions complete. ===");
    }

    static void exercise1() {
        System.out.println("-- Exercise 1: FizzBuzz --");
        for (int i = 1; i <= 30; i++) {
            if      (i % 15 == 0) System.out.print("FizzBuzz ");
            else if (i % 3  == 0) System.out.print("Fizz ");
            else if (i % 5  == 0) System.out.print("Buzz ");
            else                   System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
    }

    static void exercise2() {
        System.out.println("-- Exercise 2: Sum of Digits --");
        int number   = 94827;
        int original = number;
        int sum      = 0;
        while (number > 0) {
            sum    += number % 10;   // grab last digit
            number /= 10;            // remove last digit
        }
        System.out.println("Sum of digits of " + original + " = " + sum);
        System.out.println();
    }

    static void exercise3() {
        System.out.println("-- Exercise 3: Array Statistics --");
        int[] values = {14, 3, 77, 22, 51, 9, 45};
        int sum = 0;
        int min = values[0];
        int max = values[0];
        for (int v : values) {
            sum += v;
            if (v < min) min = v;
            if (v > max) max = v;
        }
        double avg = (double) sum / values.length;
        System.out.println("Sum: " + sum);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.printf("Avg: %.2f%n", avg);
        System.out.println();
    }

    static void exercise4() {
        System.out.println("-- Exercise 4: Star Triangle --");
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void exercise5() {
        System.out.println("-- Exercise 5: Primes up to 50 --");
        for (int candidate = 2; candidate <= 50; candidate++) {
            boolean isPrime = true;
            for (int divisor = 2; divisor <= Math.sqrt(candidate); divisor++) {
                if (candidate % divisor == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) System.out.print(candidate + " ");
        }
        System.out.println();
        System.out.println();
    }
}
