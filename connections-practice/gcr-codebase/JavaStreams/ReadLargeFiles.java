// ========================================
// ReadLargeFiles.java
// Read a Large File Line by Line
// ========================================

import java.io.*;
import java.util.Scanner;

public class ReadLargeFiles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter file path: ");
        String filePath = sc.nextLine();
        
        BufferedReader br = null;
        int totalLines = 0;
        int errorLines = 0;
        
        try {
            br = new BufferedReader(new FileReader(filePath));
            
            System.out.println("\n=== Lines containing 'error' (case insensitive) ===\n");
            
            String line;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null) {
                lineNumber++;
                totalLines++;
                
                if (line.toLowerCase().contains("error")) {
                    System.out.println("Line " + lineNumber + ": " + line);
                    errorLines++;
                }
            }
            
            System.out.println("\n=== Summary ===");
            System.out.println("Total lines read: " + totalLines);
            System.out.println("Lines with 'error': " + errorLines);
            
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