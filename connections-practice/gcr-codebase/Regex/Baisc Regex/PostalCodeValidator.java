import java.util.Scanner;
import java.util.regex.Pattern;

public class PostalCodeValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Postal Code Validator ===");
        System.out.println("Format: 5 digits or 5 digits-4 digits (e.g., 12345 or 12345-6789)");
        System.out.print("Enter postal code: ");
        String code = sc.nextLine();
        
        // Pattern: 5 digits or 5 digits-4 digits
        String regex = "^\\d{5}(-\\d{4})?$";
        boolean isValid = Pattern.matches(regex, code);
        
        if (isValid) {
            System.out.println("✅ Valid postal code!");
        } else {
            System.out.println("❌ Invalid postal code!");
        }
        
        sc.close();
    }
}