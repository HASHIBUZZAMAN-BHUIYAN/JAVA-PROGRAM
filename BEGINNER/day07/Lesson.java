/**
 * Day 07 — Strings
 * Topics: String methods, immutability, StringBuilder, comparison
 */
public class Lesson {

    public static void main(String[] args) {
        System.out.println("=== Day 07: Strings ===\n");

        String s = "Hello, Java World!";

        // ── 1. BASIC PROPERTIES ──────────────────────────────────────────────
        System.out.println("String: \"" + s + "\"");
        System.out.println("length(): " + s.length());
        System.out.println("charAt(0): " + s.charAt(0));
        System.out.println("charAt(7): " + s.charAt(7));
        System.out.println();

        // ── 2. SEARCHING ─────────────────────────────────────────────────────
        System.out.println("indexOf('J'): " + s.indexOf('J'));
        System.out.println("indexOf(\"World\"): " + s.indexOf("World"));
        System.out.println("contains(\"Java\"): " + s.contains("Java"));
        System.out.println("startsWith(\"Hello\"): " + s.startsWith("Hello"));
        System.out.println("endsWith(\"!\"): " + s.endsWith("!"));
        System.out.println();

        // ── 3. EXTRACTING SUBSTRINGS ─────────────────────────────────────────
        System.out.println("substring(7): " + s.substring(7));       // from index 7
        System.out.println("substring(7,11): " + s.substring(7, 11)); // 7 inclusive, 11 exclusive
        System.out.println();

        // ── 4. TRANSFORMATIONS (each returns a NEW String) ───────────────────
        System.out.println("toUpperCase(): " + s.toUpperCase());
        System.out.println("toLowerCase(): " + s.toLowerCase());
        System.out.println("replace('l','L'): " + s.replace('l', 'L'));
        System.out.println("replace(\"Java\",\"Python\"): " + s.replace("Java", "Python"));

        String padded = "   trim me   ";
        System.out.println("trim(): \"" + padded.trim() + "\"");
        System.out.println();

        // ── 5. SPLIT ─────────────────────────────────────────────────────────
        String csv = "apple,banana,cherry,date";
        String[] fruits = csv.split(",");
        System.out.print("split on ',': ");
        for (String f : fruits) System.out.print("[" + f + "] ");
        System.out.println("\n");

        // ── 6. IMMUTABILITY ──────────────────────────────────────────────────
        String original = "Java";
        String upper = original.toUpperCase(); // does NOT change original
        System.out.println("original after toUpperCase(): " + original); // still "Java"
        System.out.println("upper: " + upper);
        System.out.println();

        // ── 7. COMPARISON ────────────────────────────────────────────────────
        String a = new String("hello");
        String b = new String("hello");
        System.out.println("a == b: " + (a == b));           // false — different objects
        System.out.println("a.equals(b): " + a.equals(b));   // true  — same content
        System.out.println("equalsIgnoreCase: " + "JAVA".equalsIgnoreCase("java"));
        System.out.println();

        // ── 8. STRINGBUILDER — mutable, efficient for concatenation ──────────
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append("item").append(i);
            if (i < 5) sb.append(", ");
        }
        System.out.println("StringBuilder result: " + sb.toString());
        sb.insert(0, "[").append("]");  // insert at start, append at end
        System.out.println("After insert/append: " + sb);
        sb.reverse();
        System.out.println("Reversed: " + sb);
        System.out.println();

        // ── 9. STRING.FORMAT ─────────────────────────────────────────────────
        String formatted = String.format("Name: %-10s Age: %3d Score: %.2f", "Alice", 25, 98.6);
        System.out.println(formatted);

        System.out.println("\n=== End of Day 07 Lesson ===");
    }
}
