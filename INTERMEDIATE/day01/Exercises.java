import java.util.List;
import java.util.ArrayList;

/**
 * Day 01 Exercises: Generics
 *
 * Complete each TODO. Run with: java Exercises
 * Expected output is shown in each exercise comment.
 */
public class Exercises {

    // =========================================================
    // Exercise 1: Generic Triple class
    // =========================================================
    // TODO: Create a generic class named Triple<A, B, C> that holds
    // three values of potentially different types.
    // It should have:
    //   - A constructor Triple(A first, B second, C third)
    //   - Getters: getFirst(), getSecond(), getThird()
    //   - toString() returning "(first, second, third)"
    //
    // Example usage (in main):
    //   Triple<String, Integer, Boolean> t = new Triple<>("hello", 42, true);
    //   System.out.println(t);  // (hello, 42, true)

    // YOUR CODE HERE:


    // =========================================================
    // Exercise 2: Generic method — findSecondLargest
    // =========================================================
    // TODO: Write a generic method:
    //   static <T extends Comparable<T>> T findSecondLargest(List<T> list)
    // It should return the second-largest element in the list.
    // Throw IllegalArgumentException if list has fewer than 2 elements.
    //
    // Example:
    //   findSecondLargest(List.of(3, 1, 4, 1, 5, 9, 2, 6))  => 6
    //   findSecondLargest(List.of("banana","apple","cherry")) => "banana"

    // YOUR CODE HERE:


    // =========================================================
    // Exercise 3: Generic Stack class
    // =========================================================
    // TODO: Implement a generic Stack<T> using an ArrayList internally.
    // Methods needed:
    //   - void push(T item)
    //   - T pop()           — removes and returns top, throw exception if empty
    //   - T peek()          — returns top without removing, throw if empty
    //   - boolean isEmpty()
    //   - int size()
    //
    // Example:
    //   Stack<String> s = new Stack<>();
    //   s.push("a"); s.push("b"); s.push("c");
    //   s.pop()  => "c"
    //   s.peek() => "b"
    //   s.size() => 2

    // YOUR CODE HERE:


    // =========================================================
    // Exercise 4: Wildcard — copyHigherElements
    // =========================================================
    // TODO: Write a method:
    //   static <T extends Comparable<T>> void copyIfGreater(
    //       List<? extends T> source,
    //       List<? super T> dest,
    //       T threshold)
    // Copy all elements from source to dest that are greater than threshold.
    //
    // Example:
    //   List<Integer> source = List.of(1, 5, 3, 8, 2, 7);
    //   List<Number> dest = new ArrayList<>();
    //   copyIfGreater(source, dest, 4);
    //   dest => [5, 8, 7]

    // YOUR CODE HERE:


    // =========================================================
    // MAIN — test your implementations
    // =========================================================
    public static void main(String[] args) {

        System.out.println("===== EXERCISES: GENERICS =====\n");

        // Exercise 1 test
        System.out.println("--- Exercise 1: Triple ---");
        // TODO: Uncomment after implementing Triple
        // Triple<String, Integer, Boolean> t = new Triple<>("hello", 42, true);
        // System.out.println(t);                      // (hello, 42, true)
        // System.out.println(t.getFirst());           // hello
        // System.out.println(t.getSecond());          // 42
        System.out.println("(implement Triple class above)");

        // Exercise 2 test
        System.out.println("\n--- Exercise 2: findSecondLargest ---");
        // TODO: Uncomment after implementing findSecondLargest
        // List<Integer> nums = new ArrayList<>(List.of(3, 1, 4, 1, 5, 9, 2, 6));
        // System.out.println(findSecondLargest(nums));   // 6
        // List<String> words = new ArrayList<>(List.of("banana","apple","cherry"));
        // System.out.println(findSecondLargest(words));  // banana
        System.out.println("(implement findSecondLargest above)");

        // Exercise 3 test
        System.out.println("\n--- Exercise 3: Generic Stack ---");
        // TODO: Uncomment after implementing Stack
        // Stack<String> stack = new Stack<>();
        // stack.push("a"); stack.push("b"); stack.push("c");
        // System.out.println(stack.pop());   // c
        // System.out.println(stack.peek());  // b
        // System.out.println(stack.size());  // 2
        System.out.println("(implement Stack class above)");

        // Exercise 4 test
        System.out.println("\n--- Exercise 4: copyIfGreater ---");
        // TODO: Uncomment after implementing copyIfGreater
        // List<Integer> source = List.of(1, 5, 3, 8, 2, 7);
        // List<Number> dest = new ArrayList<>();
        // copyIfGreater(source, dest, 4);
        // System.out.println(dest);   // [5, 8, 7]
        System.out.println("(implement copyIfGreater above)");

        System.out.println("\nAll exercises done!");
    }
}
