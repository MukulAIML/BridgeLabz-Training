import java.util.Scanner;

/**
 * Problem 11: Anagram Check
 * This program checks if two strings are anagrams of each other.
 */
public class AnagramCheck {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();
        
        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();
        
        if (areAnagrams(str1, str2)) {
            System.out.println("The strings are anagrams.");
        } else {
            System.out.println("The strings are not anagrams.");
        }
        
        scanner.close();
    }
    
    /**
     * Checks if two strings are anagrams
     * @param str1 First string
     * @param str2 Second string
     * @return true if anagrams, false otherwise
     */
    public static boolean areAnagrams(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        String lowerStr1 = str1.toLowerCase();
        String lowerStr2 = str2.toLowerCase();
        
        int[] frequency1 = new int[256];
        int[] frequency2 = new int[256];
        
        for (int i = 0; i < lowerStr1.length(); i++) {
            frequency1[lowerStr1.charAt(i)]++;
            frequency2[lowerStr2.charAt(i)]++;
        }
        
        for (int i = 0; i < 256; i++) {
            if (frequency1[i] != frequency2[i]) {
                return false;
            }
        }
        
        return true;
    }
}

