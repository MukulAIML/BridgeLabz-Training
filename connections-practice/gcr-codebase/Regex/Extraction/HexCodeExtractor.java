import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class HexCodeExtractor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Hex Color Code Extractor ===");
        System.out.print("Enter text containing hex color codes: ");
        String text = sc.nextLine();
        
        // Pattern: # followed by 3 or 6 hex digits
        String regex = "#[0-9A-Fa-f]{6}|#[0-9A-Fa-f]{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        ArrayList<String> colors = new ArrayList<>();
        while (matcher.find()) {
            colors.add(matcher.group());
        }
        
        System.out.println("\nExtracted Hex Colors:");
        if (colors.isEmpty()) {
            System.out.println("No hex colors found!");
        } else {
            for (String color : colors) {
                System.out.println(color);
            }
        }
        
        sc.close();
    }
}