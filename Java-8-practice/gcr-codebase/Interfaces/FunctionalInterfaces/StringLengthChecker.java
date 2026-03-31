// String Length Checker using Function
import java.util.Scanner;
import java.util.function.Function;

public class StringLengthChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter character limit: ");
        int limit = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Function<String, Integer> getLength = str -> str.length();
        
        System.out.print("Enter your message: ");
        String message = scanner.nextLine();
        
        int messageLength = getLength.apply(message);
        
        if (messageLength > limit) {
            System.out.println("Message exceeds character limit!");
            System.out.println("Length: " + messageLength + " | Limit: " + limit);
            System.out.println("Exceeded by: " + (messageLength - limit) + " characters");
        } else {
            System.out.println("Message is within character limit");
            System.out.println("Length: " + messageLength + " | Limit: " + limit);
        }
        
        scanner.close();
    }
}
