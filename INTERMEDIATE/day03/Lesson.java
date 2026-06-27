import java.util.*;
import java.util.function.*;

/**
 * Day 03 Lesson: Lambda Expressions & Functional Interfaces
 *
 * A lambda is an anonymous function — a concise way to implement
 * a @FunctionalInterface (an interface with exactly one abstract method).
 *
 * Run: java Lesson
 */
public class Lesson {

    // =========================================================
    // SECTION 1: Lambda syntax evolution
    // =========================================================
    @FunctionalInterface
    interface Greeter {
        String greet(String name);
    }

    static void lambdaSyntax() {
        System.out.println("--- Lambda Syntax ---");

        // 1. Anonymous class (old way)
        Greeter g1 = new Greeter() {
            @Override
            public String greet(String name) {
                return "Hello, " + name + "!";
            }
        };

        // 2. Lambda (compact)
        Greeter g2 = name -> "Hello, " + name + "!";

        // 3. Lambda with block body (multiple statements)
        Greeter g3 = name -> {
            String upper = name.toUpperCase();
            return "HELLO, " + upper + "!";
        };

        // 4. Method reference (when lambda just calls one method)
        // Greeter g4 = System.out::println;  // doesn't fit — void vs String
        // But this works:
        Consumer<String> printer = System.out::println;
        printer.accept("Printed via method reference");

        System.out.println(g1.greet("Alice"));
        System.out.println(g2.greet("Bob"));
        System.out.println(g3.greet("Charlie"));
    }

    // =========================================================
    // SECTION 2: Function<T,R> — transform a value
    // =========================================================
    static void functionDemo() {
        System.out.println("\n--- Function<T,R> ---");

        Function<String, Integer> strLen = s -> s.length();
        Function<Integer, String> intToStr = i -> "Number:" + i;
        Function<String, Boolean> isEmail = s -> s.contains("@");

        System.out.println("strLen(\"hello\")   = " + strLen.apply("hello"));        // 5
        System.out.println("intToStr(42)       = " + intToStr.apply(42));            // Number:42
        System.out.println("isEmail(\"a@b.com\") = " + isEmail.apply("a@b.com"));   // true

        // Function composition: andThen (f then g) and compose (g then f)
        Function<String, String> trim = String::trim;
        Function<String, String> upper = String::toUpperCase;

        // andThen: apply trim first, then upper
        Function<String, String> trimThenUpper = trim.andThen(upper);
        System.out.println("trimThenUpper(\"  hello  \") = " + trimThenUpper.apply("  hello  "));

        // compose: apply upper first, then trim (same result here, different order)
        Function<String, String> upperThenTrim = trim.compose(upper);
        System.out.println("upperThenTrim(\"  hello  \") = " + upperThenTrim.apply("  hello  "));

        // Chain: string -> length -> doubled -> to string
        Function<String, String> pipeline = strLen
            .andThen(len -> len * 2)
            .andThen(Object::toString);
        System.out.println("pipeline(\"hello\") = " + pipeline.apply("hello")); // "10"
    }

    // =========================================================
    // SECTION 3: Predicate<T> — test a condition
    // =========================================================
    static void predicateDemo() {
        System.out.println("\n--- Predicate<T> ---");

        Predicate<String> isEmpty   = String::isEmpty;
        Predicate<String> isLong    = s -> s.length() > 5;
        Predicate<Integer> isEven   = n -> n % 2 == 0;
        Predicate<Integer> isPos    = n -> n > 0;

        System.out.println("isEmpty(\"\")    = " + isEmpty.test(""));           // true
        System.out.println("isLong(\"hi\")  = " + isLong.test("hi"));          // false
        System.out.println("isLong(\"hello world\") = " + isLong.test("hello world")); // true

        // Combining predicates
        Predicate<Integer> isEvenAndPos = isEven.and(isPos);
        Predicate<Integer> isEvenOrPos  = isEven.or(isPos);
        Predicate<Integer> isOdd        = isEven.negate();

        System.out.println("isEvenAndPos(4)  = " + isEvenAndPos.test(4));   // true
        System.out.println("isEvenAndPos(-4) = " + isEvenAndPos.test(-4));  // false
        System.out.println("isEvenOrPos(-3)  = " + isEvenOrPos.test(-3));   // false
        System.out.println("isOdd(7)         = " + isOdd.test(7));          // true

        // Filter a list manually (Streams do this in day 04)
        List<Integer> nums = List.of(-3, -2, -1, 0, 1, 2, 3, 4, 5);
        System.out.print("Even and positive: ");
        for (Integer n : nums) {
            if (isEvenAndPos.test(n)) System.out.print(n + " ");
        }
        System.out.println();
    }

