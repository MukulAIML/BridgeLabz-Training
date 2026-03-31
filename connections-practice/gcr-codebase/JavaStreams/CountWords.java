// ========================================
// CountWords.java
// Count Words in a File
// ========================================

import java.io.*;
import java.util.*;

public class CountWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter file path: ");
        String filePath = sc.nextLine();
        
        BufferedReader br = null;
        HashMap<String, Integer> wordCount = new HashMap<>();
        int totalWords = 0;
        
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            
            while ((line = br.readLine()) != null) {
                // Split line into words (remove punctuation and convert to lowercase)
                String[] words = line.toLowerCase().split("\\W+");
                
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                        totalWords++;
                    }
                }
            }
            
            System.out.println("\n=== Word Count Analysis ===");
            System.out.println("Total words: " + totalWords);
            System.out.println("Unique words: " + wordCount.size());
            
            // Sort words by frequency
            List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordCount.entrySet());
            sortedWords.sort((a, b) -> b.getValue().compareTo(a.getValue()));
            
            System.out.println("\n=== Top 5 Most Frequent Words ===");
            int count = 0;
            for (Map.Entry<String, Integer> entry : sortedWords) {
                if (count >= 5) break;
                System.out.println((count + 1) + ". \"" + entry.getKey() + "\" - " + entry.getValue() + " times");
                count++;
            }
            
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        sc.close();
    }
}