import java.util.Scanner;

/**
 * Problem 4: Remove Duplicates from a String
 * This program removes all duplicate characters from a given string.
 */
public class RemoveDuplicates {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        String result = removeDuplicates(input);
        
        System.out.println("String after removing duplicates: " + result);
        
        scanner.close();
    }
    
    /**
     * Removes duplicate characters from a string
     * @param str The input string
     * @return String with duplicates removed
     */
    public static String removeDuplicates(String str) {
        StringBuilder result = new StringBuilder();
        boolean[] seen = new boolean[256];
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!seen[ch]) {
                result.append(ch);
                seen[ch] = true;
            }
        }
        
        return result.toString();
    }
}

