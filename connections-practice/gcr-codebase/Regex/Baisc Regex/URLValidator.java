import java.util.Scanner;
import java.util.regex.Pattern;

public class URLValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== URL Validator ===");
        System.out.print("Enter URL: ");
        String url = sc.nextLine();
        
        // Pattern: http or https URLs
        String regex = "^https?://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/.*)?$";
        boolean isValid = Pattern.matches(regex, url);
        
        if (isValid) {
            System.out.println("✅ Valid URL!");
        } else {
            System.out.println("❌ Invalid URL!");
        }
        
        sc.close();
    }
}