    // =========================================================
    // SECTION 4: Consumer<T> — side-effect action
    // =========================================================
    static void consumerDemo() {
        System.out.println("\n--- Consumer<T> ---");

        Consumer<String> print     = System.out::println;
        Consumer<String> printUpper = s -> System.out.println(s.toUpperCase());

        print.accept("Hello Consumer");
        printUpper.accept("hello consumer");

        // andThen chains two Consumers
        Consumer<String> printThenUppercase = print.andThen(printUpper);
        printThenUppercase.accept("chained");  // prints twice

        // BiConsumer: two inputs, no output
        BiConsumer<String, Integer> repeatPrint =
            (s, n) -> { for (int i = 0; i < n; i++) System.out.print(s + " "); System.out.println(); };
        repeatPrint.accept("Java", 3);  // Java Java Java

        // Practical use: forEach with Consumer
        List<String> names = List.of("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("  Name: " + name));
    }

    // =========================================================
    // SECTION 5: Supplier<T> — produce a value lazily
    // =========================================================
    static void supplierDemo() {
        System.out.println("\n--- Supplier<T> ---");

        Supplier<String> greeting  = () -> "Hello World";
        Supplier<Double>  random   = Math::random;
        Supplier<List<String>> newList = ArrayList::new;  // constructor reference

        System.out.println(greeting.get());    // Hello World
        System.out.println(random.get());      // random double
        System.out.println(newList.get());     // []

        // "Lazy" evaluation — the value is only computed when get() is called
        Supplier<String> expensive = () -> {
            // Imagine this is a slow database call
            return "expensive result at " + System.currentTimeMillis();
        };
        // Nothing runs until here:
        System.out.println(expensive.get());
    }

    // =========================================================
    // SECTION 6: Method References — 4 kinds
    // =========================================================
    static void methodReferenceDemo() {
        System.out.println("\n--- Method References ---");

        // 1. Static method reference: ClassName::staticMethod
        Function<String, Integer> parseInt = Integer::parseInt;
        System.out.println("parseInt(\"42\") = " + parseInt.apply("42"));

        // 2. Instance method of a particular instance: instance::method
        String prefix = "PREFIX_";
        Function<String, String> addPrefix = prefix::concat;
        System.out.println("addPrefix(\"hello\") = " + addPrefix.apply("hello"));

        // 3. Instance method of an arbitrary instance of a type: Type::instanceMethod
        //    The first argument becomes the receiver
        Function<String, String> toUpper = String::toUpperCase;
        System.out.println("toUpper(\"hello\") = " + toUpper.apply("hello"));

        Comparator<String> byLength = Comparator.comparingInt(String::length);
        List<String> words = new ArrayList<>(List.of("banana", "fig", "cherry", "date", "apple"));
        words.sort(byLength);
        System.out.println("Sorted by length: " + words);

        // 4. Constructor reference: ClassName::new
        Supplier<ArrayList<String>> listMaker = ArrayList::new;
        ArrayList<String> newList = listMaker.get();
        newList.add("created via constructor ref");
        System.out.println(newList);
    }

    public static void main(String[] args) {
        System.out.println("===== DAY 03: LAMBDAS & FUNCTIONAL INTERFACES =====\n");
        lambdaSyntax();
        functionDemo();
        predicateDemo();
        consumerDemo();
        supplierDemo();
        methodReferenceDemo();
        System.out.println("\n===== KEY TAKEAWAYS =====");
        System.out.println("1. Lambdas implement @FunctionalInterface (one abstract method)");
        System.out.println("2. Function: transform  | Predicate: test | Consumer: act | Supplier: produce");
        System.out.println("3. Chain with andThen/compose (Function), and/or/negate (Predicate)");
        System.out.println("4. Method references: Static::, instance::, Type::instance, Type::new");
    }
}
