import java.util.Scanner;

public class RemoveSpecialChars {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Remove Special Characters ===");
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        
        // Keep only alphanumeric and spaces
        String result = text.replaceAll("[^a-zA-Z0-9\\s]", "");
        
        System.out.println("\nOriginal: " + text);
        System.out.println("Result:   " + result);
        
        sc.close();
    }
}