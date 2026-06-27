/**
 * Day 13 — Collections II: Set, Comparator/Comparable, Streams
 */
import java.util.*;
import java.util.stream.*;

class Student implements Comparable<Student> {
    String name;
    int    grade;
    String subject;

    Student(String name, int grade, String subject) {
        this.name    = name;
        this.grade   = grade;
        this.subject = subject;
    }

    // Natural ordering: by grade descending
    @Override
    public int compareTo(Student other) {
        return Integer.compare(other.grade, this.grade); // descending
    }

    @Override
    public String toString() {
        return String.format("%-10s %-8s %d", name, subject, grade);
    }
}

public class Lesson {

    public static void main(String[] args) {
        System.out.println("=== Day 13: Sets, Sorting, Streams ===\n");

        // ── HASHSET — unique elements, no order ──────────────────────────────
        System.out.println("--- HashSet ---");
        Set<String> colors = new HashSet<>();
        colors.add("red"); colors.add("blue"); colors.add("green");
        colors.add("red"); // duplicate — silently ignored
        System.out.println("HashSet: " + colors + " (no defined order)");
        System.out.println("Contains 'blue': " + colors.contains("blue"));

        // ── TREESET — unique + sorted ─────────────────────────────────────────
        System.out.println("\n--- TreeSet ---");
        Set<Integer> nums = new TreeSet<>(Set.of(5, 2, 8, 1, 9, 2, 5));
        System.out.println("TreeSet (sorted, no dups): " + nums);
        System.out.println();

        // ── SORTING WITH COMPARABLE ──────────────────────────────────────────
        System.out.println("--- Sorting with Comparable ---");
        List<Student> students = new ArrayList<>(List.of(
            new Student("Alice", 92, "Math"),
            new Student("Bob",   78, "Math"),
            new Student("Carol", 85, "Science"),
            new Student("Dave",  92, "Science"),
            new Student("Eve",   67, "Math")
        ));
        Collections.sort(students); // uses compareTo — grade descending
        System.out.println("By grade (desc):");
        students.forEach(s -> System.out.println("  " + s));
        System.out.println();

        // ── SORTING WITH COMPARATOR ──────────────────────────────────────────
        System.out.println("--- Sorting with Comparator ---");
        // By name alphabetically
        students.sort(Comparator.comparing(s -> s.name));
        System.out.println("By name:");
        students.forEach(s -> System.out.println("  " + s));

        // Multi-key sort: subject then grade desc
        students.sort(Comparator.comparing((Student s) -> s.subject)
                                .thenComparing(s -> -s.grade));
        System.out.println("\nBy subject then grade desc:");
        students.forEach(s -> System.out.println("  " + s));
        System.out.println();

        // ── JAVA STREAMS ─────────────────────────────────────────────────────
        System.out.println("--- Java Streams ---");

        // filter: students with grade >= 85
        List<Student> highAchievers = students.stream()
            .filter(s -> s.grade >= 85)
            .collect(Collectors.toList());
        System.out.println("Grade >= 85: " + highAchievers.size() + " students");
        highAchievers.forEach(s -> System.out.println("  " + s));

        // map: extract names of Math students
        System.out.println("\nMath students:");
        students.stream()
            .filter(s -> s.subject.equals("Math"))
            .map(s -> s.name)
            .sorted()
            .forEach(name -> System.out.println("  " + name));

        // reduce: average grade
        OptionalDouble avg = students.stream()
            .mapToInt(s -> s.grade)
            .average();
        System.out.printf("%nClass average: %.1f%n", avg.orElse(0));

        // count, min, max
        System.out.println("Highest grade: " +
            students.stream().mapToInt(s -> s.grade).max().orElse(0));

        System.out.println("\n=== End of Day 13 Lesson ===");
    }
}
