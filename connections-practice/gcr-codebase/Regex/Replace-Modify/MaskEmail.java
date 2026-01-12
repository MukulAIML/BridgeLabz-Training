import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskEmail {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Mask Email Addresses ===");
        System.out.print("Enter text with email addresses: ");
        String text = sc.nextLine();
        
        // Pattern: email addresses
        String regex = "([a-zA-Z0-9._-]+)@([a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String username = matcher.group(1);
            String domain = matcher.group(2);
            String masked = username.charAt(0) + "***@" + domain;
            matcher.appendReplacement(result, masked);
        }
        matcher.appendTail(result);
        
        System.out.println("\nOriginal: " + text);
        System.out.println("Masked:   " + result.toString());
        
        sc.close();
    }
}