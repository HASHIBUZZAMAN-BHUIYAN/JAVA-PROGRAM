/**
 * Day 04 Mini Project — Number Guessing Game
 * Applies: loops (while), conditionals, Scanner input
 *
 * Compile: javac MiniProject.java
 * Run:     java MiniProject
 */
import java.util.Scanner;
import java.util.Random;

public class MiniProject {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random   = new Random();

        System.out.println("=== Number Guessing Game ===");
        System.out.println("I've picked a number between 1 and 100.");
        System.out.println("Can you guess it?\n");

        int totalRounds = 0;
        int totalWins   = 0;
        String playAgain;

        do {
            int secret  = random.nextInt(100) + 1; // 1..100
            int guess;
            int attempts = 0;
            int maxAttempts = 7;
            boolean won = false;

            System.out.println("--- New Round ---");

            while (attempts < maxAttempts) {
                System.out.print("Guess (" + (maxAttempts - attempts) + " tries left): ");
                guess = scanner.nextInt();
                attempts++;

                if (guess == secret) {
                    System.out.println("Correct! You got it in " + attempts + " attempt(s).\n");
                    won = true;
                    break;
                } else if (guess < secret) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!won) {
                System.out.println("Out of tries! The number was " + secret + ".\n");
            }

            totalRounds++;
            if (won) totalWins++;

            System.out.print("Play again? (yes/no): ");
            playAgain = scanner.next().trim().toLowerCase();

        } while (playAgain.equals("yes") || playAgain.equals("y"));

        System.out.println("\n=== Game Over ===");
        System.out.println("Rounds played: " + totalRounds);
        System.out.println("Rounds won:    " + totalWins);

        double pct = totalRounds == 0 ? 0 : (totalWins * 100.0 / totalRounds);
        System.out.printf("Win rate:      %.1f%%\n", pct);

        scanner.close();
    }
}
