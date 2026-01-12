import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReverseWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Reverse Each Word ===");
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        
        // Pattern: match words
        String regex = "\\b[a-zA-Z]+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String word = matcher.group();
            String reversed = new StringBuilder(word).reverse().toString();
            matcher.appendReplacement(result, reversed);
        }
        matcher.appendTail(result);
        
        System.out.println("\nOriginal: " + text);
        System.out.println("Result:   " + result.toString());
        
        sc.close();
    }
}