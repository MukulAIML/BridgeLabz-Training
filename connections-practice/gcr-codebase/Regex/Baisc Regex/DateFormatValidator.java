import java.util.Scanner;
import java.util.regex.Pattern;

public class DateFormatValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Date Format Validator (dd/mm/yyyy) ===");
        System.out.print("Enter date: ");
        String date = sc.nextLine();
        
        // Pattern: dd/mm/yyyy format
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
        boolean isValid = Pattern.matches(regex, date);
        
        if (isValid) {
            System.out.println("✅ Valid date format!");
        } else {
            System.out.println("❌ Invalid date format!");
        }
        
        sc.close();
    }
}