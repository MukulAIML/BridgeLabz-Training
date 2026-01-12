import java.util.Scanner;

public class ReplaceNewlines {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Replace Newlines with Space ===");
        System.out.println("Enter text (type 'END' on new line to finish):");
        
        StringBuilder text = new StringBuilder();
        String line;
        while (!(line = sc.nextLine()).equals("END")) {
            text.append(line).append("\n");
        }
        
        String input = text.toString();
        // Replace newlines with spaces
        String result = input.replaceAll("\\n+", " ");
        
        System.out.println("\nOriginal:\n" + input);
        System.out.println("Result: " + result);
        
        sc.close();
    }
}