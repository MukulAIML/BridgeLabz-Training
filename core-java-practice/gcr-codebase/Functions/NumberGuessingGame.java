import java.util.Random;
import java.util.Scanner;

/**
 * Problem 1: Number Guessing Game
 * The computer tries to guess a number between 1 and 100 that the user is thinking of.
 * The user provides feedback (high, low, or correct).
 */
public class NumberGuessingGame {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Think of a number between 1 and 100.");
        System.out.println("I will try to guess it. Tell me if my guess is:");
        System.out.println("- 'high' if my guess is too high");
        System.out.println("- 'low' if my guess is too low");
        System.out.println("- 'correct' if I guessed right\n");
        
        int min = 1;
        int max = 100;
        int attempts = 0;
        
        while (true) {
            int guess = generateGuess(min, max);
            attempts++;
            
            System.out.println("My guess: " + guess);
            System.out.print("Your feedback (high/low/correct): ");
            String feedback = scanner.nextLine().toLowerCase();
            
            if (feedback.equals("correct")) {
                System.out.println("Great! I guessed it in " + attempts + " attempt(s).");
                break;
            } else if (feedback.equals("high")) {
                max = guess - 1;
            } else if (feedback.equals("low")) {
                min = guess + 1;
            } else {
                System.out.println("Invalid input. Please enter 'high', 'low', or 'correct'.");
                attempts--;
                continue;
            }
            
            if (min > max) {
                System.out.println("Something went wrong. Please restart the game.");
                break;
            }
        }
        
        scanner.close();
    }
    
    /**
     * Generates a random guess between min and max
     * @param min Minimum value
     * @param max Maximum value
     * @return Random number between min and max
     */
    public static int generateGuess(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}

