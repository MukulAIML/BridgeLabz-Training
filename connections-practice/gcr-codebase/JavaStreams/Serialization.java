// ========================================
// Serialization.java
// Serialization - Save and Retrieve an Object
// ========================================

import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private int id;
    private String name;
    private String department;
    private double salary;
    
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: $" + salary;
    }
}

public class Serialization {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Employee Management System ===");
        System.out.println("1. Save employees to file");
        System.out.println("2. Retrieve employees from file");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();
        
        if (choice == 1) {
            saveEmployees(sc, fileName);
        } else if (choice == 2) {
            retrieveEmployees(fileName);
        } else {
            System.out.println("Invalid choice!");
        }
        
        sc.close();
    }
    
    static void saveEmployees(Scanner sc, String fileName) {
        ObjectOutputStream oos = null;
        
        try {
            System.out.print("How many employees do you want to add? ");
            int n = sc.nextInt();
            sc.nextLine();
            
            ArrayList<Employee> employees = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                System.out.println("\n--- Employee " + (i + 1) + " ---");
                
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                
                System.out.print("Enter Department: ");
                String dept = sc.nextLine();
                
                System.out.print("Enter Salary: ");
                double salary = sc.nextDouble();
                sc.nextLine();
                
                employees.add(new Employee(id, name, dept, salary));
            }
            
            oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(employees);
            
            System.out.println("\nEmployees saved successfully!");
            
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static void retrieveEmployees(String fileName) {
        ObjectInputStream ois = null;
        
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            ArrayList<Employee> employees = (ArrayList<Employee>) ois.readObject();
            
            System.out.println("\n=== Employee List ===");
            for (Employee emp : employees) {
                System.out.println(emp);
            }
            
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}