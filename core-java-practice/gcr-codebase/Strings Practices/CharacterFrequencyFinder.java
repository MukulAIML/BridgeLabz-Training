import java.util.Scanner;

/**
 * Program to find the frequency of characters in a string using charAt() method
 */
public class CharacterFrequencyFinder {
    
    private static final int ASCII_SIZE = 256;
    
    /**
     * Finds the frequency of characters in a string using charAt() method
     * @param text Input string
     * @return 2D array with characters and their frequencies
     */
    public static String[][] findCharacterFrequency(String text) {
        int[] freqArray = new int[ASCII_SIZE];
        int textLength = text.length();
        
        for (int i = 0; i < textLength; i++) {
            char currChar = text.charAt(i);
            freqArray[currChar]++;
        }
        
        int count = 0;
        for (int i = 0; i < ASCII_SIZE; i++) {
            if (freqArray[i] > 0) {
                count++;
            }
        }
        
        String[][] result = new String[count][2];
        int index = 0;
        
        for (int i = 0; i < textLength; i++) {
            char currChar = text.charAt(i);
            if (freqArray[currChar] > 0) {
                result[index][0] = String.valueOf(currChar);
                result[index][1] = String.valueOf(freqArray[currChar]);
                freqArray[currChar] = 0;
                index++;
            }
        }
        
        return result;
    }
    
    /**
     * Displays character frequencies in tabular format
     * @param frequencyData 2D array with characters and frequencies
     */
    public static void displayFrequency(String[][] frequencyData) {
        System.out.println("\n========================================");
        System.out.println("    CHARACTER FREQUENCY REPORT");
        System.out.println("========================================");
        System.out.printf("%-15s %-10s%n", "Character", "Frequency");
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < frequencyData.length; i++) {
            System.out.printf("%-15s %-10s%n", 
                             frequencyData[i][0], 
                             frequencyData[i][1]);
        }
        
        System.out.println("========================================\n");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Enter a string: ");
            String inputText = sc.nextLine();
            
            if (inputText == null || inputText.isEmpty()) {
                System.out.println("Error: Please enter a non-empty string.");
                return;
            }
            
            String[][] frequencyData = findCharacterFrequency(inputText);
            displayFrequency(frequencyData);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

