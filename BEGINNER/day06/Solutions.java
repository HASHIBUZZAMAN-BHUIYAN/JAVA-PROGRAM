/**
 * Day 06 — Arrays — Solutions
 */
import java.util.Arrays;

public class Solutions {

    static int findMax(int[] arr) {
        int max = arr[0];
        for (int v : arr) if (v > max) max = v;
        return max;
    }

    static int[] reverse(int[] arr) {
        int[] rev = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            rev[i] = arr[arr.length - 1 - i];
        return rev;
    }

    static boolean contains(int[] arr, int target) {
        for (int v : arr) if (v == target) return true;
        return false;
    }

    static int sumMatrix(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix)
            for (int v : row) sum += v;
        return sum;
    }

    static int[] rotateLeft(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length - 1; i++)
            result[i] = arr[i + 1];
        result[arr.length - 1] = arr[0];
        return result;
    }

    public static void main(String[] args) {
        int[] data = {3, 7, 1, 9, 4};
        System.out.println("findMax: " + findMax(data));
        System.out.println("reverse: " + Arrays.toString(reverse(data)));
        System.out.println("contains 7: " + contains(data, 7));
        System.out.println("contains 5: " + contains(data, 5));
        int[][] m = {{1,2},{3,4},{5,6}};
        System.out.println("sumMatrix: " + sumMatrix(m));
        System.out.println("rotateLeft: " + Arrays.toString(rotateLeft(data)));
    }
}
