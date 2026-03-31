import java.util.Scanner;
import java.util.regex.Pattern;

public class PhoneNumberValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Phone Number Validator ===");
        System.out.println("Format: 10 digits (e.g., 1234567890 or 123-456-7890)");
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();
        
        // Pattern: 10 digits with optional dashes
        String regex = "^\\d{3}-?\\d{3}-?\\d{4}$";
        boolean isValid = Pattern.matches(regex, phone);
        
        if (isValid) {
            System.out.println("✅ Valid phone number!");
        } else {
            System.out.println("❌ Invalid phone number!");
        }
        
        sc.close();
    }
}