import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CapitalizeFirstLetter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Capitalize First Letter of Each Word ===");
        System.out.print("Enter text: ");
        String text = sc.nextLine().toLowerCase();
        
        // Pattern: word boundaries followed by a letter
        String regex = "\\b([a-z])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(result);
        
        System.out.println("\nOriginal: " + text);
        System.out.println("Result:   " + result.toString());
        
        sc.close();
    }
}