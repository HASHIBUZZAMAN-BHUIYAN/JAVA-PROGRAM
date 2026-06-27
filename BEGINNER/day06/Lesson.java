/**
 * Day 06 — Arrays
 * Topics: 1D arrays, 2D arrays, array methods, iteration
 */
import java.util.Arrays;

public class Lesson {

    public static void main(String[] args) {
        System.out.println("=== Day 06: Arrays ===\n");

        // ── 1. DECLARING AND INITIALIZING ───────────────────────────────────
        int[] scores = new int[5];          // 5 zeros
        int[] primes = {2, 3, 5, 7, 11};   // array literal
        String[] names = {"Alice", "Bob", "Carol"};

        System.out.println("scores (default): " + Arrays.toString(scores));
        System.out.println("primes: " + Arrays.toString(primes));
        System.out.println("names:  " + Arrays.toString(names));
        System.out.println();

        // ── 2. ACCESSING ELEMENTS (zero-based index) ────────────────────────
        System.out.println("primes[0] = " + primes[0]);  // first element
        System.out.println("primes[4] = " + primes[4]);  // last element
        System.out.println("primes.length = " + primes.length);
        System.out.println();

        // ── 3. MODIFYING ELEMENTS ───────────────────────────────────────────
        scores[0] = 90;
        scores[1] = 85;
        scores[2] = 78;
        scores[3] = 92;
        scores[4] = 88;
        System.out.println("scores after assignment: " + Arrays.toString(scores));
        System.out.println();

        // ── 4. ITERATING WITH FOR LOOP ──────────────────────────────────────
        int total = 0;
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }
        System.out.println("Sum of scores: " + total);
        System.out.println("Average: " + (total / scores.length));
        System.out.println();

        // ── 5. ENHANCED FOR-EACH LOOP ───────────────────────────────────────
        System.out.print("Names: ");
        for (String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();
        System.out.println();

        // ── 6. ARRAYS.SORT ───────────────────────────────────────────────────
        int[] unsorted = {5, 2, 8, 1, 9, 3};
        System.out.println("Before sort: " + Arrays.toString(unsorted));
        Arrays.sort(unsorted);
        System.out.println("After sort:  " + Arrays.toString(unsorted));
        System.out.println();

        // ── 7. 2D ARRAYS ────────────────────────────────────────────────────
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("3x3 matrix:");
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%3d", matrix[row][col]);
            }
            System.out.println();
        }
        System.out.println("matrix[1][2] = " + matrix[1][2]); // row 1, col 2 = 6
        System.out.println();

        // ── 8. COMMON MISTAKE: ArrayIndexOutOfBoundsException ───────────────
        // Accessing index >= array.length causes a runtime error.
        // primes[5] would crash — there are only indices 0-4.
        System.out.println("Last prime: primes[primes.length - 1] = "
                           + primes[primes.length - 1]);

        System.out.println("\n=== End of Day 06 Lesson ===");
    }
}
