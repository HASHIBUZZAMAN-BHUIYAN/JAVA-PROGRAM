/**
 * INTERMEDIATE Day 05 — File I/O with java.nio.file
 */
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;

public class Lesson {

    public static void main(String[] args) throws IOException {
        System.out.println("=== INTERMEDIATE Day 05: File I/O ===\n");

        // Working directory for temp files during this demo
        Path tempDir = Path.of("day05_temp");
        Files.createDirectories(tempDir);

        // ── 1. WRITE A FILE ──────────────────────────────────────────────────
        System.out.println("--- 1. Writing a file ---");
        Path simple = tempDir.resolve("hello.txt");
        Files.writeString(simple, "Hello, Java Files!\nLine 2\nLine 3\n");
        System.out.println("Written to: " + simple);
        System.out.println("File exists: " + Files.exists(simple));
        System.out.println("File size: " + Files.size(simple) + " bytes\n");

        // ── 2. READ A FILE ───────────────────────────────────────────────────
        System.out.println("--- 2. Reading a file ---");
        // readAllLines — for smaller files
        List<String> lines = Files.readAllLines(simple);
        System.out.println("Lines: " + lines.size());
        lines.forEach(line -> System.out.println("  > " + line));
        System.out.println();

        // Read as single String (Java 11+)
        String content = Files.readString(simple);
        System.out.println("As one string:\n" + content);

        // ── 3. WRITE LINES ───────────────────────────────────────────────────
        System.out.println("--- 3. Writing multiple lines ---");
        Path linesFile = tempDir.resolve("fruits.txt");
        List<String> fruits = List.of("Apple", "Banana", "Cherry", "Date");
        Files.write(linesFile, fruits);
        System.out.println("Written " + fruits.size() + " lines to " + linesFile);
        System.out.println();

        // ── 4. APPEND TO A FILE ──────────────────────────────────────────────
        System.out.println("--- 4. Append ---");
        Files.writeString(linesFile, "\nEldereberry\n", StandardOpenOption.APPEND);
        System.out.println("Appended. Lines now: " + Files.readAllLines(linesFile).size());
        System.out.println();

        // ── 5. STREAM LINES (memory-efficient for large files) ───────────────
        System.out.println("--- 5. Stream lines ---");
        try (Stream<String> stream = Files.lines(simple)) {
            long count = stream.filter(l -> !l.isBlank()).count();
            System.out.println("Non-blank lines: " + count);
        }
        System.out.println();

        // ── 6. WRITE + READ CSV ──────────────────────────────────────────────
        System.out.println("--- 6. CSV file ---");
        Path csv = tempDir.resolve("employees.csv");
        List<String> csvData = List.of(
            "name,department,salary",
            "Alice,Engineering,85000",
            "Bob,Marketing,72000",
            "Carol,Engineering,92000",
            "Dave,HR,65000"
        );
        Files.write(csv, csvData);

        System.out.println("CSV contents:");
        Files.lines(csv).skip(1) // skip header
            .map(line -> line.split(","))
            .forEach(p -> System.out.printf("  %-10s %-15s $%s%n", p[0], p[1], p[2]));
        System.out.println();

        // ── 7. DIRECTORY OPERATIONS ──────────────────────────────────────────
        System.out.println("--- 7. Directory walk ---");
        Files.walk(tempDir).forEach(p -> System.out.println("  " + p));
        System.out.println();

        // Cleanup
        System.out.println("--- Cleanup ---");
        Files.walk(tempDir)
            .sorted(Comparator.reverseOrder()) // delete files before dirs
            .forEach(p -> {
                try { Files.delete(p); } catch (IOException e) { /* ignore */ }
            });
        System.out.println("Temp files cleaned up");

        System.out.println("\n=== End of Day 05 Lesson ===");
    }
}
