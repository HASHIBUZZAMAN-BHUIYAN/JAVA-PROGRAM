/**
 * Day 02 — Operators, Expressions, Type Casting, final
 * Run: java Lesson
 */
public class Lesson {

    public static void main(String[] args) {

        System.out.println("=== Day 02: Operators & Casting ===\n");

        // ── 1. ARITHMETIC OPERATORS ─────────────────────────────────────────
        System.out.println("--- Arithmetic Operators ---");
        int a = 17, b = 5;
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + (a + b));   // 22
        System.out.println("a - b = " + (a - b));   // 12
        System.out.println("a * b = " + (a * b));   // 85
        System.out.println("a / b = " + (a / b));   // 3  ← integer division!
        System.out.println("a % b = " + (a % b));   // 2  ← remainder
        System.out.println();

        // Integer division TRUNCATES — does not round
        System.out.println("7 / 2 (int)    = " + (7 / 2));       // 3
        System.out.println("7.0 / 2 (double) = " + (7.0 / 2));   // 3.5
        System.out.println();

        // ── 2. INCREMENT & DECREMENT ────────────────────────────────────────
        System.out.println("--- Increment & Decrement ---");
        int x = 10;
        System.out.println("x = " + x);
        x++;   // x becomes 11
        System.out.println("After x++: x = " + x);
        x--;   // x becomes 10
        System.out.println("After x--: x = " + x);

        // Prefix (++x) increments BEFORE the expression is evaluated
        // Postfix (x++) increments AFTER the expression is evaluated
        int y = 5;
        System.out.println("y = " + y);
        System.out.println("Print ++y: " + (++y));  // prints 6, y is now 6
        System.out.println("Print y++: " + (y++));  // prints 6, y becomes 7
        System.out.println("y is now: " + y);        // 7
        System.out.println();

        // ── 3. COMPOUND ASSIGNMENT ──────────────────────────────────────────
        System.out.println("--- Compound Assignment ---");
        int n = 100;
        System.out.println("n = " + n);
        n += 10;  System.out.println("n += 10  → " + n); // 110
        n -= 5;   System.out.println("n -= 5   → " + n); // 105
        n *= 2;   System.out.println("n *= 2   → " + n); // 210
        n /= 3;   System.out.println("n /= 3   → " + n); // 70
        n %= 8;   System.out.println("n %= 8   → " + n); // 6
        System.out.println();

        // ── 4. COMPARISON OPERATORS ─────────────────────────────────────────
        System.out.println("--- Comparison Operators (return boolean) ---");
        int p = 10, q = 20;
        System.out.println("p=" + p + ", q=" + q);
        System.out.println("p == q : " + (p == q));  // false
        System.out.println("p != q : " + (p != q));  // true
        System.out.println("p <  q : " + (p <  q));  // true
        System.out.println("p >  q : " + (p >  q));  // false
        System.out.println("p <= q : " + (p <= q));  // true
        System.out.println("p >= q : " + (p >= q));  // false
        System.out.println();

        // ── 5. LOGICAL OPERATORS ────────────────────────────────────────────
        System.out.println("--- Logical Operators ---");
        boolean t = true, f = false;
        System.out.println("true && true  = " + (t && t)); // true
        System.out.println("true && false = " + (t && f)); // false
        System.out.println("true || false = " + (t || f)); // true
        System.out.println("false|| false = " + (f || f)); // false
        System.out.println("!true         = " + (!t));     // false
        System.out.println("!false        = " + (!f));     // true
        System.out.println();

        // ── 6. TYPE CASTING ─────────────────────────────────────────────────
        System.out.println("--- Type Casting ---");

        // Widening: smaller type → larger type (automatic, no data loss)
        int   myInt    = 42;
        double myDouble = myInt;    // int widens to double automatically
        System.out.println("int 42 widened to double: " + myDouble);

        // Narrowing: larger type → smaller type (MUST cast explicitly; data may be lost)
        double pi        = 3.99999;
        int    piTrunc   = (int) pi;  // decimal is truncated, NOT rounded
        System.out.println("double 3.99999 narrowed to int: " + piTrunc); // 3

        double price     = 19.99;
        int    dollars   = (int) price;
        System.out.println("$19.99 narrowed to int dollars: " + dollars);  // 19
        System.out.println();

        // Useful cast: force double division from int variables
        int numerator   = 7;
        int denominator = 2;
        double result   = (double) numerator / denominator; // cast before dividing
        System.out.println("7 / 2 as int:    " + (numerator / denominator));  // 3
        System.out.println("7 / 2 as double: " + result);                     // 3.5
        System.out.println();

        // ── 7. FINAL KEYWORD ────────────────────────────────────────────────
        System.out.println("--- final (constants) ---");
        final double PI          = 3.14159265358979;
        final int    MAX_SCORE   = 100;
        final String APP_NAME    = "LearnJava";

        System.out.println("PI        = " + PI);
        System.out.println("MAX_SCORE = " + MAX_SCORE);
        System.out.println("APP_NAME  = " + APP_NAME);

        // PI = 3.0;  // ← This would cause a COMPILE ERROR — final cannot be reassigned
        System.out.println("(final variables cannot be reassigned)");
        System.out.println();

        // ── 8. OPERATOR PRECEDENCE ──────────────────────────────────────────
        System.out.println("--- Operator Precedence ---");
        // Rule: * / % before + -  (just like math)
        int expr1 = 2 + 3 * 4;      // 14, not 20
        int expr2 = (2 + 3) * 4;    // 20 — parentheses override
        System.out.println("2 + 3 * 4   = " + expr1);
        System.out.println("(2 + 3) * 4 = " + expr2);
        System.out.println();

        System.out.println("=== End of Day 02 Lesson ===");
    }
}
