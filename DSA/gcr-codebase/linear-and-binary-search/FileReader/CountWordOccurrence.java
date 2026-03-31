import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountWordOccurrence {
    
    public static int countWord(String filePath, String targetWord) {
        int count = 0;
        
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) {
                        count++;
                    }
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        String filePath = "sample.txt";
        String targetWord = "the";
        int occurrence = countWord(filePath, targetWord);
        System.out.println("The word \"" + targetWord + "\" appears " + occurrence + " times.");
    }
}

