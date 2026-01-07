// ========================================
// DataStreams.java
// Data Streams - Store and Retrieve Primitive Data
// ========================================

import java.io.*;
import java.util.Scanner;

public class DataStreams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Student Data Management ===");
        System.out.println("1. Save student data");
        System.out.println("2. Retrieve student data");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();
        
        if (choice == 1) {
            saveStudentData(sc, fileName);
        } else if (choice == 2) {
            retrieveStudentData(fileName);
        } else {
            System.out.println("Invalid choice!");
        }
        
        sc.close();
    }
    
    static void saveStudentData(Scanner sc, String fileName) {
        DataOutputStream dos = null;
        
        try {
            dos = new DataOutputStream(new FileOutputStream(fileName));
            
            System.out.print("How many students? ");
            int n = sc.nextInt();
            sc.nextLine();
            
            dos.writeInt(n); // Write number of students first
            
            for (int i = 0; i < n; i++) {
                System.out.println("\n--- Student " + (i + 1) + " ---");
                
                System.out.print("Enter Roll Number: ");
                int rollNo = sc.nextInt();
                sc.nextLine();
                
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                
                System.out.print("Enter GPA: ");
                double gpa = sc.nextDouble();
                sc.nextLine();
                
                dos.writeInt(rollNo);
                dos.writeUTF(name);
                dos.writeDouble(gpa);
            }
            
            System.out.println("\nStudent data saved successfully!");
            
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (dos != null) dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static void retrieveStudentData(String fileName) {
        DataInputStream dis = null;
        
        try {
            dis = new DataInputStream(new FileInputStream(fileName));
            
            int n = dis.readInt(); // Read number of students
            
            System.out.println("\n=== Student Records ===");
            for (int i = 0; i < n; i++) {
                int rollNo = dis.readInt();
                String name = dis.readUTF();
                double gpa = dis.readDouble();
                
                System.out.println("\nStudent " + (i + 1) + ":");
                System.out.println("Roll Number: " + rollNo);
                System.out.println("Name: " + name);
                System.out.println("GPA: " + gpa);
            }
            
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (dis != null) dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}