import java.util.*;
import java.util.regex.*;

public class HTMLTagExtractor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== HTML Tag Extractor ===");
        System.out.print("Enter HTML text: ");
        String text = sc.nextLine();
        
        // Pattern: HTML tags
        String regex = "<[^>]+>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        ArrayList<String> tags = new ArrayList<>();
        while (matcher.find()) {
            tags.add(matcher.group());
        }
        
        System.out.println("\nExtracted HTML Tags:");
        if (tags.isEmpty()) {
            System.out.println("No HTML tags found!");
        } else {
            for (String tag : tags) {
                System.out.println(tag);
            }
        }
        
        sc.close();
    }
}