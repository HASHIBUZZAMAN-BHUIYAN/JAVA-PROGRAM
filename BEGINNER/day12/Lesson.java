/**
 * Day 12 — Collections I: ArrayList and HashMap
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class Lesson {

    public static void main(String[] args) {
        System.out.println("=== Day 12: ArrayList and HashMap ===\n");

        // ── ARRAYLIST ────────────────────────────────────────────────────────
        System.out.println("=== ArrayList ===");

        List<String> fruits = new ArrayList<>(); // declare as List<>, store as ArrayList<>

        fruits.add("apple");
        fruits.add("banana");
        fruits.add("cherry");
        fruits.add("apple"); // duplicates allowed
        System.out.println("After add: " + fruits);
        System.out.println("Size: " + fruits.size());
        System.out.println("Get index 1: " + fruits.get(1));
        System.out.println("Contains 'banana': " + fruits.contains("banana"));
        System.out.println("Index of 'cherry': " + fruits.indexOf("cherry"));

        fruits.set(1, "blueberry"); // replace index 1
        System.out.println("After set(1,'blueberry'): " + fruits);

        fruits.remove("apple");     // removes FIRST occurrence
        System.out.println("After remove('apple'): " + fruits);

        fruits.remove(0);           // remove by index
        System.out.println("After remove(0): " + fruits);

        System.out.println("\nFor-each iteration:");
        for (String f : fruits) System.out.println("  - " + f);

        Collections.sort(fruits);
        System.out.println("Sorted: " + fruits);
        System.out.println();

        // ── HASHMAP ──────────────────────────────────────────────────────────
        System.out.println("=== HashMap ===");

        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob",   82);
        scores.put("Carol", 91);
        scores.put("Dave",  78);

        System.out.println("Scores: " + scores);
        System.out.println("Alice's score: " + scores.get("Alice"));
        System.out.println("Contains key 'Bob': " + scores.containsKey("Bob"));
        System.out.println("Contains value 91: " + scores.containsValue(91));
        System.out.println("Eve's score (default 0): " + scores.getOrDefault("Eve", 0));

        scores.put("Bob", 88); // update existing key
        scores.remove("Dave");
        System.out.println("After update/remove: " + scores);

        System.out.println("\nIterating HashMap entries:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.printf("  %-10s -> %d%n", entry.getKey(), entry.getValue());
        }

        System.out.println("\nJust keys: " + scores.keySet());
        System.out.println("Just values: " + scores.values());
        System.out.println();

        // ── COMMON PATTERN: count occurrences ────────────────────────────────
        System.out.println("=== Counting with HashMap ===");
        String sentence = "the cat sat on the mat the cat";
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : sentence.split(" ")) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        wordCount.forEach((word, count) ->
            System.out.printf("  %-5s -> %d%n", word, count));

        System.out.println("\n=== End of Day 12 Lesson ===");
    }
}
