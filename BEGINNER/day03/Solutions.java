/**
 * Day 03 — Solutions
 * Run: java Solutions
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== Day 03 Solutions ===\n");
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        System.out.println("\n=== All solutions complete. ===");
    }

    static void exercise1() {
        System.out.println("-- Exercise 1: Leap Year --");
        int year = 2024;
        boolean isLeap = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
        if (isLeap) {
            System.out.println(year + " is a leap year.");
        } else {
            System.out.println(year + " is not a leap year.");
        }
        System.out.println();
    }

    static void exercise2() {
        System.out.println("-- Exercise 2: Triangle Classifier --");
        int a = 5, b = 5, c = 8;
        // Validity check
        boolean valid = (a + b > c) && (a + c > b) && (b + c > a);
        System.out.println("Triangle (" + a + ", " + b + ", " + c + ")");
        if (!valid) {
            System.out.println("  Not a valid triangle.");
        } else {
            System.out.println("  Valid triangle.");
            if (a == b && b == c) {
                System.out.println("  Type: Equilateral");
            } else if (a == b || b == c || a == c) {
                System.out.println("  Type: Isosceles");
            } else {
                System.out.println("  Type: Scalene");
            }
        }
        System.out.println();
    }

    static void exercise3() {
        System.out.println("-- Exercise 3: Ternary --");
        int n = -7;
        int x = 14;
        int p = 33, q = 27;
        System.out.println(n + " is " + (n > 0 ? "positive" : "non-positive"));
        System.out.println(x + " is " + (x % 2 == 0 ? "even" : "odd"));
        System.out.println("Larger of " + p + " and " + q + " = " + (p > q ? p : q));
        System.out.println();
    }

    static void exercise4() {
        System.out.println("-- Exercise 4: Month Details --");
        String month = "July";

        int numberOfDays = switch (month) {
            case "January", "March", "May", "July", "August", "October", "December" -> 31;
            case "April", "June", "September", "November"                            -> 30;
            case "February"                                                           -> 28;
            default -> -1;
        };

        String quarter = switch (month) {
            case "January", "February", "March"    -> "Q1";
            case "April", "May", "June"            -> "Q2";
            case "July", "August", "September"     -> "Q3";
            case "October", "November", "December" -> "Q4";
            default -> "Unknown";
        };

        System.out.println(month + " has " + numberOfDays + " days.");
        System.out.println(month + " is in " + quarter + ".");
        System.out.println();
    }

    static void exercise5() {
        System.out.println("-- Exercise 5: BMI Category --");
        double bmi = 24.3;
        String category;
        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi < 25.0) {
            category = "Normal weight";
        } else if (bmi < 30.0) {
            category = "Overweight";
        } else {
            category = "Obese";
        }
        System.out.println("BMI " + bmi + " → " + category);
        System.out.println();
    }
}
