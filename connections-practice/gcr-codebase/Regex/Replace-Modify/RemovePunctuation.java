import java.util.Scanner;

public class RemoveExtraPunctuation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Remove Extra Punctuation ===");
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        
        // Replace multiple punctuation with single
        String result = text.replaceAll("([!?.,;:])+", "$1");
        
        System.out.println("\nOriginal: " + text);
        System.out.println("Result:   " + result);
        
        sc.close();
    }
}