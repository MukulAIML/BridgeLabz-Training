import java.util.Scanner;

/**
 * Problem 2: Reverse a String
 * This program reverses a given string without using built-in reverse functions.
 */
public class ReverseString {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        String reversed = reverseString(input);
        
        System.out.println("Reversed string: " + reversed);
        
        scanner.close();
    }
    
    /**
     * Reverses a string without using built-in functions
     * @param str The input string to reverse
     * @return The reversed string
     */
    public static String reverseString(String str) {
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        
        return new String(chars);
    }
}

