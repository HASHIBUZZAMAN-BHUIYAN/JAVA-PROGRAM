/**
 * Day 01 — Java Basics
 * Topics: public static void main, variables, primitive types, System.out.println
 *
 * Run: java Lesson
 */
public class Lesson {

    public static void main(String[] args) {

        // ── 1. YOUR FIRST PRINT STATEMENT ──────────────────────────────────
        System.out.println("=== Day 01: Java Basics ===");
        System.out.println(); // prints a blank line

        // ── 2. INTEGER TYPE (int) ───────────────────────────────────────────
        // int holds whole numbers from -2,147,483,648 to 2,147,483,647
        int age = 25;
        int year = 2024;
        int score = -10;

        System.out.println("--- int (integer) ---");
        System.out.println("age  = " + age);   // + joins (concatenates) values
        System.out.println("year = " + year);
        System.out.println("score = " + score);
        System.out.println();

        // ── 3. DOUBLE TYPE (double) ─────────────────────────────────────────
        // double holds decimal numbers (64-bit floating point)
        double price = 19.99;
        double pi    = 3.14159;
        double gpa   = 3.8;

        System.out.println("--- double (decimal) ---");
        System.out.println("price = " + price);
        System.out.println("pi    = " + pi);
        System.out.println("gpa   = " + gpa);
        System.out.println();

        // ── 4. BOOLEAN TYPE (boolean) ───────────────────────────────────────
        // boolean is either true or false — nothing else
        boolean isJavaFun   = true;
        boolean isRaining   = false;
        boolean hasLicense  = true;

        System.out.println("--- boolean ---");
        System.out.println("isJavaFun  = " + isJavaFun);
        System.out.println("isRaining  = " + isRaining);
        System.out.println("hasLicense = " + hasLicense);
        System.out.println();

        // ── 5. CHAR TYPE (char) ─────────────────────────────────────────────
        // char holds a SINGLE character — always use single quotes ''
        char grade      = 'A';
        char initial    = 'J';
        char symbol     = '@';

        System.out.println("--- char (single character) ---");
        System.out.println("grade   = " + grade);
        System.out.println("initial = " + initial);
        System.out.println("symbol  = " + symbol);
        System.out.println();

        // ── 6. STRING (not a primitive — it's a class) ─────────────────────
        // String holds text of any length — use double quotes ""
        String name     = "Alice";
        String language = "Java";
        String greeting = "Hello, " + name + "!"; // strings can be joined with +

        System.out.println("--- String ---");
        System.out.println("name     = " + name);
        System.out.println("language = " + language);
        System.out.println("greeting = " + greeting);
        System.out.println();

        // ── 7. COMBINING TYPES IN OUTPUT ───────────────────────────────────
        System.out.println("--- Combining values in output ---");
        System.out.println(name + " is " + age + " years old.");
        System.out.println("GPA: " + gpa + " | Grade: " + grade);
        System.out.println("Java is fun? " + isJavaFun);
        System.out.println();

        // ── 8. System.out.print vs System.out.println ──────────────────────
        // println → prints then goes to next line
        // print   → prints WITHOUT going to next line
        System.out.println("--- print vs println ---");
        System.out.print("Hello ");  // cursor stays on same line
        System.out.print("World");
        System.out.println("!");     // now we move to the next line
        System.out.println();

        // ── 9. DEFAULT VALUES (what variables hold before assignment) ───────
        // Local variables MUST be assigned before use.
        // Class/instance fields get defaults: int=0, double=0.0, boolean=false, char='\0'
        System.out.println("--- Key facts ---");
        System.out.println("int range: -2,147,483,648 to 2,147,483,647");
        System.out.println("double has ~15 decimal digits of precision");
        System.out.println("boolean: only true or false");
        System.out.println("char: single character in single quotes");
        System.out.println();

        System.out.println("=== End of Day 01 Lesson ===");
    }
}
