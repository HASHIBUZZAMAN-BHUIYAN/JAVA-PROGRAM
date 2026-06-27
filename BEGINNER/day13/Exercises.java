/**
 * Day 13 — Collections II — Exercises
 */
import java.util.*;
import java.util.stream.*;

public class Exercises {

    // Exercise 1: Return unique elements from a List as a Set.
    static <T> Set<T> toSet(List<T> list) {
        // TODO
        return new HashSet<>();
    }

    // Exercise 2: Given a list of integers, return only the even ones, sorted ascending.
    static List<Integer> evenSorted(List<Integer> nums) {
        // TODO: use stream().filter().sorted().collect()
        return new ArrayList<>();
    }

    // Exercise 3: Return the sum of all integers in the list using streams.
    static int streamSum(List<Integer> nums) {
        // TODO: use stream().mapToInt().sum()
        return 0;
    }

    // Exercise 4: Given a list of strings, return them all in uppercase, sorted.
    static List<String> upperSorted(List<String> words) {
        // TODO: stream().map(String::toUpperCase).sorted().collect(...)
        return new ArrayList<>();
    }

    // Exercise 5: Count how many strings in the list have length > 4.
    static long countLong(List<String> words) {
        // TODO: stream().filter().count()
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("toSet: " + toSet(List.of(1,2,2,3,3)));  // {1,2,3}
        System.out.println("evenSorted: " + evenSorted(List.of(5,2,8,1,4,3,6))); // [2,4,6,8]
        System.out.println("streamSum: " + streamSum(List.of(1,2,3,4,5)));       // 15
        System.out.println("upperSorted: " + upperSorted(List.of("banana","apple","cherry"))); // [APPLE,BANANA,CHERRY]
        System.out.println("countLong: " + countLong(List.of("hi","hello","hey","howdy","ok"))); // 3
    }
}
