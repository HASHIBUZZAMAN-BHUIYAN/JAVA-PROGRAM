/**
 * Day 13 Mini Project — Student Report Card with Streams
 * Applies: Comparable, Comparator, full Stream pipeline.
 *
 * Run: java MiniProject
 */
import java.util.*;
import java.util.stream.*;

class ReportStudent implements Comparable<ReportStudent> {
    private String name;
    private Map<String, Integer> subjectGrades = new LinkedHashMap<>();

    ReportStudent(String name, Map<String, Integer> grades) {
        this.name = name;
        this.subjectGrades.putAll(grades);
    }

    String getName()  { return name; }
    Map<String, Integer> getGrades() { return subjectGrades; }

    double average() {
        return subjectGrades.values().stream()
            .mapToInt(Integer::intValue).average().orElse(0);
    }

    char letterGrade() {
        double avg = average();
        if (avg >= 90) return 'A';
        if (avg >= 80) return 'B';
        if (avg >= 70) return 'C';
        if (avg >= 60) return 'D';
        return 'F';
    }

    // Natural order: by average descending
    @Override
    public int compareTo(ReportStudent other) {
        return Double.compare(other.average(), this.average());
    }

    @Override
    public String toString() {
        return String.format("%-12s avg=%.1f (%c) %s",
            name, average(), letterGrade(), subjectGrades);
    }
}

public class MiniProject {
    public static void main(String[] args) {
        List<ReportStudent> students = List.of(
            new ReportStudent("Alice",  Map.of("Math",95,"Science",88,"English",92)),
            new ReportStudent("Bob",    Map.of("Math",72,"Science",68,"English",75)),
            new ReportStudent("Carol",  Map.of("Math",85,"Science",90,"English",88)),
            new ReportStudent("Dave",   Map.of("Math",60,"Science",55,"English",65)),
            new ReportStudent("Eve",    Map.of("Math",98,"Science",95,"English",97))
        );

        System.out.println("=== Student Report Card ===\n");

        System.out.println("--- Class Rankings (by average) ---");
        students.stream()
            .sorted()
            .forEach(s -> System.out.println("  " + s));

        System.out.println("\n--- Honor Roll (average >= 85) ---");
        students.stream()
            .filter(s -> s.average() >= 85)
            .sorted()
            .forEach(s -> System.out.printf("  %-12s %.1f%n", s.getName(), s.average()));

        System.out.println("\n--- Class Averages per Subject ---");
        Set<String> subjects = students.get(0).getGrades().keySet();
        for (String subject : subjects) {
            double avg = students.stream()
                .mapToInt(s -> s.getGrades().get(subject))
                .average().orElse(0);
            System.out.printf("  %-10s: %.1f%n", subject, avg);
        }

        System.out.println("\n--- Grade Distribution ---");
        Map<Character, Long> dist = students.stream()
            .collect(Collectors.groupingBy(ReportStudent::letterGrade, Collectors.counting()));
        dist.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(e -> System.out.println("  " + e.getKey() + ": " + e.getValue()));
    }
}
