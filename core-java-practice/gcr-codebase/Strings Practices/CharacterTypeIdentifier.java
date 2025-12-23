import java.util.Scanner;

public class CharacterTypeIdentifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = scanner.nextLine();

        System.out.println("Char\tType");
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            char lower = Character.toLowerCase(ch);
            if (lower >= 'a' && lower <= 'z') {
                if ("aeiou".indexOf(lower) != -1)
                    System.out.println(ch + "\tVowel");
                else
                    System.out.println(ch + "\tConsonant");
            } else {
                System.out.println(ch + "\tNot a Letter");
            }
        }
        scanner.close();
    }
}
