import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class CapitalizedWordsExtractor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Capitalized Words Extractor ===");
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        
        // Pattern: words starting with uppercase letter
        String regex = "\\b[A-Z][a-z]*\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        ArrayList<String> words = new ArrayList<>();
        while (matcher.find()) {
            words.add(matcher.group());
        }
        
        System.out.println("\nCapitalized Words:");
        if (words.isEmpty()) {
            System.out.println("No capitalized words found!");
        } else {
            System.out.println(String.join(", ", words));
        }
        
        sc.close();
    }
}