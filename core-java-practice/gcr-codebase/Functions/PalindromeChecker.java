import java.util.Scanner;

/**
 * Problem 5: Palindrome Checker
 * This program checks if a given string is a palindrome.
 */
public class PalindromeChecker {
    
    public static void main(String[] args) {
        String input = takeInput();
        boolean isPalindrome = checkPalindrome(input);
        displayResult(input, isPalindrome);
    }
    
    /**
     * Takes string input from the user
     * @return The input string
     */
    public static String takeInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to check if it's a palindrome: ");
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }
    
    /**
     * Checks if a string is a palindrome
     * @param str The string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean checkPalindrome(String str) {
        String lowerStr = str.toLowerCase().replaceAll("\\s+", "");
        int left = 0;
        int right = lowerStr.length() - 1;
        
        while (left < right) {
            if (lowerStr.charAt(left) != lowerStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Displays the result of palindrome check
     * @param str The input string
     * @param isPalindrome Whether the string is a palindrome
     */
    public static void displayResult(String str, boolean isPalindrome) {
        if (isPalindrome) {
            System.out.println("\"" + str + "\" is a palindrome.");
        } else {
            System.out.println("\"" + str + "\" is not a palindrome.");
        }
    }
}

