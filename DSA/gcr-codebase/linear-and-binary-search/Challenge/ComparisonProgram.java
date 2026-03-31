import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ComparisonProgram {
    
    public static long testStringBuffer(int iterations, String testString) {
        long startTime = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        
        for (int i = 0; i < iterations; i++) {
            stringBuffer.append(testString);
        }
        
        String result = stringBuffer.toString();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    public static long testStringBuilder(int iterations, String testString) {
        long startTime = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append(testString);
        }
        
        String result = stringBuilder.toString();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    public static int countWordsWithFileReader(String filePath) {
        int wordCount = 0;
        
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return wordCount;
    }
    
    public static int countWordsWithInputStreamReader(String filePath) {
        int wordCount = 0;
        
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return wordCount;
    }
    
    public static void main(String[] args) {
        System.out.println("=== StringBuffer vs StringBuilder ===");
        int iterations = 1000000;
        String testString = "hello";
        
        long stringBufferTime = testStringBuffer(iterations, testString);
        long stringBuilderTime = testStringBuilder(iterations, testString);
        
        System.out.println("StringBuffer time: " + (stringBufferTime / 1_000_000.0) + " ms");
        System.out.println("StringBuilder time: " + (stringBuilderTime / 1_000_000.0) + " ms");
        
        System.out.println("\n=== FileReader vs InputStreamReader ===");
        String filePath = "sample.txt";
        
        long start1 = System.nanoTime();
        int wordCount1 = countWordsWithFileReader(filePath);
        long end1 = System.nanoTime();
        
        long start2 = System.nanoTime();
        int wordCount2 = countWordsWithInputStreamReader(filePath);
        long end2 = System.nanoTime();
        
        System.out.println("FileReader word count: " + wordCount1);
        System.out.println("FileReader time: " + ((end1 - start1) / 1_000_000.0) + " ms");
        System.out.println("InputStreamReader word count: " + wordCount2);
        System.out.println("InputStreamReader time: " + ((end2 - start2) / 1_000_000.0) + " ms");
    }
}

