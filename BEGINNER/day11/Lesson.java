/**
 * Day 11 — Exception Handling
 * Topics: try/catch/finally, custom exceptions, checked vs unchecked
 */

// Custom unchecked exception
class InsufficientFundsException extends RuntimeException {
    private double shortfall;

    InsufficientFundsException(double shortfall) {
        super(String.format("Insufficient funds — need $%.2f more", shortfall));
        this.shortfall = shortfall;
    }

    double getShortfall() { return shortfall; }
}

// Custom checked exception (extends Exception, not RuntimeException)
class InvalidAgeException extends Exception {
    InvalidAgeException(int age) {
        super("Invalid age: " + age + ". Must be 0-150.");
    }
}

class Person {
    private String name;
    private int    age;

    // Method that throws a checked exception — caller MUST handle or declare throws
    Person(String name, int age) throws InvalidAgeException {
        if (age < 0 || age > 150) throw new InvalidAgeException(age);
        this.name = name;
        this.age  = age;
    }

    @Override public String toString() { return name + " (age " + age + ")"; }
}

// Simulated resource (implements AutoCloseable for try-with-resources)
class DatabaseConnection implements AutoCloseable {
    private String url;

    DatabaseConnection(String url) {
        this.url = url;
        System.out.println("  [DB] Opened connection to " + url);
    }

    String query(String sql) {
        if (sql == null) throw new IllegalArgumentException("SQL cannot be null");
        System.out.println("  [DB] Executed: " + sql);
        return "result-data";
    }

    @Override
    public void close() {
        System.out.println("  [DB] Connection to " + url + " closed");
    }
}

public class Lesson {

    public static void main(String[] args) {
        System.out.println("=== Day 11: Exception Handling ===\n");

        // ── 1. BASIC TRY/CATCH ───────────────────────────────────────────────
        System.out.println("--- 1. Basic try/catch ---");
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]); // throws ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e.getMessage());
        }
        System.out.println("Execution continues after catch\n");

        // ── 2. FINALLY BLOCK ─────────────────────────────────────────────────
        System.out.println("--- 2. finally ---");
        try {
            System.out.println("Trying...");
            int result = 10 / 0; // ArithmeticException
            System.out.println("This won't print");
        } catch (ArithmeticException e) {
            System.out.println("Caught division by zero: " + e.getMessage());
        } finally {
            System.out.println("finally always runs (cleanup here)\n");
        }

        // ── 3. MULTI-CATCH ───────────────────────────────────────────────────
        System.out.println("--- 3. Multi-catch ---");
        String[] inputs = {"42", "abc", null};
        for (String s : inputs) {
            try {
                int val = Integer.parseInt(s);
                System.out.println("Parsed: " + val);
            } catch (NumberFormatException e) {
                System.out.println("Bad number format: " + s);
            } catch (NullPointerException e) {
                System.out.println("Got a null input!");
            }
        }
        System.out.println();

        // ── 4. CUSTOM UNCHECKED EXCEPTION ────────────────────────────────────
        System.out.println("--- 4. Custom exception ---");
        try {
            double balance = 100.0;
            double withdrawal = 150.0;
            if (withdrawal > balance)
                throw new InsufficientFundsException(withdrawal - balance);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.printf("Shortfall: $%.2f%n%n", e.getShortfall());
        }

        // ── 5. CHECKED EXCEPTION ─────────────────────────────────────────────
        System.out.println("--- 5. Checked exception (InvalidAgeException) ---");
        try {
            Person valid   = new Person("Alice", 30);
            Person invalid = new Person("Bob", -5);
            System.out.println(valid);
            System.out.println(invalid); // won't reach here
        } catch (InvalidAgeException e) {
            System.out.println("Caught checked exception: " + e.getMessage());
        }
        System.out.println();

        // ── 6. TRY-WITH-RESOURCES ────────────────────────────────────────────
        System.out.println("--- 6. try-with-resources ---");
        try (DatabaseConnection conn = new DatabaseConnection("jdbc:h2:mem:test")) {
            String result = conn.query("SELECT * FROM users");
            System.out.println("  Result: " + result);
        } // conn.close() is called AUTOMATICALLY here
        System.out.println("(Resource was closed automatically)\n");

        System.out.println("=== End of Day 11 Lesson ===");
    }
}
