import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class WordLengthExtractor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Extract Words by Length ===");
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter minimum word length: ");
        int minLength = sc.nextInt();
        
        // Pattern: words with at least minLength characters
        String regex = "\\b[a-zA-Z]{" + minLength + ",}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        ArrayList<String> words = new ArrayList<>();
        while (matcher.find()) {
            words.add(matcher.group());
        }
        
        System.out.println("\nWords with " + minLength + "+ characters:");
        if (words.isEmpty()) {
            System.out.println("No words found!");
        } else {
            System.out.println(String.join(", ", words));
        }
        
        sc.close();
    }
}