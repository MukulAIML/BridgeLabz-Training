// ========================================
// FileHandling.java
// File Handling - Read and Write a Text File
// ========================================

import java.io.*;
import java.util.Scanner;

public class FileHandling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter source file path: ");
        String sourcePath = sc.nextLine();
        
        System.out.print("Enter destination file path: ");
        String destPath = sc.nextLine();
        
        File sourceFile = new File(sourcePath);
        
        if (!sourceFile.exists()) {
            System.out.println("Error: Source file does not exist!");
            sc.close();
            return;
        }
        
        FileInputStream fis = null;
        FileOutputStream fos = null;
        
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(destPath);
            
            int byteData;
            while ((byteData = fis.read()) != -1) {
                fos.write(byteData);
            }
            
            System.out.println("File copied successfully!");
            
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sc.close();
    }
}