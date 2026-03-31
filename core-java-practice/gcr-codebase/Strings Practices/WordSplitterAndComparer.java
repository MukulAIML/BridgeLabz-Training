import java.util.Scanner;
import java.util.Arrays;

public class WordSplitterAndComparer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String text = scanner.nextLine();

        String[] builtInWords = text.split(" ");
        String[] manualWords = new String[builtInWords.length];
        String word = "";
        int index = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch != ' ') {
                word += ch;
            } else if (!word.isEmpty()) {
                manualWords[index++] = word;
                word = "";
            }
        }
        if (!word.isEmpty()) {
            manualWords[index++] = word;
        }

        boolean areEqual = Arrays.equals(builtInWords, manualWords);

        System.out.println("Manual Split: " + Arrays.toString(manualWords));
        System.out.println("Built-in Split: " + Arrays.toString(builtInWords));
        System.out.println("Are both equal? " + areEqual);
        scanner.close();
    }
}
