/**
 * Day 07 Mini Project — Word Frequency Counter
 * Reads a sentence, counts word occurrences, shows stats.
 *
 * Run: java MiniProject
 */
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class MiniProject {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Word Frequency Counter ===");
        System.out.println("Enter a sentence (or paste a paragraph):");
        String input = sc.nextLine();

        // Clean and split
        String cleaned = input.toLowerCase().replaceAll("[^a-z ]", "");
        String[] words = cleaned.trim().split("\\s+");

        // Count frequencies
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            if (!w.isEmpty())
                freq.put(w, freq.getOrDefault(w, 0) + 1);
        }

        // Stats
        int totalWords = words.length;
        int uniqueWords = freq.size();
        String mostCommon = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> e : freq.entrySet()) {
            if (e.getValue() > maxCount) {
                maxCount = e.getValue();
                mostCommon = e.getKey();
            }
        }

        System.out.println("\n=== Results ===");
        System.out.println("Total words:  " + totalWords);
        System.out.println("Unique words: " + uniqueWords);
        System.out.println("Most common:  \"" + mostCommon + "\" (" + maxCount + "x)");

        System.out.println("\nAll words (alphabetical):");
        freq.entrySet().stream()
            .sorted(Map.Entry.<String,Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey()))
            .forEach(e -> System.out.printf("  %-15s %d%n", e.getKey(), e.getValue()));

        sc.close();
    }
}
