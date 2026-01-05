import java.util.*;

public class WordFrequency {
    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        
        String cleanText = text.toLowerCase().replaceAll("[^a-z\\s]", "");
        String[] words = cleanText.split("\\s+");
        
        for (String word : words) {
            if (!word.isEmpty()) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }
        
        return frequencyMap;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter text (press Enter when done):");
        String text = sc.nextLine();
        
        Map<String, Integer> result = countWords(text);
        System.out.println("\nWord Frequency: " + result);
        
        sc.close();
    }
}