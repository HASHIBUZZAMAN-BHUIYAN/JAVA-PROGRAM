/**
 * Day 12 — Collections I — Solutions
 */
import java.util.*;

public class Solutions {

    static int sumList(List<Integer> list) {
        int sum = 0;
        for (int v : list) sum += v;
        return sum;
    }

    static List<String> removeDuplicates(List<String> list) {
        List<String> result = new ArrayList<>();
        Set<String> seen = new LinkedHashSet<>();
        for (String s : list) {
            if (seen.add(s)) result.add(s);
        }
        return result;
    }

    static String topKey(Map<String, Integer> map) {
        String best = null;
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (best == null || e.getValue() > map.get(best)) best = e.getKey();
        }
        return best;
    }

    static List<String> arrayToList(String[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    static Map<Character, Integer> charFrequency(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        return map;
    }

    public static void main(String[] args) {
        System.out.println(sumList(List.of(1,2,3,4,5)));
        System.out.println(removeDuplicates(new ArrayList<>(List.of("a","b","a","c","b"))));
        Map<String,Integer> scores = new HashMap<>();
        scores.put("Alice",90); scores.put("Bob",75); scores.put("Carol",95);
        System.out.println(topKey(scores));
        System.out.println(arrayToList(new String[]{"x","y","z"}));
        System.out.println(charFrequency("hello"));
    }
}
