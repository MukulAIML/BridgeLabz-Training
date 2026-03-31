import java.util.Scanner;

public class AddLineNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Add Line Numbers ===");
        System.out.println("Enter text (type 'END' on new line to finish):");
        
        StringBuilder text = new StringBuilder();
        String line;
        while (!(line = sc.nextLine()).equals("END")) {
            text.append(line).append("\n");
        }
        
        String[] lines = text.toString().split("\n");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < lines.length; i++) {
            result.append((i + 1)).append(". ").append(lines[i]).append("\n");
        }
        
        System.out.println("\nResult:");
        System.out.print(result.toString());
        
        sc.close();
    }
}