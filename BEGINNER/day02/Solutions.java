/**
 * Day 02 — Solutions
 * Run: java Solutions
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== Day 02 Solutions ===\n");
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        System.out.println("\n=== All solutions complete. ===");
    }

    static void exercise1() {
        System.out.println("-- Exercise 1: Arithmetic --");
        int a = 23, b = 7;
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));  // integer division → 3
        System.out.println("a % b = " + (a % b));  // remainder → 2
        System.out.println();
    }

    static void exercise2() {
        System.out.println("-- Exercise 2: Remainder --");
        int num = 144;
        if (num % 2 == 0) {
            System.out.println(num + " is even");
        } else {
            System.out.println(num + " is odd");
        }
        int big = 3847;
        System.out.println("Last digit of " + big + ": " + (big % 10));
        System.out.println();
    }

    static void exercise3() {
        System.out.println("-- Exercise 3: Type Casting --");
        double celsius  = 36.6;
        int    truncated = (int) celsius;
        System.out.println("celsius as double: " + celsius);
        System.out.println("celsius as int   : " + truncated);  // 36

        int items = 7, boxes = 2;
        double perBox = (double) items / boxes;   // cast before dividing
        System.out.println("Items per box: " + perBox);         // 3.5
        System.out.println();
    }

    static void exercise4() {
        System.out.println("-- Exercise 4: Constants --");
        final int    SPEED_OF_LIGHT = 299_792_458;
        final double GRAVITY        = 9.81;
        final String PLANET         = "Earth";
        System.out.println("Speed of light: " + SPEED_OF_LIGHT + " m/s");
        System.out.println("Gravity on " + PLANET + ": " + GRAVITY + " m/s^2");
        System.out.println();
    }

    static void exercise5() {
        System.out.println("-- Exercise 5: Compound Assignment --");
        int score = 50;
        System.out.println("Start : " + score);
        score += 30; System.out.println("+=30  : " + score);  // 80
        score *= 2;  System.out.println("*=2   : " + score);  // 160
        score -= 10; System.out.println("-=10  : " + score);  // 150
        score /= 3;  System.out.println("/=3   : " + score);  // 50
        score %= 7;  System.out.println("%=7   : " + score);  // 1
        System.out.println();
    }
}
