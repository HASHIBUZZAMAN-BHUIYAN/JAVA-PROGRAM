/**
 * Day 06 — Arrays — Exercises
 */
import java.util.Arrays;

public class Exercises {

    // Exercise 1: Return the largest element in an int array.
    static int findMax(int[] arr) {
        // TODO
        return 0;
    }

    // Exercise 2: Return a NEW array that is the reverse of the input.
    static int[] reverse(int[] arr) {
        // TODO
        return new int[0];
    }

    // Exercise 3: Return true if the array contains the target value.
    static boolean contains(int[] arr, int target) {
        // TODO
        return false;
    }

    // Exercise 4: Return the sum of all values in a 2D int array.
    static int sumMatrix(int[][] matrix) {
        // TODO
        return 0;
    }

    // Exercise 5: Rotate array left by one position.
    // e.g. {1,2,3,4} -> {2,3,4,1}
    static int[] rotateLeft(int[] arr) {
        // TODO
        return new int[0];
    }

    public static void main(String[] args) {
        int[] data = {3, 7, 1, 9, 4};
        System.out.println("findMax: " + findMax(data));                        // 9
        System.out.println("reverse: " + Arrays.toString(reverse(data)));       // [4,9,1,7,3]
        System.out.println("contains 7: " + contains(data, 7));                 // true
        System.out.println("contains 5: " + contains(data, 5));                 // false

        int[][] m = {{1,2},{3,4},{5,6}};
        System.out.println("sumMatrix: " + sumMatrix(m));                       // 21

        System.out.println("rotateLeft: " + Arrays.toString(rotateLeft(data))); // [7,1,9,4,3]
    }
}
