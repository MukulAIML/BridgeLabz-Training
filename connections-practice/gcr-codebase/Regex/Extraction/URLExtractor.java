import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class URLExtractor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== URL Extractor ===");
        System.out.print("Enter text containing URLs: ");
        String text = sc.nextLine();
        
        // Pattern: http or https URLs
        String regex = "https?://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/[a-zA-Z0-9._~:/?#\\[\\]@!$&'()*+,;=-]*)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        ArrayList<String> urls = new ArrayList<>();
        while (matcher.find()) {
            urls.add(matcher.group());
        }
        
        System.out.println("\nExtracted URLs:");
        if (urls.isEmpty()) {
            System.out.println("No URLs found!");
        } else {
            System.out.println(String.join(", ", urls));
        }
        
        sc.close();
    }
}