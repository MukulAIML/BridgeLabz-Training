// ========================================
// FilterStreams.java
// Filter Streams - Convert Uppercase to Lowercase
// ========================================

import java.io.*;
import java.util.Scanner;

public class FilterStreams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter source file path: ");
        String sourcePath = sc.nextLine();
        
        System.out.print("Enter destination file path: ");
        String destPath = sc.nextLine();
        
        BufferedReader br = null;
        BufferedWriter bw = null;
        
        try {
            br = new BufferedReader(new FileReader(sourcePath));
            bw = new BufferedWriter(new FileWriter(destPath));
            
            String line;
            int lineCount = 0;
            
            while ((line = br.readLine()) != null) {
                String lowerCaseLine = line.toLowerCase();
                bw.write(lowerCaseLine);
                bw.newLine();
                lineCount++;
            }
            
            System.out.println("\nConversion completed successfully!");
            System.out.println("Total lines processed: " + lineCount);
            System.out.println("Output saved to: " + destPath);
            
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
                if (bw != null) bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        sc.close();
    }
}