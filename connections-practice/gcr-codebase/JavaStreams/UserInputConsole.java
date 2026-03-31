// ========================================
// UserInputConsole.java
// Read User Input from Console
// ========================================

import java.io.*;
import java.util.Scanner;

public class UserInputConsole {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fw = null;
        
        try {
            System.out.print("Enter your name: ");
            String name = br.readLine();
            
            System.out.print("Enter your age: ");
            String age = br.readLine();
            
            System.out.print("Enter your favorite programming language: ");
            String language = br.readLine();
            
            System.out.print("Enter output file name: ");
            String fileName = br.readLine();
            
            fw = new FileWriter(fileName);
            
            fw.write("===== User Information =====\n");
            fw.write("Name: " + name + "\n");
            fw.write("Age: " + age + "\n");
            fw.write("Favorite Programming Language: " + language + "\n");
            fw.write("============================\n");
            
            System.out.println("\nData saved successfully to " + fileName);
            
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (fw != null) fw.close();
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}