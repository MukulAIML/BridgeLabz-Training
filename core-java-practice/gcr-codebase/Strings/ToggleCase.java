import java.util.Scanner;

/**
 * Problem 7: Toggle Case of Characters
 * This program toggles the case of each character in a string.
 */
public class ToggleCase {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        String toggled = toggleCase(input);
        
        System.out.println("Toggled case string: " + toggled);
        
        scanner.close();
    }
    
    /**
     * Toggles the case of each character in a string
     * @param str The input string
     * @return String with toggled case
     */
    public static String toggleCase(String str) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (ch >= 'A' && ch <= 'Z') {
                result.append((char)(ch + 32));
            } else if (ch >= 'a' && ch <= 'z') {
                result.append((char)(ch - 32));
            } else {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
}

