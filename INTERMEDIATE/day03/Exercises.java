/**
 * INTERMEDIATE Day 03 — Lambda Expressions — Exercises
 */
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Exercises {

    // Exercise 1: Use a Predicate<String> to test if a string length > n
    static Predicate<String> longerThan(int n) {
        // TODO: return a Predicate that returns true if s.length() > n
        return s -> false;
    }

    // Exercise 2: Use Function<String,Integer> to get string length
    static Function<String, Integer> stringLength() {
        // TODO: return String::length as a Function
        return s -> 0;
    }

    // Exercise 3: Use Consumer<String> to print a string in UPPER CASE
    static Consumer<String> upperPrinter() {
        // TODO: return a Consumer that prints s.toUpperCase()
        return s -> {};
    }

    // Exercise 4: Use Supplier<List<String>> to supply a new empty ArrayList
    static Supplier<List<String>> listSupplier() {
        // TODO: return ArrayList::new
        return () -> null;
    }

    // Exercise 5: Chain two Functions: first trim(), then toUpperCase()
    static Function<String, String> trimAndUpper() {
        // TODO: use andThen() to chain String::trim and String::toUpperCase
        return s -> s;
    }

    public static void main(String[] args) {
        Predicate<String> longer5 = longerThan(5);
        System.out.println(longer5.test("Hello"));    // false (5 is not > 5)
        System.out.println(longer5.test("Hello!"));   // true

        Function<String, Integer> len = stringLength();
        System.out.println(len.apply("Java"));         // 4

        Consumer<String> printer = upperPrinter();
        printer.accept("hello world");                 // HELLO WORLD

        Supplier<List<String>> supplier = listSupplier();
        List<String> list = supplier.get();
        list.add("test");
        System.out.println(list);                      // [test]

        Function<String,String> f = trimAndUpper();
        System.out.println(f.apply("  hello  "));     // HELLO
    }
}
