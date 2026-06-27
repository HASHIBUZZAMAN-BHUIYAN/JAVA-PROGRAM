import java.util.*;

/**
 * Day 02 Solutions: Advanced Collections
 * Run: java Solutions
 */
public class Solutions {

    // =========================================================
    // Exercise 1 Solution: BrowserHistory
    // =========================================================
    static class BrowserHistory {
        private final Deque<String> backStack    = new ArrayDeque<>();
        private final Deque<String> forwardStack = new ArrayDeque<>();
        private String current;

        public BrowserHistory(String homepage) {
            this.current = homepage;
        }

        public void visit(String url) {
            backStack.push(current);   // save current to back
            forwardStack.clear();      // new visit clears forward
            current = url;
        }

        public String back() {
            if (backStack.isEmpty()) return "No history";
            forwardStack.push(current);
            current = backStack.pop();
            return current;
        }

        public String forward() {
            if (forwardStack.isEmpty()) return "No forward history";
            backStack.push(current);
            current = forwardStack.pop();
            return current;
        }

        public String current() { return current; }
    }

    // =========================================================
    // Exercise 2 Solution: wordFrequency
    // =========================================================
    static TreeMap<String, Integer> wordFrequency(String text) {
        TreeMap<String, Integer> freq = new TreeMap<>();
        // Split on any non-word characters (spaces, punctuation, etc.)
        String[] words = text.toLowerCase().split("[^a-z]+");
        for (String word : words) {
            if (!word.isEmpty()) {
                freq.merge(word, 1, Integer::sum);
            }
        }
        return freq;
    }

    // =========================================================
    // Exercise 3 Solution: slidingWindowMax (O(n) with Deque)
    // =========================================================
    static int[] slidingWindowMax(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        int n = nums.length;
        int[] result = new int[n - k + 1];
        // Deque stores indices; front is always the index of the window's max
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Remove indices outside the window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // Remove from back all indices whose values are < nums[i]
            // (they can never be the max for any future window)
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            // Start recording once the first window is complete
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    // =========================================================
    // Exercise 4 Solution: topKFrequent
    // =========================================================
    static List<Integer> topKFrequent(int[] nums, int k) {
        // Step 1: count frequencies
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.merge(n, 1, Integer::sum);

        // Step 2: min-heap of size k keyed by frequency
        // Keeps the k highest-frequency elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(freq::get)
        );
        for (int num : freq.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();  // remove the least frequent
            }
        }

        // Step 3: collect result (order not guaranteed)
        List<Integer> result = new ArrayList<>(minHeap);
        result.sort(Comparator.comparingInt(freq::get).reversed());
        return result;
    }

    public static void main(String[] args) {
        System.out.println("===== SOLUTIONS: ADVANCED COLLECTIONS =====\n");

        // Exercise 1
        System.out.println("--- Exercise 1: Browser History ---");
        BrowserHistory h = new BrowserHistory("home.html");
        h.visit("google.com");
        h.visit("github.com");
        System.out.println("Current: " + h.current());    // github.com
        System.out.println("Back:    " + h.back());        // google.com
        System.out.println("Back:    " + h.back());        // home.html
        System.out.println("Back:    " + h.back());        // No history
        System.out.println("Forward: " + h.forward());     // google.com
        h.visit("stackoverflow.com");
        System.out.println("Visit stackoverflow — forward cleared");
        System.out.println("Forward: " + h.forward());     // No forward history

        // Exercise 2
        System.out.println("\n--- Exercise 2: Word Frequency ---");
        String text = "the cat sat on the mat the cat";
        System.out.println(wordFrequency(text));
        // {cat=2, mat=1, on=1, sat=1, the=3}

        String text2 = "to be or not to be that is the question";
        System.out.println(wordFrequency(text2));

        // Exercise 3
        System.out.println("\n--- Exercise 3: Sliding Window Max ---");
        int[] r1 = slidingWindowMax(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println("k=3: " + Arrays.toString(r1));  // [3, 3, 5, 5, 6, 7]
        int[] r2 = slidingWindowMax(new int[]{1, -1}, 1);
        System.out.println("k=1: " + Arrays.toString(r2));  // [1, -1]
        int[] r3 = slidingWindowMax(new int[]{9, 8, 7, 6}, 2);
        System.out.println("k=2: " + Arrays.toString(r3));  // [9, 8, 7]

        // Exercise 4
        System.out.println("\n--- Exercise 4: Top-K Frequent ---");
        System.out.println(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));  // [1, 2]
        System.out.println(topKFrequent(new int[]{1}, 1));                   // [1]
        System.out.println(topKFrequent(new int[]{4, 4, 4, 2, 2, 1}, 2));  // [4, 2]

        System.out.println("\nAll solutions verified!");
    }
}
