import java.util.Scanner;

/**
 * Problem 6: Factorial Using Recursion
 * This program calculates the factorial of a number using recursion.
 */
public class FactorialUsingRecursion {
    
    public static void main(String[] args) {
        int number = takeInput();
        long factorial = calculateFactorial(number);
        displayOutput(number, factorial);
    }
    
    /**
     * Takes input from the user
     * @return The number entered by the user
     */
    public static int takeInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to calculate factorial: ");
        int num = scanner.nextInt();
        scanner.close();
        return num;
    }
    
    /**
     * Calculates factorial using recursion
     * @param n The number to calculate factorial for
     * @return The factorial value
     */
    public static long calculateFactorial(int n) {
        if (n < 0) {
            return -1;
        }
        
        if (n == 0 || n == 1) {
            return 1;
        }
        
        return n * calculateFactorial(n - 1);
    }
    
    /**
     * Displays the factorial result
     * @param number The input number
     * @param factorial The calculated factorial
     */
    public static void displayOutput(int number, long factorial) {
        if (factorial == -1) {
            System.out.println("Factorial is not defined for negative numbers.");
        } else {
            System.out.println("Factorial of " + number + " is: " + factorial);
        }
    }
}

