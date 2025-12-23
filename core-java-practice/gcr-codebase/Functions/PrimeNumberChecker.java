import java.util.Scanner;

/**
 * Problem 3: Prime Number Checker
 * This program checks whether a given number is a prime number.
 */
public class PrimeNumberChecker {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number to check if it's prime: ");
        int number = scanner.nextInt();
        
        boolean isPrime = checkPrime(number);
        
        if (isPrime) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is not a prime number.");
        }
        
        scanner.close();
    }
    
    /**
     * Checks if a number is prime
     * @param num The number to check
     * @return true if prime, false otherwise
     */
    public static boolean checkPrime(int num) {
        if (num <= 1) {
            return false;
        }
        
        if (num == 2) {
            return true;
        }
        
        if (num % 2 == 0) {
            return false;
        }
        
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}

