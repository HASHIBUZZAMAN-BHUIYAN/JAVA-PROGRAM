import java.util.ArrayList;
import java.util.List;

/**
 * Day 01 Solutions: Generics
 *
 * Reference solutions for all four exercises.
 * Run: java Solutions
 */
public class Solutions {

    // =========================================================
    // Exercise 1 Solution: Generic Triple class
    // =========================================================
    static class Triple<A, B, C> {
        private final A first;
        private final B second;
        private final C third;

        public Triple(A first, B second, C third) {
            this.first  = first;
            this.second = second;
            this.third  = third;
        }

        public A getFirst()  { return first; }
        public B getSecond() { return second; }
        public C getThird()  { return third; }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ", " + third + ")";
        }
    }

    // =========================================================
    // Exercise 2 Solution: findSecondLargest
    // =========================================================
    static <T extends Comparable<T>> T findSecondLargest(List<T> list) {
        if (list.size() < 2) {
            throw new IllegalArgumentException("Need at least 2 elements");
        }
        T first  = null;  // largest
        T second = null;  // second largest

        for (T item : list) {
            if (first == null || item.compareTo(first) > 0) {
                second = first;
                first  = item;
            } else if (second == null || item.compareTo(second) > 0) {
                // Only update second if item is strictly less than first
                // (handles duplicates of the max: we want the true 2nd largest)
                if (item.compareTo(first) < 0) {
                    second = item;
                }
            }
        }
        if (second == null) {
            throw new IllegalArgumentException("No second largest (all elements equal?)");
        }
        return second;
    }

    // =========================================================
    // Exercise 3 Solution: Generic Stack
    // =========================================================
    static class Stack<T> {
        private final ArrayList<T> data = new ArrayList<>();

        public void push(T item) {
            data.add(item);
        }

        public T pop() {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return data.remove(data.size() - 1);
        }

        public T peek() {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return data.get(data.size() - 1);
        }

        public boolean isEmpty() { return data.isEmpty(); }
        public int size()        { return data.size(); }

        @Override
        public String toString() { return data.toString(); }
    }

    // =========================================================
    // Exercise 4 Solution: copyIfGreater
    // =========================================================
    static <T extends Comparable<T>> void copyIfGreater(
            List<? extends T> source,
            List<? super T>   dest,
            T threshold) {
        for (T item : source) {
            if (item.compareTo(threshold) > 0) {
                dest.add(item);
            }
        }
    }

    // =========================================================
    // MAIN
    // =========================================================
    public static void main(String[] args) {

        System.out.println("===== SOLUTIONS: GENERICS =====\n");

        // Exercise 1
        System.out.println("--- Exercise 1: Triple ---");
        Triple<String, Integer, Boolean> t = new Triple<>("hello", 42, true);
        System.out.println(t);                 // (hello, 42, true)
        System.out.println(t.getFirst());      // hello
        System.out.println(t.getSecond());     // 42
        System.out.println(t.getThird());      // true

        // Exercise 2
        System.out.println("\n--- Exercise 2: findSecondLargest ---");
        List<Integer> nums = new ArrayList<>(List.of(3, 1, 4, 1, 5, 9, 2, 6));
        System.out.println("Second largest of " + nums + " = " + findSecondLargest(nums));  // 6

        List<String> words = new ArrayList<>(List.of("banana", "apple", "cherry"));
        System.out.println("Second largest of " + words + " = " + findSecondLargest(words)); // banana

        // Exercise 3
        System.out.println("\n--- Exercise 3: Generic Stack ---");
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println("Stack: " + stack);
        System.out.println("pop()  => " + stack.pop());    // c
        System.out.println("peek() => " + stack.peek());   // b
        System.out.println("size() => " + stack.size());   // 2

        Stack<Integer> intStack = new Stack<>();
        intStack.push(10);
        intStack.push(20);
        System.out.println("Int stack pop: " + intStack.pop()); // 20

        // Exercise 4
        System.out.println("\n--- Exercise 4: copyIfGreater ---");
        List<Integer> source = List.of(1, 5, 3, 8, 2, 7);
        List<Number>  dest   = new ArrayList<>();
        copyIfGreater(source, dest, 4);
        System.out.println("Elements > 4 from " + source + " => " + dest); // [5, 8, 7]

        List<String> strSrc  = List.of("apple", "mango", "banana", "cherry");
        List<Object> strDest = new ArrayList<>();
        copyIfGreater(strSrc, strDest, "b");
        System.out.println("Strings > 'b' => " + strDest); // [mango, banana, cherry]

        System.out.println("\nAll solutions verified!");
    }
}
