/**
 * Day 13 — Collections II — Solutions
 */
import java.util.*;
import java.util.stream.*;

public class Solutions {

    static <T> Set<T> toSet(List<T> list) {
        return new HashSet<>(list);
    }

    static List<Integer> evenSorted(List<Integer> nums) {
        return nums.stream()
            .filter(n -> n % 2 == 0)
            .sorted()
            .collect(Collectors.toList());
    }

    static int streamSum(List<Integer> nums) {
        return nums.stream().mapToInt(Integer::intValue).sum();
    }

    static List<String> upperSorted(List<String> words) {
        return words.stream()
            .map(String::toUpperCase)
            .sorted()
            .collect(Collectors.toList());
    }

    static long countLong(List<String> words) {
        return words.stream().filter(w -> w.length() > 4).count();
    }

    public static void main(String[] args) {
        System.out.println(toSet(List.of(1,2,2,3,3)));
        System.out.println(evenSorted(List.of(5,2,8,1,4,3,6)));
        System.out.println(streamSum(List.of(1,2,3,4,5)));
        System.out.println(upperSorted(List.of("banana","apple","cherry")));
        System.out.println(countLong(List.of("hi","hello","hey","howdy","ok")));
    }
}
