import java.util.Scanner;

/**
 * Problem 1: Count Vowels and Consonants
 * This program counts the number of vowels and consonants in a given string.
 */
public class CountVowelsAndConsonants {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        int[] counts = countVowelsAndConsonants(input);
        
        System.out.println("Number of vowels: " + counts[0]);
        System.out.println("Number of consonants: " + counts[1]);
        
        scanner.close();
    }
    
    /**
     * Counts vowels and consonants in a string
     * @param str The input string
     * @return An array where [0] is vowel count and [1] is consonant count
     */
    public static int[] countVowelsAndConsonants(String str) {
        int vowelCount = 0;
        int consonantCount = 0;
        
        String lowerStr = str.toLowerCase();
        
        for (int i = 0; i < lowerStr.length(); i++) {
            char ch = lowerStr.charAt(i);
            
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }
        }
        
        return new int[]{vowelCount, consonantCount};
    }
}

