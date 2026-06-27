/**
 * INTERMEDIATE Day 05 — File I/O — Exercises
 */
import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class Exercises {

    // Exercise 1: Write a method that counts lines in a file.
    static long countLines(Path file) throws IOException {
        // TODO: use Files.lines() or Files.readAllLines()
        return 0;
    }

    // Exercise 2: Write a method that searches for a word in a file
    // and returns the line numbers (1-based) where it appears.
    static List<Integer> findWord(Path file, String word) throws IOException {
        // TODO: read lines, check each for the word, collect line numbers
        return new ArrayList<>();
    }

    // Exercise 3: Write a CSV reader — read a CSV file (first row is header)
    // and return a List<Map<String,String>> where each map is column->value.
    static List<Map<String,String>> readCsv(Path file) throws IOException {
        // TODO
        return new ArrayList<>();
    }

    // Exercise 4: Copy all .txt files from one directory to another.
    static void copyTxtFiles(Path source, Path dest) throws IOException {
        // TODO: Files.walk, filter *.txt, Files.copy each to dest
    }

    public static void main(String[] args) throws IOException {
        // Create test file
        Path test = Path.of("test_exercises.txt");
        Files.write(test, List.of("Hello world", "Java is fun", "Hello again", "Bye"));

        System.out.println("Line count: " + countLines(test));               // 4
        System.out.println("'Hello' at lines: " + findWord(test, "Hello")); // [1, 3]

        Files.delete(test);
        System.out.println("Exercises done.");
    }
}
