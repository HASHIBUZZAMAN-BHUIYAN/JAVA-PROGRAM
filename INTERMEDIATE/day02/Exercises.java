import java.util.*;

/**
 * Day 02 Exercises: Advanced Collections
 *
 * Complete each TODO. Run with: java Exercises
 */
public class Exercises {

    // =========================================================
    // Exercise 1: Browser History with LinkedList/Deque
    // =========================================================
    // TODO: Implement a BrowserHistory class using ArrayDeque (or LinkedList).
    // Methods:
    //   - void visit(String url)     — add url to history
    //   - String back()              — go to previous url, return it (or "No history")
    //   - String forward()           — go forward (return url or "No forward history")
    //   - String current()           — return current url
    //
    // Hint: Use two stacks (backStack and forwardStack).
    // When you visit a new page, clear the forward stack.

    // YOUR CODE HERE:


    // =========================================================
    // Exercise 2: Word Frequency with TreeMap
    // =========================================================
    // TODO: Write a method:
    //   static TreeMap<String, Integer> wordFrequency(String text)
    // Split the text into words (split on spaces + punctuation),
    // count occurrences of each word (case-insensitive),
    // return a TreeMap (so output is alphabetically sorted).
    //
    // Example:
    //   wordFrequency("the cat sat on the mat the cat")
    //   => {cat=2, mat=1, on=1, sat=1, the=3}

    // YOUR CODE HERE:


    // =========================================================
    // Exercise 3: Sliding Window Maximum with Deque
    // =========================================================
    // TODO: Write a method:
    //   static int[] slidingWindowMax(int[] nums, int k)
    // Return an array of the maximum values in each window of size k.
    // Use an ArrayDeque to keep track of indices efficiently.
    //
    // Example:
    //   slidingWindowMax([1, 3, -1, -3, 5, 3, 6, 7], 3)
    //   => [3, 3, 5, 5, 6, 7]
    //
    // Brute-force O(n*k) is acceptable; O(n) with Deque is bonus.

    // YOUR CODE HERE:


    // =========================================================
    // Exercise 4: Top-K Frequent Elements with PriorityQueue
    // =========================================================
    // TODO: Write a method:
    //   static List<Integer> topKFrequent(int[] nums, int k)
    // Return the k most frequently occurring numbers.
    // Order within the result does not matter.
    //
    // Steps:
    //   1. Count frequencies with a HashMap
    //   2. Use a PriorityQueue (min-heap of size k) to find top-k
    //
    // Example:
    //   topKFrequent([1,1,1,2,2,3], 2) => [1, 2]  (in any order)

    // YOUR CODE HERE:


    // =========================================================
    // MAIN
    // =========================================================
    public static void main(String[] args) {

        System.out.println("===== EXERCISES: ADVANCED COLLECTIONS =====\n");

        System.out.println("--- Exercise 1: Browser History ---");
        // TODO: Uncomment after implementing BrowserHistory
        // BrowserHistory h = new BrowserHistory("home.html");
        // h.visit("google.com");
        // h.visit("github.com");
        // System.out.println(h.current());   // github.com
        // System.out.println(h.back());      // google.com
        // System.out.println(h.back());      // home.html
        // System.out.println(h.forward());   // google.com
        // h.visit("stackoverflow.com");
        // System.out.println(h.forward());   // No forward history
        System.out.println("(implement BrowserHistory above)");

        System.out.println("\n--- Exercise 2: Word Frequency ---");
        // TODO: Uncomment after implementing wordFrequency
        // String text = "the cat sat on the mat the cat";
        // System.out.println(wordFrequency(text));
        // Expected: {cat=2, mat=1, on=1, sat=1, the=3}
        System.out.println("(implement wordFrequency above)");

        System.out.println("\n--- Exercise 3: Sliding Window Maximum ---");
        // TODO: Uncomment after implementing slidingWindowMax
        // int[] result = slidingWindowMax(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        // System.out.println(Arrays.toString(result));  // [3, 3, 5, 5, 6, 7]
        System.out.println("(implement slidingWindowMax above)");

        System.out.println("\n--- Exercise 4: Top-K Frequent ---");
        // TODO: Uncomment after implementing topKFrequent
        // System.out.println(topKFrequent(new int[]{1,1,1,2,2,3}, 2));  // [1, 2]
        // System.out.println(topKFrequent(new int[]{1}, 1));             // [1]
        System.out.println("(implement topKFrequent above)");

        System.out.println("\nDone!");
    }
}
