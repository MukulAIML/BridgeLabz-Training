import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a paragraph:");
        String paragraph = scanner.nextLine();
        
        String[] words = paragraph.toLowerCase()
            .replaceAll("[^a-zA-Z ]", "")
            .split("\\s+");
        
        Map<String, Long> wordFrequency = Arrays.stream(words)
            .filter(word -> !word.isEmpty())
            .collect(Collectors.toMap(
                word -> word,
                word -> 1L,
                Long::sum
            ));
        
        System.out.println("\nWord Frequency:");
        wordFrequency.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(entry -> 
                System.out.println(entry.getKey() + ": " + entry.getValue())
            );
        
        scanner.close();
    }
}
