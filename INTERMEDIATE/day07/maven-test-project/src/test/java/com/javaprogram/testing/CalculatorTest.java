package com.javaprogram.testing;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for Calculator.
 *
 * Run: mvn test (from the maven-test-project/ directory)
 */
class CalculatorTest {

    private Calculator calc;

    @BeforeEach  // runs before EACH test method
    void setUp() {
        calc = new Calculator();
        System.out.println("  [setUp] New Calculator created");
    }

    @AfterEach  // runs after EACH test method
    void tearDown() {
        System.out.println("  [tearDown] Test complete\n");
    }

    // ── Addition tests ────────────────────────────────────────────────────────
    @Test
    @DisplayName("add: positive numbers")
    void testAddPositives() {
        assertEquals(5, calc.add(2, 3), "2 + 3 should equal 5");
    }

    @Test
    @DisplayName("add: negative numbers")
    void testAddNegatives() {
        assertEquals(-5, calc.add(-2, -3));
    }

    @Test
    @DisplayName("add: zero identity")
    void testAddZero() {
        assertEquals(7, calc.add(7, 0));
    }

    // ── Subtraction ───────────────────────────────────────────────────────────
    @Test
    void testSubtract() {
        assertEquals(1, calc.subtract(3, 2));
        assertEquals(-1, calc.subtract(2, 3));
    }

    // ── Multiply ──────────────────────────────────────────────────────────────
    @Test
    void testMultiply() {
        assertEquals(12, calc.multiply(3, 4));
        assertEquals(0, calc.multiply(5, 0));
    }

    // ── Division ──────────────────────────────────────────────────────────────
    @Test
    void testDivide() {
        assertEquals(2.5, calc.divide(5, 2), 0.001);
    }

    @Test
    @DisplayName("divide: by zero throws ArithmeticException")
    void testDivideByZeroThrows() {
        // assertThrows verifies an exception IS thrown
        assertThrows(ArithmeticException.class,
            () -> calc.divide(10, 0),
            "Dividing by zero should throw ArithmeticException");
    }

    // ── isEven ────────────────────────────────────────────────────────────────
    @Test
    void testIsEven() {
        assertTrue(calc.isEven(4));
        assertTrue(calc.isEven(0));
        assertFalse(calc.isEven(7));
        assertFalse(calc.isEven(-3));
    }

    // ── isPrime ───────────────────────────────────────────────────────────────
    @Test
    void testIsPrime() {
        // assertAll: checks ALL assertions even if one fails
        assertAll("prime checks",
            () -> assertTrue(calc.isPrime(2),   "2 is prime"),
            () -> assertTrue(calc.isPrime(7),   "7 is prime"),
            () -> assertTrue(calc.isPrime(13),  "13 is prime"),
            () -> assertFalse(calc.isPrime(1),  "1 is not prime"),
            () -> assertFalse(calc.isPrime(4),  "4 is not prime"),
            () -> assertFalse(calc.isPrime(0),  "0 is not prime")
        );
    }

    // ── factorial ─────────────────────────────────────────────────────────────
    @Test
    void testFactorial() {
        assertEquals(1,   calc.factorial(0));
        assertEquals(1,   calc.factorial(1));
        assertEquals(120, calc.factorial(5));
        assertEquals(720, calc.factorial(6));
    }

    @Test
    void testFactorialNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
    }
}
