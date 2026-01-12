import java.util.Scanner;
import java.util.regex.Pattern;

public class LicensePlateValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== License Plate Validator ===");
        System.out.println("Format: 2 uppercase letters + 4 digits (e.g., AB1234)");
        System.out.print("Enter license plate: ");
        String plate = sc.nextLine();
        
        // Pattern: exactly 2 uppercase letters followed by exactly 4 digits
        String regex = "^[A-Z]{2}\\d{4}$";
        boolean isValid = Pattern.matches(regex, plate);
        
        if (isValid) {
            System.out.println("✅ Valid license plate!");
        } else {
            System.out.println("❌ Invalid license plate!");
        }
        
        sc.close();
    }
}