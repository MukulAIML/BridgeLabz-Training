import java.lang.reflect.Constructor;
import java.util.Scanner;

/**
 * BASIC LEVEL - Problem 4: Dynamically Create Objects
 * Create instances without using new keyword
 */
class Student {
    private String name;
    private int rollNo;
    
    public Student() {
        this.name = "Unknown";
        this.rollNo = 0;
    }
    
    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
    
    public void display() {
        System.out.println("Student: " + name + ", Roll No: " + rollNo);
    }
}

public class DynamicObject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Enter student name: ");
            String name = sc.nextLine();
            
            System.out.print("Enter roll number: ");
            int roll = sc.nextInt();
            
            Class<?> cls = Class.forName("Student");
            Constructor<?> constructor = cls.getDeclaredConstructor(String.class, int.class);
            
            Student student = (Student) constructor.newInstance(name, roll);
            student.display();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
