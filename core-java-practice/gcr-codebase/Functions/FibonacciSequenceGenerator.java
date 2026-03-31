import java.util.Scanner;

/**
 * Problem 4: Fibonacci Sequence Generator
 * This program generates the Fibonacci sequence up to a specified number of terms.
 */
public class FibonacciSequenceGenerator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of terms for Fibonacci sequence: ");
        int terms = scanner.nextInt();
        
        if (terms <= 0) {
            System.out.println("Please enter a positive number.");
        } else {
            generateFibonacci(terms);
        }
        
        scanner.close();
    }
    
    /**
     * Calculates and prints the Fibonacci sequence
     * @param n Number of terms to generate
     */
    public static void generateFibonacci(int n) {
        System.out.println("Fibonacci sequence up to " + n + " terms:");
        
        if (n == 1) {
            System.out.println("0");
            return;
        }
        
        if (n == 2) {
            System.out.println("0, 1");
            return;
        }
        
        int first = 0;
        int second = 1;
        
        System.out.print(first + ", " + second);
        
        for (int i = 3; i <= n; i++) {
            int next = first + second;
            System.out.print(", " + next);
            first = second;
            second = next;
        }
        
        System.out.println();
    }
}

