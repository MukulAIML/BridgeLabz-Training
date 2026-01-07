// ========================================
// ByteArrayStream.java
// ByteArray Stream - Convert Image to ByteArray
// ========================================

import java.io.*;
import java.util.Scanner;

public class ByteArrayStream {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter source image file path: ");
        String sourcePath = sc.nextLine();
        
        System.out.print("Enter destination image file path: ");
        String destPath = sc.nextLine();
        
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        ByteArrayInputStream bais = null;
        FileOutputStream fos = null;
        
        try {
            // Read image file into byte array
            fis = new FileInputStream(sourcePath);
            baos = new ByteArrayOutputStream();
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            
            byte[] imageBytes = baos.toByteArray();
            System.out.println("Image converted to byte array. Size: " + imageBytes.length + " bytes");
            
            // Write byte array back to new image file
            bais = new ByteArrayInputStream(imageBytes);
            fos = new FileOutputStream(destPath);
            
            int data;
            while ((data = bais.read()) != -1) {
                fos.write(data);
            }
            
            System.out.println("Image saved successfully to " + destPath);
            
            // Verify file sizes
            File original = new File(sourcePath);
            File copy = new File(destPath);
            
            if (original.length() == copy.length()) {
                System.out.println("Verification: Files are identical!");
            } else {
                System.out.println("Warning: File sizes do not match!");
            }
            
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (fis != null) fis.close();
                if (baos != null) baos.close();
                if (bais != null) bais.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        sc.close();
    }
}