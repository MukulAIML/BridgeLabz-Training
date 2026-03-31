import java.util.Scanner;

/**
 * Problem 7: GCD and LCM Calculator
 * This program calculates the Greatest Common Divisor (GCD) and 
 * Least Common Multiple (LCM) of two numbers.
 */
public class GCDAndLCMCalculator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first number: ");
        int num1 = scanner.nextInt();
        
        System.out.print("Enter second number: ");
        int num2 = scanner.nextInt();
        
        int gcd = calculateGCD(num1, num2);
        int lcm = calculateLCM(num1, num2, gcd);
        
        System.out.println("GCD of " + num1 + " and " + num2 + " is: " + gcd);
        System.out.println("LCM of " + num1 + " and " + num2 + " is: " + lcm);
        
        scanner.close();
    }
    
    /**
     * Calculates the Greatest Common Divisor using Euclidean algorithm
     * @param a First number
     * @param b Second number
     * @return GCD of the two numbers
     */
    public static int calculateGCD(int a, int b) {
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        
        return a;
    }
    
    /**
     * Calculates the Least Common Multiple
     * @param a First number
     * @param b Second number
     * @param gcd The GCD of the two numbers
     * @return LCM of the two numbers
     */
    public static int calculateLCM(int a, int b, int gcd) {
        if (gcd == 0) {
            return 0;
        }
        return Math.abs(a * b) / gcd;
    }
}

