// ========================================
// BufferedStreams.java
// Buffered Streams - Efficient File Copy
// ========================================

import java.io.*;
import java.util.Scanner;

public class BufferedStreams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter source file path: ");
        String sourcePath = sc.nextLine();
        
        System.out.print("Enter destination file path: ");
        String destPath = sc.nextLine();
        
        // Copy using normal streams
        long startUnbuffered = System.nanoTime();
        copyWithoutBuffer(sourcePath, destPath + "_normal.txt");
        long endUnbuffered = System.nanoTime();
        double timeUnbuffered = (endUnbuffered - startUnbuffered) / 1_000_000_000.0;
        
        // Copy using buffered streams
        long startBuffered = System.nanoTime();
        copyWithBuffer(sourcePath, destPath + "_buffered.txt");
        long endBuffered = System.nanoTime();
        double timeBuffered = (endBuffered - startBuffered) / 1_000_000_000.0;
        
        System.out.println("\n=== Performance Comparison ===");
        System.out.printf("Unbuffered copy time: %.4f seconds\n", timeUnbuffered);
        System.out.printf("Buffered copy time: %.4f seconds\n", timeBuffered);
        System.out.printf("Speed improvement: %.2fx faster\n", timeUnbuffered / timeBuffered);
        
        sc.close();
    }
    
    static void copyWithoutBuffer(String src, String dest) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);
            
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static void copyWithBuffer(String src, String dest) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        
        try {
            bis = new BufferedInputStream(new FileInputStream(src));
            bos = new BufferedOutputStream(new FileOutputStream(dest));
            
            byte[] buffer = new byte[4096]; // 4KB buffer
            int bytesRead;
            
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}