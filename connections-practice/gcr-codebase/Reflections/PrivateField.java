import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * BASIC LEVEL - Problem 2: Access Private Field
 * Modify and retrieve private field values using Reflection
 */
class Person {
    private int age = 25;
    private String name = "John";
}

public class PrivateField {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            Person p = new Person();
            
            Field ageField = Person.class.getDeclaredField("age");
            ageField.setAccessible(true);
            
            System.out.println("Original age: " + ageField.get(p));
            
            System.out.print("Enter new age: ");
            int newAge = sc.nextInt();
            
            ageField.set(p, newAge);
            System.out.println("Modified age: " + ageField.get(p));
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
