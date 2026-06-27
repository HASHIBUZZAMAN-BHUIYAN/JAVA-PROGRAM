/**
 * Day 07 — Strings — Exercises
 */
public class Exercises {

    // Exercise 1: Return true if s is a palindrome (reads same forwards and backwards).
    // Ignore case. e.g. "Racecar" -> true
    static boolean isPalindrome(String s) {
        // TODO
        return false;
    }

    // Exercise 2: Count how many times a character appears in a string.
    static int countChar(String s, char c) {
        // TODO
        return 0;
    }

    // Exercise 3: Capitalize the first letter of every word.
    // "hello world" -> "Hello World"
    static String titleCase(String s) {
        // TODO
        return "";
    }

    // Exercise 4: Remove all spaces from a string.
    static String removeSpaces(String s) {
        // TODO
        return "";
    }

    // Exercise 5: Given a sentence, return the longest word.
    static String longestWord(String sentence) {
        // TODO
        return "";
    }

    public static void main(String[] args) {
        System.out.println("isPalindrome(\"Racecar\"): " + isPalindrome("Racecar")); // true
        System.out.println("isPalindrome(\"hello\"): " + isPalindrome("hello"));     // false
        System.out.println("countChar(\"banana\",'a'): " + countChar("banana", 'a')); // 3
        System.out.println("titleCase(\"hello world\"): " + titleCase("hello world")); // Hello World
        System.out.println("removeSpaces(\"a b c\"): " + removeSpaces("a b c")); // abc
        System.out.println("longestWord(\"the quick brown fox\"): " + longestWord("the quick brown fox")); // quick
    }
}
