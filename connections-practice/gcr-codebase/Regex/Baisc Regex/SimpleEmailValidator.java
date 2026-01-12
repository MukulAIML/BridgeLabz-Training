import java.util.Scanner;
import java.util.regex.Pattern;

public class SimpleEmailValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Simple Email Validator ===");
        System.out.print("Enter email address: ");
        String email = sc.nextLine();
        
        // Pattern: basic email format
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean isValid = Pattern.matches(regex, email);
        
        if (isValid) {
            System.out.println("✅ Valid email!");
        } else {
            System.out.println("❌ Invalid email!");
        }
        
        sc.close();
    }
}