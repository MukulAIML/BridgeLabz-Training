import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class DateExtractor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Date Extractor (dd/mm/yyyy) ===");
        System.out.print("Enter text containing dates: ");
        String text = sc.nextLine();
        
        // Pattern: dd/mm/yyyy format
        String regex = "\\b\\d{2}/\\d{2}/\\d{4}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        ArrayList<String> dates = new ArrayList<>();
        while (matcher.find()) {
            dates.add(matcher.group());
        }
        
        System.out.println("\nExtracted Dates:");
        if (dates.isEmpty()) {
            System.out.println("No dates found!");
        } else {
            System.out.println(String.join(", ", dates));
        }
        
        sc.close();
    }
}