import java.util.Scanner;

/**
 * Program to check if two texts are anagrams
 */
public class AnagramChecker {
    
    private static final int ASCII_SIZE = 256;
    
    /**
     * Checks if two texts are anagrams
     * @param t1 First text
     * @param t2 Second text
     * @return true if anagrams, false otherwise
     */
    public static boolean areAnagrams(String t1, String t2) {
        if (t1.length() != t2.length()) {
            return false;
        }
        
        int[] frequencyArray1 = new int[ASCII_SIZE];
        int[] frequencyArray2 = new int[ASCII_SIZE];
        
        for (int i = 0; i < t1.length(); i++) {
            frequencyArray1[t1.charAt(i)]++;
            frequencyArray2[t2.charAt(i)]++;
        }
        
        for (int i = 0; i < ASCII_SIZE; i++) {
            if (frequencyArray1[i] != frequencyArray2[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Displays the result of anagram check
     * @param t1 First text
     * @param t2 Second text
     * @param result Result of anagram check
     */
    public static void displayResult(String t1, String t2, boolean result) {
        System.out.println("\n========================================");
        System.out.println("        ANAGRAM CHECK RESULT");
        System.out.println("========================================");
        System.out.println("Text 1: " + t1);
        System.out.println("Text 2: " + t2);
        System.out.println("----------------------------------------");
        System.out.println("Result: " + (result ? "Anagrams" : "Not Anagrams"));
        System.out.println("========================================\n");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Enter first string: ");
            String t1 = sc.nextLine();
            
            System.out.print("Enter second string: ");
            String t2 = sc.nextLine();
            
            if (t1 == null || t2 == null || t1.isEmpty() || t2.isEmpty()) {
                System.out.println("Error: Please enter non-empty strings.");
                return;
            }
            
            boolean result = areAnagrams(t1, t2);
            displayResult(t1, t2, result);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

