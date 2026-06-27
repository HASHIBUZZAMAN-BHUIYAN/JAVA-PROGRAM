/**
 * INTERMEDIATE Day 07 — Unit Testing with JUnit 5
 *
 * This file shows JUnit 5 concepts in plain Java comments + a minimal
 * self-contained test runner to demonstrate the ideas.
 * The real JUnit 5 tests live in maven-test-project/
 *
 * Run: java Lesson
 */

// A class we want to test
class StringUtils {
    static String reverse(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        return new StringBuilder(s).reverse().toString();
    }

    static boolean isPalindrome(String s) {
        if (s == null) return false;
        String clean = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        return clean.equals(reverse(clean));
    }

    static int wordCount(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) return 0;
        return sentence.trim().split("\\s+").length;
    }
}

// Minimal self-contained test runner (no JUnit dependency needed)
class SimpleTestRunner {
    static int passed = 0, failed = 0;

    static void assertEquals(Object expected, Object actual, String message) {
        if (expected.equals(actual)) {
            System.out.println("  ✓ " + message);
            passed++;
        } else {
            System.out.println("  ✗ " + message);
            System.out.println("    Expected: " + expected + "  Got: " + actual);
            failed++;
        }
    }

    static void assertTrue(boolean condition, String message) {
        assertEquals(true, condition, message);
    }

    static void assertThrows(Class<? extends Exception> type, Runnable code, String message) {
        try {
            code.run();
            System.out.println("  ✗ " + message + " (no exception thrown)");
            failed++;
        } catch (Exception e) {
            if (type.isInstance(e)) {
                System.out.println("  ✓ " + message);
                passed++;
            } else {
                System.out.println("  ✗ " + message + " (wrong exception: " + e.getClass().getSimpleName() + ")");
                failed++;
            }
        }
    }

    static void printSummary() {
        System.out.printf("%n  Results: %d passed, %d failed%n", passed, failed);
    }
}

public class Lesson {

    public static void main(String[] args) {
        System.out.println("=== INTERMEDIATE Day 07: Unit Testing ===\n");
        System.out.println("--- StringUtils Tests ---");

        // Naming convention: testMethodName_scenario_expectedResult
        SimpleTestRunner.assertEquals("avaJ", StringUtils.reverse("Java"),
            "reverse('Java') → 'avaJ'");

        SimpleTestRunner.assertEquals("", StringUtils.reverse(""),
            "reverse('') → ''");

        SimpleTestRunner.assertTrue(StringUtils.isPalindrome("racecar"),
            "isPalindrome('racecar') → true");

        SimpleTestRunner.assertTrue(StringUtils.isPalindrome("A man a plan a canal Panama"),
            "isPalindrome('A man...') → true (ignoring case/spaces)");

        SimpleTestRunner.assertTrue(!StringUtils.isPalindrome("hello"),
            "isPalindrome('hello') → false");

        SimpleTestRunner.assertEquals(3, StringUtils.wordCount("hello world again"),
            "wordCount('hello world again') → 3");

        SimpleTestRunner.assertEquals(0, StringUtils.wordCount(""),
            "wordCount('') → 0");

        SimpleTestRunner.assertThrows(IllegalArgumentException.class,
            () -> StringUtils.reverse(null),
            "reverse(null) → throws IllegalArgumentException");

        SimpleTestRunner.printSummary();

        System.out.println("\n--- JUnit 5 Key Annotations (used in maven-test-project/) ---");
        System.out.println("""
            @Test          — marks a test method
            @BeforeEach    — runs before EACH test method (setup)
            @AfterEach     — runs after EACH test method (teardown)
            @BeforeAll     — runs once before all tests in the class
            @DisplayName   — human-readable test name

            Key assertions (import static org.junit.jupiter.api.Assertions.*):
              assertEquals(expected, actual)
              assertNotEquals(unexpected, actual)
              assertTrue(condition)
              assertFalse(condition)
              assertNull(value)
              assertNotNull(value)
              assertThrows(ExceptionClass.class, () -> code)
              assertAll(  // all assertions checked even if one fails
                () -> assertEquals(1, 1),
                () -> assertTrue(true)
              )
            """);

        System.out.println("See maven-test-project/ for real JUnit 5 tests.");
        System.out.println("Run: cd maven-test-project && mvn test");
        System.out.println("\n=== End of Day 07 Lesson ===");
    }
}
