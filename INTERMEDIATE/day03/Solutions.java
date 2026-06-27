/**
 * INTERMEDIATE Day 03 — Lambda Expressions — Solutions
 */
import java.util.*;
import java.util.function.*;

public class Solutions {

    static Predicate<String> longerThan(int n) {
        return s -> s.length() > n;
    }

    static Function<String, Integer> stringLength() {
        return String::length;
    }

    static Consumer<String> upperPrinter() {
        return s -> System.out.println(s.toUpperCase());
    }

    static Supplier<List<String>> listSupplier() {
        return ArrayList::new;
    }

    static Function<String, String> trimAndUpper() {
        return ((Function<String,String>) String::trim).andThen(String::toUpperCase);
    }

    public static void main(String[] args) {
        System.out.println(longerThan(5).test("Hello"));
        System.out.println(longerThan(5).test("Hello!"));
        System.out.println(stringLength().apply("Java"));
        upperPrinter().accept("hello world");
        List<String> list = listSupplier().get(); list.add("test"); System.out.println(list);
        System.out.println(trimAndUpper().apply("  hello  "));
    }
}
