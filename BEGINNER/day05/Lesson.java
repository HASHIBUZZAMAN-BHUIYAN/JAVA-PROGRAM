/**
 * Day 05 — Methods
 * Topics: declarations, parameters, return values, overloading, scope
 */
public class Lesson {

    // ── Static methods belong to the class (no object needed) ──────────────

    // Simple void method — no return value
    static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }

    // Method that returns a value
    static int add(int a, int b) {
        return a + b;
    }

    // OVERLOADING — same name, different parameter types
    static double add(double a, double b) {
        return a + b;
    }

    // OVERLOADING — same name, different number of parameters
    static int add(int a, int b, int c) {
        return a + b + c;
    }

    static double circleArea(double radius) {
        return Math.PI * radius * radius;
    }

    // Method calling another method
    static String classify(int n) {
        if (isEven(n)) return n + " is even";
        return n + " is odd";
    }

    static boolean isEven(int n) {
        return n % 2 == 0;
    }

    // Demonstrates scope: variable declared inside method is local
    static void scopeDemo() {
        int localVar = 99; // only visible inside this method
        System.out.println("Inside scopeDemo, localVar = " + localVar);
    }

    // ── main method ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("=== Day 05: Methods ===\n");

        // Calling a void method
        greet("Alice");
        greet("Bob");
        System.out.println();

        // Calling a method with a return value
        int sum = add(3, 4);
        System.out.println("add(3, 4) = " + sum);

        // Java picks the right overload based on argument types
        double dsum = add(1.5, 2.5);
        System.out.println("add(1.5, 2.5) = " + dsum);

        int tripleSum = add(10, 20, 30);
        System.out.println("add(10, 20, 30) = " + tripleSum);
        System.out.println();

        // Using a return value inline
        System.out.printf("Area of circle r=5: %.2f%n", circleArea(5));
        System.out.println();

        // Method calling another method
        System.out.println(classify(7));
        System.out.println(classify(12));
        System.out.println();

        // Scope demo
        scopeDemo();
        // localVar doesn't exist here — would cause a compile error if we tried to use it

        System.out.println("\n--- Key Facts ---");
        System.out.println("void methods perform actions but return nothing.");
        System.out.println("Overloading = same name, different parameters.");
        System.out.println("Scope = variables live only where they were declared.");
        System.out.println("\n=== End of Day 05 Lesson ===");
    }
}
