import java.util.Scanner;

public class RemoveDuplicateWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Remove Duplicate Consecutive Words ===");
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        
        // Remove consecutive duplicate words
        String result = text.replaceAll("\\b(\\w+)\\s+\\1\\b", "$1");
        
        System.out.println("\nOriginal: " + text);
        System.out.println("Result:   " + result);
        
        sc.close();
    }
}