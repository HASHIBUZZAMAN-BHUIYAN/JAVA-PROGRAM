import java.util.ArrayList;
import java.util.List;

/**
 * Day 01 Lesson: Generics
 *
 * Generics allow you to write classes and methods that work with ANY type
 * while still being type-safe at compile time. Before generics (Java 1.4),
 * everything was stored as Object and you needed casts everywhere — error-prone.
 *
 * Run: java Lesson
 */
public class Lesson {

    // =========================================================
    // SECTION 1: Generic Classes
    // =========================================================

    /**
     * A simple generic Box that can hold any type T.
     * T is a "type parameter" — a placeholder replaced at compile time.
     */
    static class Box<T> {
        private T value;

        public Box(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Box[" + value + "]";
        }
    }

    // A generic Pair holding two (possibly different) types
    static class Pair<A, B> {
        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst()  { return first; }
        public B getSecond() { return second; }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    // =========================================================
    // SECTION 2: Bounded Type Parameters
    // =========================================================

    /**
     * <T extends Comparable<T>> means T must implement Comparable.
     * This lets us call compareTo() inside the method safely.
     */
    static <T extends Comparable<T>> T findMax(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    /**
     * Find the minimum element in a list — T must be Comparable.
     */
    static <T extends Comparable<T>> T findMin(List<T> list) {
        if (list.isEmpty()) throw new IllegalArgumentException("List is empty");
        T min = list.get(0);
        for (T item : list) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        return min;
    }

    // Multiple bounds: T must extend Number AND implement Comparable
    static <T extends Number & Comparable<T>> T clamp(T value, T min, T max) {
        if (value.compareTo(min) < 0) return min;
        if (value.compareTo(max) > 0) return max;
        return value;
    }

    // =========================================================
    // SECTION 3: Wildcards
    // =========================================================

    /**
     * Upper bounded wildcard: List<? extends Number>
     * Means: a list of Number or any subtype (Integer, Double, Float...).
     * Rule: you can READ from it, but NOT add to it.
     */
    static double sumList(List<? extends Number> list) {
        double total = 0;
        for (Number n : list) {
            total += n.doubleValue();  // safe to read as Number
        }
        return total;
    }

    /**
     * Lower bounded wildcard: List<? super Integer>
     * Means: a list of Integer or any supertype (Number, Object).
     * Rule: you can ADD Integers to it, but reading gives Object.
     */
    static void addIntegers(List<? super Integer> list, int count) {
        for (int i = 1; i <= count; i++) {
            list.add(i);  // safe to add Integer (or subtype)
        }
    }

    /**
     * Unbounded wildcard: List<?>
     * Means: a list of any type. You can only read as Object.
     * Useful for operations that don't care about the element type.
     */
    static void printList(List<?> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // =========================================================
    // SECTION 4: Generic Methods (standalone)
    // =========================================================

    // Swap two elements in an array — works for any type
    static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Create a list from varargs — sugar around Arrays.asList
    @SafeVarargs
    static <T> List<T> listOf(T... elements) {
        List<T> list = new ArrayList<>();
        for (T e : elements) list.add(e);
        return list;
    }

    // =========================================================
    // MAIN — runs all demonstrations
    // =========================================================
    public static void main(String[] args) {

        System.out.println("===== DAY 01: GENERICS =====\n");

        // --- Generic Classes ---
        System.out.println("--- Generic Classes ---");
        Box<String>  strBox = new Box<>("Hello Generics");
        Box<Integer> intBox = new Box<>(42);
        Box<Double>  dblBox = new Box<>(3.14);

        System.out.println("strBox: " + strBox);
        System.out.println("intBox: " + intBox);
        System.out.println("dblBox: " + dblBox);

        // The compiler enforces the type — this would be a compile error:
        // strBox.setValue(100);  // incompatible types: int cannot be converted to String

        Pair<String, Integer> pair = new Pair<>("Alice", 30);
        System.out.println("Pair: " + pair);
        System.out.println("First: " + pair.getFirst() + ", Second: " + pair.getSecond());

        // --- Bounded Type Parameters ---
        System.out.println("\n--- Bounded Type Parameters ---");
        System.out.println("Max(3, 7) = "         + findMax(3, 7));
        System.out.println("Max(\"apple\",\"mango\") = " + findMax("apple", "mango"));
        System.out.println("Max(2.5, 1.8) = "     + findMax(2.5, 1.8));

        List<Integer> nums = listOf(5, 2, 8, 1, 9, 3);
        System.out.println("Min of " + nums + " = " + findMin(nums));

        System.out.println("clamp(15, 0, 10) = " + clamp(15, 0, 10));
        System.out.println("clamp(-3, 0, 10) = " + clamp(-3, 0, 10));
        System.out.println("clamp(5,  0, 10) = " + clamp(5, 0, 10));

        // --- Wildcards ---
        System.out.println("\n--- Wildcards ---");
        List<Integer> ints    = listOf(1, 2, 3, 4, 5);
        List<Double>  doubles = listOf(1.5, 2.5, 3.5);

        // Both work with sumList(List<? extends Number>)
        System.out.println("Sum of ints:    " + sumList(ints));
        System.out.println("Sum of doubles: " + sumList(doubles));

        List<Number> numbers = new ArrayList<>();
        addIntegers(numbers, 3);  // adds 1, 2, 3
        System.out.print("After addIntegers: ");
        printList(numbers);

        // printList works with any type
        System.out.print("String list: ");
        printList(listOf("a", "b", "c"));

        // --- Generic Methods ---
        System.out.println("\n--- Generic Methods ---");
        String[] words = {"cat", "ant", "bat"};
        System.out.println("Before swap: " + java.util.Arrays.toString(words));
        swap(words, 0, 2);
        System.out.println("After swap(0,2): " + java.util.Arrays.toString(words));

        System.out.println("\n===== KEY TAKEAWAYS =====");
        System.out.println("1. Generics provide compile-time type safety");
        System.out.println("2. <T extends X> lets you call X's methods on T");
        System.out.println("3. <? extends T> = read-only, upper-bounded wildcard");
        System.out.println("4. <? super T>   = write-friendly, lower-bounded wildcard");
        System.out.println("5. Generics are erased at runtime (type erasure)");
    }
}
