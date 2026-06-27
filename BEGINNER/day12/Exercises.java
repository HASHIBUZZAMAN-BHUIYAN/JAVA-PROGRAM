/**
 * Day 12 — Collections I — Exercises
 */
import java.util.*;

public class Exercises {

    // Exercise 1: Given a List<Integer>, return the sum of all elements.
    static int sumList(List<Integer> list) {
        // TODO
        return 0;
    }

    // Exercise 2: Remove all duplicates from a List<String> while preserving order.
    static List<String> removeDuplicates(List<String> list) {
        // TODO: return new list with duplicates removed, order preserved
        return new ArrayList<>();
    }

    // Exercise 3: Given a Map<String,Integer>, return the key with the highest value.
    static String topKey(Map<String, Integer> map) {
        // TODO
        return "";
    }

    // Exercise 4: Convert an array of strings to a List and return it.
    static List<String> arrayToList(String[] arr) {
        // TODO
        return new ArrayList<>();
    }

    // Exercise 5: Count how many times each character appears in a string.
    // Return the result as a Map<Character, Integer>.
    static Map<Character, Integer> charFrequency(String s) {
        // TODO
        return new HashMap<>();
    }

    public static void main(String[] args) {
        System.out.println("sumList: " + sumList(List.of(1,2,3,4,5)));          // 15

        List<String> withDups = new ArrayList<>(List.of("a","b","a","c","b"));
        System.out.println("removeDuplicates: " + removeDuplicates(withDups));   // [a,b,c]

        Map<String,Integer> scores = new HashMap<>();
        scores.put("Alice",90); scores.put("Bob",75); scores.put("Carol",95);
        System.out.println("topKey: " + topKey(scores));                         // Carol

        System.out.println("arrayToList: " + arrayToList(new String[]{"x","y","z"})); // [x,y,z]

        System.out.println("charFrequency('hello'): " + charFrequency("hello")); // {h=1,e=1,l=2,o=1}
    }
}
