import java.util.Scanner;

/**
 * Problem 10: Remove a Specific Character from a String
 * This program removes all occurrences of a specific character from a string.
 */
public class RemoveSpecificCharacter {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        System.out.print("Enter character to remove: ");
        char charToRemove = scanner.nextLine().charAt(0);
        
        String result = removeCharacter(input, charToRemove);
        
        System.out.println("Modified String: " + result);
        
        scanner.close();
    }
    
    /**
     * Removes all occurrences of a specific character from a string
     * @param str The input string
     * @param ch The character to remove
     * @return String with the character removed
     */
    public static String removeCharacter(String str, char ch) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ch) {
                result.append(str.charAt(i));
            }
        }
        
        return result.toString();
    }
}

