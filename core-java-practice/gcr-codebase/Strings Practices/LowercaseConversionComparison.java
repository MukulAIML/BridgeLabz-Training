import java.util.Scanner;

public class LowercaseConversionComparison {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        String customLower = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                customLower += (char)(ch + 32);
            } else {
                customLower += ch;
            }
        }

        String builtInLower = text.toLowerCase();

        boolean areEqual = customLower.equals(builtInLower);

        System.out.println("Custom lowercase: " + customLower);
        System.out.println("Built-in lowercase: " + builtInLower);
        System.out.println("Are both lowercase strings equal? " + areEqual);

        scanner.close();
    }
}
