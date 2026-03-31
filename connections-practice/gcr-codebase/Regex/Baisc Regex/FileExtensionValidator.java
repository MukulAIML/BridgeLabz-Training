import java.util.Scanner;
import java.util.regex.Pattern;

public class FileExtensionValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== File Extension Validator ===");
        System.out.println("Valid extensions: .jpg, .png, .gif, .pdf, .txt");
        System.out.print("Enter filename: ");
        String filename = sc.nextLine();
        
        // Pattern: filename with specific extensions
        String regex = "^[\\w-]+\\.(jpg|png|gif|pdf|txt)$";
        boolean isValid = Pattern.matches(regex, filename);
        
        if (isValid) {
            System.out.println("✅ Valid filename and extension!");
        } else {
            System.out.println("❌ Invalid filename or unsupported extension!");
        }
        
        sc.close();
    }
}