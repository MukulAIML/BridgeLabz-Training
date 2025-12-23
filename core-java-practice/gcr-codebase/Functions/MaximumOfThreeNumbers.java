import java.util.Scanner;

/**
 * Problem 2: Maximum of Three Numbers
 * This program takes three integer inputs and finds the maximum value.
 */
public class MaximumOfThreeNumbers {
    
    public static void main(String[] args) {
        int num1 = takeInput("Enter first number: ");
        int num2 = takeInput("Enter second number: ");
        int num3 = takeInput("Enter third number: ");
        
        int maximum = findMaximum(num1, num2, num3);
        
        System.out.println("The maximum of " + num1 + ", " + num2 + ", and " + num3 + " is: " + maximum);
    }
    
    /**
     * Takes integer input from the user
     * @param prompt The prompt message to display
     * @return The integer value entered by the user
     */
    public static int takeInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        int value = scanner.nextInt();
        return value;
    }
    
    /**
     * Finds the maximum of three numbers
     * @param a First number
     * @param b Second number
     * @param c Third number
     * @return The maximum value
     */
    public static int findMaximum(int a, int b, int c) {
        if (a >= b && a >= c) {
            return a;
        } else if (b >= a && b >= c) {
            return b;
        } else {
            return c;
        }
    }
}

