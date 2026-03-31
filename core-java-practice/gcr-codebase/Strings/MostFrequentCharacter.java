import java.util.Scanner;

/**
 * Problem 9: Find the Most Frequent Character
 * This program finds the most frequent character in a string.
 */
public class MostFrequentCharacter {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        char mostFrequent = findMostFrequentCharacter(input);
        
        System.out.println("Most Frequent Character: '" + mostFrequent + "'");
        
        scanner.close();
    }
    
    /**
     * Finds the most frequent character in a string
     * @param str The input string
     * @return The most frequent character
     */
    public static char findMostFrequentCharacter(String str) {
        int[] frequency = new int[256];
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            frequency[ch]++;
        }
        
        char mostFrequent = str.charAt(0);
        int maxCount = frequency[mostFrequent];
        
        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (frequency[ch] > maxCount) {
                maxCount = frequency[ch];
                mostFrequent = ch;
            }
        }
        
        return mostFrequent;
    }
}

