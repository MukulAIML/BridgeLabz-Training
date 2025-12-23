import java.util.Scanner;

/**
 * Program to find the frequency of characters using nested loops
 */
public class CharacterFrequencyNestedLoops {
    
    /**
     * Finds the frequency of characters in a string using nested loops
     * @param text Input string
     * @return 1D String array with characters and their frequencies
     */
    public static String[] findCharacterFrequency(String text) {
        char[] charArray = text.toCharArray();
        int[] freqArray = new int[charArray.length];
        int textLength = charArray.length;
        
        for (int i = 0; i < textLength; i++) {
            if (charArray[i] == '0') {
                continue;
            }
            
            freqArray[i] = 1;
            
            for (int j = i + 1; j < textLength; j++) {
                if (charArray[i] == charArray[j]) {
                    freqArray[i]++;
                    charArray[j] = '0';
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < textLength; i++) {
            if (charArray[i] != '0') {
                count++;
            }
        }
        
        String[] result = new String[count];
        int index = 0;
        
        for (int i = 0; i < textLength; i++) {
            if (charArray[i] != '0') {
                result[index] = charArray[i] + ": " + freqArray[i];
                index++;
            }
        }
        
        return result;
    }
    
    /**
     * Displays character frequencies
     * @param frequencyData Array with character frequencies
     */
    public static void displayFrequency(String[] frequencyData) {
        System.out.println("\n========================================");
        System.out.println("    CHARACTER FREQUENCY REPORT");
        System.out.println("========================================");
        System.out.printf("%-15s %-10s%n", "Character", "Frequency");
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < frequencyData.length; i++) {
            String[] parts = frequencyData[i].split(": ");
            System.out.printf("%-15s %-10s%n", parts[0], parts[1]);
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
            
            String[] frequencyData = findCharacterFrequency(inputText);
            displayFrequency(frequencyData);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

