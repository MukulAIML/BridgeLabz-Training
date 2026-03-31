import java.util.Scanner;

/**
 * Problem 12: Replace Word in Sentence
 * This program replaces a given word with another word in a sentence.
 */
public class ReplaceWord {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();
        
        System.out.print("Enter word to replace: ");
        String oldWord = scanner.nextLine();
        
        System.out.print("Enter new word: ");
        String newWord = scanner.nextLine();
        
        String result = replaceWord(sentence, oldWord, newWord);
        
        System.out.println("Modified sentence: " + result);
        
        scanner.close();
    }
    
    /**
     * Replaces a word with another word in a sentence
     * @param sentence The input sentence
     * @param oldWord The word to be replaced
     * @param newWord The word to replace with
     * @return Modified sentence with replaced word
     */
    public static String replaceWord(String sentence, String oldWord, String newWord) {
        String[] words = sentence.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(oldWord)) {
                result.append(newWord);
            } else {
                result.append(words[i]);
            }
            
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
}

