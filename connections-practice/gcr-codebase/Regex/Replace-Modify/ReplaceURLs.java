import java.util.Scanner;

public class ReplaceURLs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Replace URLs with HTML Hyperlinks ===");
        System.out.print("Enter text with URLs: ");
        String text = sc.nextLine();
        
        // Replace URLs with HTML anchor tags
        String regex = "(https?://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/[^\\s]*)?)";
        String result = text.replaceAll(regex, "<a href=\"$1\">$1</a>");
        
        System.out.println("\nOriginal: " + text);
        System.out.println("Result:   " + result);
        
        sc.close();
    }
}