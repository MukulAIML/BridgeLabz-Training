import java.util.Scanner;
import java.util.Arrays;

public class CharArrayComparison {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = scanner.next();

        char[] customArray = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            customArray[i] = text.charAt(i);
        }

        char[] builtinArray = text.toCharArray();

        boolean areEqual = Arrays.equals(customArray, builtinArray);

        System.out.println("Custom char array: " + Arrays.toString(customArray));
        System.out.println("Built-in char array: " + Arrays.toString(builtinArray));
        System.out.println("Are arrays equal? " + areEqual);

        scanner.close();
    }
}
