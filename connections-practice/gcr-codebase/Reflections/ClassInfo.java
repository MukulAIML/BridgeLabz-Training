import java.lang.reflect.*;
import java.util.Scanner;

/**
 * BASIC LEVEL - Problem 1: Get Class Information
 * Displays methods, fields, and constructors of any class using Reflection
 */
public class ClassInfo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Enter class name (e.g., java.lang.String): ");
            String className = sc.nextLine();
            
            Class<?> cls = Class.forName(className);
            
            System.out.println("\n===== METHODS =====");
            for (Method m : cls.getDeclaredMethods()) {
                System.out.println(m.getName() + " - " + m.getReturnType().getSimpleName());
            }
            
            System.out.println("\n===== FIELDS =====");
            for (Field f : cls.getDeclaredFields()) {
                System.out.println(f.getName() + " - " + f.getType().getSimpleName());
            }
            
            System.out.println("\n===== CONSTRUCTORS =====");
            for (Constructor<?> c : cls.getDeclaredConstructors()) {
                System.out.println(c.getName());
            }
            
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
