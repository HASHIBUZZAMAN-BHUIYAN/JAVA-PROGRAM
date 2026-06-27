/**
 * Day 05 Mini Project — Simple Calculator
 * Applies: method overloading, parameters, return values
 *
 * Run: java MiniProject
 */
import java.util.Scanner;

public class MiniProject {

    static double add(double a, double b)      { return a + b; }
    static double subtract(double a, double b) { return a - b; }
    static double multiply(double a, double b) { return a * b; }
    static double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }
    static double power(double base, int exp) {
        double result = 1;
        for (int i = 0; i < Math.abs(exp); i++) result *= base;
        return exp < 0 ? 1.0 / result : result;
    }

    static void printResult(double a, String op, double b, double result) {
        System.out.printf("  %.4g %s %.4g = %.6g%n", a, op, b, result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Simple Calculator ===");

        while (true) {
            System.out.print("\nEnter: num op num  (ops: + - * / ^)  or 'quit': ");
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("quit")) break;

            String[] parts = line.split("\\s+");
            if (parts.length != 3) {
                System.out.println("Format: number operator number (e.g. 5 + 3)");
                continue;
            }
            try {
                double a  = Double.parseDouble(parts[0]);
                String op = parts[1];
                double b  = Double.parseDouble(parts[2]);

                double result = switch (op) {
                    case "+" -> add(a, b);
                    case "-" -> subtract(a, b);
                    case "*" -> multiply(a, b);
                    case "/" -> divide(a, b);
                    case "^" -> power(a, (int) b);
                    default  -> throw new IllegalArgumentException("Unknown operator: " + op);
                };
                printResult(a, op, b, result);
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numbers.");
            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Goodbye!");
        sc.close();
    }
}
