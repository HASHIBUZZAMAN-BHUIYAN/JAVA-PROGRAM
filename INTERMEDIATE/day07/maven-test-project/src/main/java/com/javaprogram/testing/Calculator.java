package com.javaprogram.testing;

/**
 * Simple calculator class to demonstrate JUnit 5 testing.
 * This is the production code — tests live in src/test/
 */
public class Calculator {

    public int add(int a, int b)      { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public int multiply(int a, int b) { return a * b; }

    public double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }

    public boolean isEven(int n)  { return n % 2 == 0; }
    public boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n == 0) return 1;
        int result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }
}
