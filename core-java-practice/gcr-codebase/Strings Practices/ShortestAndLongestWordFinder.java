import java.util.Scanner;

public class ShortestAndLongestWordFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String text = scanner.nextLine() + " ";

        String word = "", shortest = "", longest = "";
        int min = Integer.MAX_VALUE, max = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch != ' ') {
                word += ch;
            } else if (!word.isEmpty()) {
                if (word.length() < min) {
                    min = word.length();
                    shortest = word;
                }
                if (word.length() > max) {
                    max = word.length();
                    longest = word;
                }
                word = "";
            }
        }

        System.out.println("Shortest word: " + shortest);
        System.out.println("Longest word: " + longest);
        scanner.close();
    }
}
