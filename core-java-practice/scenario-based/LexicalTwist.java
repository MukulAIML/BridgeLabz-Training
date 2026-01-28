import java.util.*;

public class LexicalTwist {
    private String firstWord;
    private String secondWord;

    public LexicalTwist(String firstWord, String secondWord) {
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }

    public void execute() {
        // check if second is reversed of first (Case-insensitive)
        StringBuilder reversedFirst = new StringBuilder();
        for (int i = firstWord.length()-1; i >= 0; i--) {
            reversedFirst.append(Character.toLowerCase(firstWord.charAt(i)));
        }
        StringBuilder result = new StringBuilder();
        if (secondWord.contentEquals(reversedFirst)) {
            for (int i = 0; i < reversedFirst.length(); i++) {
                char c = reversedFirst.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    result.append('@');
                }
                else {
                    result.append(c);
                }
            }
            System.out.println(result);
        }
        else {
            StringBuilder tmp = new StringBuilder(firstWord + secondWord);
            StringBuilder concatenated = new StringBuilder();
            int consonants = 0;
            int vowels = 0;
            List<Character> vowelList = new ArrayList<>();
            List<Character> consonantList = new ArrayList<>();
            // convert to uppercase
            for (int i = 0; i < tmp.length(); i++) {
                concatenated.append(Character.toUpperCase(tmp.charAt(i)));
            }

            for (int i = 0; i < concatenated.length(); i++) {
                char c = concatenated.charAt(i);
                if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                    vowels++;
                    if (!vowelList.contains(c)) {
                        vowelList.add(c);
                    }

                }
                else {
                    consonants++;
                    if (!consonantList.contains(c)) {
                        consonantList.add(c);
                    }                }
                result.append(Character.toUpperCase(c));
            }
            if (vowels > consonants) {
                System.out.println(vowelList.get(0) + " " + vowelList.get(1));
            }
            else if (consonants > vowels) {
                System.out.println(consonantList.get(0) + " " + consonantList.get(1));
            }
            else {
                System.out.println("Vowels and consonants are equal");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        String first = scanner.nextLine();
        String second = scanner.nextLine();

        LexicalTwist lexicalTwist = new LexicalTwist(first, second);
        lexicalTwist.execute();
    }
}
