/**
 * Day 02 — Mini Project: Simple Calculator
 *
 * Applies: arithmetic operators, type casting, final, expressions.
 *
 * This calculator performs common operations on two fixed numbers and also
 * demonstrates unit conversions using final constants.
 *
 * Run: java MiniProject
 */
public class MiniProject {

    // Constants — declared at class level so all methods can use them
    static final double CM_PER_INCH = 2.54;
    static final double KG_PER_LB   = 0.453592;
    static final double KM_PER_MILE = 1.60934;
    static final double F_TO_C_OFFSET = 32.0;

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║       SIMPLE JAVA CALCULATOR     ║");
        System.out.println("╚══════════════════════════════════╝\n");

        // ── Part 1: Basic Arithmetic ────────────────────────────────────────
        double num1 = 84.0;
        double num2 = 13.0;

        System.out.println("Numbers: " + num1 + " and " + num2);
        System.out.println("─".repeat(36));
        System.out.printf("%-20s = %.4f%n", "Addition",       num1 + num2);
        System.out.printf("%-20s = %.4f%n", "Subtraction",    num1 - num2);
        System.out.printf("%-20s = %.4f%n", "Multiplication", num1 * num2);
        System.out.printf("%-20s = %.4f%n", "Division",       num1 / num2);
        System.out.printf("%-20s = %.4f%n", "Remainder",      num1 % num2);

        // Power using Math.pow (returns double)
        System.out.printf("%-20s = %.4f%n", num1 + " ^ 2", Math.pow(num1, 2));
        System.out.printf("%-20s = %.4f%n", "sqrt(" + num1 + ")", Math.sqrt(num1));
        System.out.println();

        // ── Part 2: Integer vs Double Division ──────────────────────────────
        int dividend = 100;
        int divisor  = 7;
        System.out.println("Integer division: " + dividend + " / " + divisor);
        System.out.println("  As int    : " + (dividend / divisor));           // 14
        System.out.println("  As double : " + ((double) dividend / divisor));   // 14.2857...
        System.out.println("  Remainder : " + (dividend % divisor));            // 2
        System.out.println();

        // ── Part 3: Unit Converter ──────────────────────────────────────────
        System.out.println("═".repeat(36));
        System.out.println("      UNIT CONVERSIONS");
        System.out.println("═".repeat(36));

        // Length
        double inches = 70.0;
        double cm     = inches * CM_PER_INCH;
        System.out.printf("%.1f inches = %.2f cm%n", inches, cm);

        double miles = 26.2;   // marathon
        double km    = miles * KM_PER_MILE;
        System.out.printf("%.1f miles  = %.2f km%n", miles, km);

        // Weight
        double pounds = 150.0;
        double kg     = pounds * KG_PER_LB;
        System.out.printf("%.1f lbs    = %.2f kg%n", pounds, kg);

        // Temperature: F → C  formula: C = (F - 32) * 5/9
        double fahrenheit = 98.6;
        double celsius    = (fahrenheit - F_TO_C_OFFSET) * 5.0 / 9.0;
        System.out.printf("%.1f °F     = %.2f °C%n", fahrenheit, celsius);
        System.out.println();

        // ── Part 4: Compound Interest Calculator ───────────────────────────
        System.out.println("═".repeat(36));
        System.out.println("   COMPOUND INTEREST CALCULATOR");
        System.out.println("═".repeat(36));

        final double PRINCIPAL   = 1000.0;   // starting amount
        final double RATE        = 0.05;     // 5% annual interest
        final int    YEARS       = 10;

        // Formula: A = P * (1 + r)^n
        double amount = PRINCIPAL * Math.pow(1 + RATE, YEARS);
        double earned = amount - PRINCIPAL;

        System.out.printf("Principal     : $%.2f%n", PRINCIPAL);
        System.out.printf("Annual Rate   : %.0f%%%n", RATE * 100);
        System.out.printf("Years         : %d%n",    YEARS);
        System.out.printf("Final Amount  : $%.2f%n", amount);
        System.out.printf("Interest Earned: $%.2f%n", earned);
        System.out.println();

        System.out.println("=== Calculator done ===");
    }
}
