import java.util.Scanner;

/**
 * Problem 3: Palindrome String Check
 * This program checks if a given string is a palindrome.
 */
public class PalindromeCheck {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        if (isPalindrome(input)) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }
        
        scanner.close();
    }
    
    /**
     * Checks if a string is a palindrome
     * @param str The input string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(String str) {
        String lowerStr = str.toLowerCase();
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
}

