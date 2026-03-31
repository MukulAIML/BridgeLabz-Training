import java.util.Scanner;

/**
 * Problem 5: Find the Longest Word in a Sentence
 * This program finds and returns the longest word in a sentence.
 */
public class LongestWord {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();
        
        String longestWord = findLongestWord(sentence);
        
        System.out.println("Longest word: " + longestWord);
        
        scanner.close();
    }
    
    /**
     * Finds the longest word in a sentence
     * @param sentence The input sentence
     * @return The longest word in the sentence
     */
    public static String findLongestWord(String sentence) {
        String[] words = sentence.split("\\s+");
        String longestWord = "";
        
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        
        return longestWord;
    }
}

