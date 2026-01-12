import java.util.Scanner;
import java.util.regex.Pattern;

public class HexColorValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Hex Color Code Validator ===");
        System.out.println("Format: # followed by 6 hex digits (e.g., #FFA500)");
        System.out.print("Enter hex color code: ");
        String color = sc.nextLine();
        
        // Pattern: # followed by exactly 6 hexadecimal characters
        String regex = "^#[0-9A-Fa-f]{6}$";
        boolean isValid = Pattern.matches(regex, color);
        
        if (isValid) {
            System.out.println("✅ Valid hex color code!");
        } else {
            System.out.println("❌ Invalid hex color code!");
        }
        
        sc.close();
    }
}