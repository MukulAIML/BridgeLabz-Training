import java.util.Scanner;

public class TrimStringUsingCharAt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string with spaces: ");
        String text = scanner.nextLine();

        int start = 0, end = text.length() - 1;
        while (start <= end && text.charAt(start) == ' ') start++;
        while (end >= start && text.charAt(end) == ' ') end--;

        String trimmedManual = "";
        for (int i = start; i <= end; i++) {
            trimmedManual += text.charAt(i);
        }

        String trimmedBuiltIn = text.trim();
        boolean areEqual = trimmedManual.equals(trimmedBuiltIn);

        System.out.println("Manual Trim: [" + trimmedManual + "]");
        System.out.println("Built-in Trim: [" + trimmedBuiltIn + "]");
        System.out.println("Are both equal? " + areEqual);
        scanner.close();
    }
}
