/**
 * Day 06 Mini Project — Student Grade Tracker
 * Uses arrays to store grades and compute statistics.
 *
 * Run: java MiniProject
 */
import java.util.Arrays;
import java.util.Scanner;

public class MiniProject {

    static char letterGrade(double score) {
        if (score >= 90) return 'A';
        if (score >= 80) return 'B';
        if (score >= 70) return 'C';
        if (score >= 60) return 'D';
        return 'F';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many students? ");
        int n = sc.nextInt();
        String[] names  = new String[n];
        double[] grades = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Name for student " + (i + 1) + ": ");
            names[i] = sc.next();
            System.out.print("Grade for " + names[i] + " (0-100): ");
            grades[i] = sc.nextDouble();
        }

        // Statistics
        double sum = 0, min = grades[0], max = grades[0];
        for (double g : grades) {
            sum += g;
            if (g < min) min = g;
            if (g > max) max = g;
        }
        double avg = sum / n;

        System.out.println("\n=== Grade Report ===");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-15s  %.1f  (%c)%n",
                names[i], grades[i], letterGrade(grades[i]));
        }

        System.out.printf("%nAverage : %.2f%n", avg);
        System.out.printf("Highest : %.1f%n", max);
        System.out.printf("Lowest  : %.1f%n", min);

        // Sorted copy
        double[] sorted = Arrays.copyOf(grades, n);
        Arrays.sort(sorted);
        System.out.println("Sorted (low→high): " + Arrays.toString(sorted));

        sc.close();
    }
}
