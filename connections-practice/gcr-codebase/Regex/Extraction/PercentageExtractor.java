import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class PercentageExtractor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Percentage Extractor ===");
        System.out.print("Enter text containing percentages: ");
        String text = sc.nextLine();
        
        // Pattern: numbers followed by %
        String regex = "\\d+(\\.\\d+)?%";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        ArrayList<String> percentages = new ArrayList<>();
        while (matcher.find()) {
            percentages.add(matcher.group());
        }
        
        System.out.println("\nExtracted Percentages:");
        if (percentages.isEmpty()) {
            System.out.println("No percentages found!");
        } else {
            for (String pct : percentages) {
                System.out.println(pct);
            }
        }
        
        sc.close();
    }
}