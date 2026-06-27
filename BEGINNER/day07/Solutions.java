/**
 * Day 07 — Strings — Solutions
 */
public class Solutions {

    static boolean isPalindrome(String s) {
        String cleaned = s.toLowerCase();
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }

    static int countChar(String s, char c) {
        int count = 0;
        for (char ch : s.toCharArray()) if (ch == c) count++;
        return count;
    }

    static String titleCase(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (!w.isEmpty())
                sb.append(Character.toUpperCase(w.charAt(0)))
                  .append(w.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    static String removeSpaces(String s) {
        return s.replace(" ", "");
    }

    static String longestWord(String sentence) {
        String[] words = sentence.split(" ");
        String longest = "";
        for (String w : words)
            if (w.length() > longest.length()) longest = w;
        return longest;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("Racecar"));
        System.out.println(isPalindrome("hello"));
        System.out.println(countChar("banana", 'a'));
        System.out.println(titleCase("hello world"));
        System.out.println(removeSpaces("a b c"));
        System.out.println(longestWord("the quick brown fox"));
    }
}
