/**
 * Day 03 — Conditionals: if/else and switch
 * Run: java Lesson
 */
public class Lesson {

    public static void main(String[] args) {

        System.out.println("=== Day 03: Conditionals ===\n");

        // ── 1. BASIC IF ─────────────────────────────────────────────────────
        System.out.println("--- Basic if ---");
        int temperature = 35;
        System.out.println("Temperature: " + temperature + "°C");

        if (temperature > 30) {
            System.out.println("It's hot today!");
        }
        // If the condition is false, the block is simply skipped.
        System.out.println();

        // ── 2. IF-ELSE ──────────────────────────────────────────────────────
        System.out.println("--- if-else ---");
        int age = 17;
        System.out.println("Age: " + age);

        if (age >= 18) {
            System.out.println("You are an adult.");
        } else {
            System.out.println("You are a minor.");
        }
        System.out.println();

        // ── 3. IF-ELSE-IF CHAIN ─────────────────────────────────────────────
        System.out.println("--- if-else-if chain ---");
        int score = 78;
        System.out.println("Score: " + score);

        if (score >= 90) {
            System.out.println("Grade: A");
        } else if (score >= 80) {
            System.out.println("Grade: B");
        } else if (score >= 70) {
            System.out.println("Grade: C");
        } else if (score >= 60) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }
        System.out.println();

        // ── 4. NESTED IF ────────────────────────────────────────────────────
        System.out.println("--- Nested if ---");
        boolean hasTicket = true;
        boolean isVIP     = false;

        if (hasTicket) {
            System.out.println("Welcome! You may enter.");
            if (isVIP) {
                System.out.println("VIP lounge is to the left.");
            } else {
                System.out.println("Please take a general seat.");
            }
        } else {
            System.out.println("Sorry, no ticket — no entry.");
        }
        System.out.println();

        // ── 5. TERNARY OPERATOR ─────────────────────────────────────────────
        System.out.println("--- Ternary operator ---");
        // Syntax: condition ? valueIfTrue : valueIfFalse
        int a = 10, b = 20;
        int max = (a > b) ? a : b;
        System.out.println("max of " + a + " and " + b + " = " + max);

        String status = (age >= 18) ? "adult" : "minor";
        System.out.println("Age " + age + " → " + status);
        System.out.println();

        // ── 6. TRADITIONAL SWITCH STATEMENT ────────────────────────────────
        System.out.println("--- Traditional switch statement ---");
        int dayNum = 3;
        System.out.println("Day number: " + dayNum);

        switch (dayNum) {
            case 1:
                System.out.println("Monday");
                break;   // IMPORTANT: without break, execution falls through!
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
            case 7:                              // fall-through: both 6 and 7 hit this
                System.out.println("Weekend!");
                break;
            default:
                System.out.println("Invalid day number");
        }
        System.out.println();

        // ── 7. MODERN SWITCH EXPRESSION (Java 14+) ──────────────────────────
        System.out.println("--- Modern switch expression (Java 14+) ---");
        // Uses -> instead of colon+break; can be used as an expression (has a value)
        String dayName = switch (dayNum) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6, 7 -> "Weekend";     // comma-separated cases!
            default -> "Unknown";
        };
        System.out.println("Day " + dayNum + " = " + dayName);
        System.out.println();

        // Switch expression with a block (use yield to return a value)
        String month = "March";
        String season = switch (month) {
            case "December", "January", "February" -> "Winter";
            case "March", "April", "May"           -> "Spring";
            case "June", "July", "August"           -> "Summer";
            case "September", "October", "November" -> "Autumn";
            default -> {
                System.out.println("  (Unknown month — defaulting)");
                yield "Unknown";   // yield returns the value from a block
            }
        };
        System.out.println(month + " → " + season);
        System.out.println();

        // ── 8. LOGICAL CONDITIONS ───────────────────────────────────────────
        System.out.println("--- Compound conditions ---");
        int num = 42;
        if (num > 0 && num % 2 == 0) {
            System.out.println(num + " is a positive even number.");
        }

        char letter = 'a';
        if (letter == 'a' || letter == 'e' || letter == 'i'
                || letter == 'o' || letter == 'u') {
            System.out.println("'" + letter + "' is a vowel.");
        }
        System.out.println();

        System.out.println("=== End of Day 03 Lesson ===");
    }
}
