import java.lang.reflect.Field;
import java.util.*;

/**
 * ADVANCED LEVEL - Problem 1: Custom Object Mapper
 * Map HashMap values to object fields using Reflection
 */
class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;
    
    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + 
               ", department='" + department + "', salary=" + salary + "}";
    }
}

public class ObjectMapper {
    
    public static <T> T toObject(Class<T> cls, Map<String, Object> properties) {
        try {
            T obj = cls.getDeclaredConstructor().newInstance();
            
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                try {
                    Field field = cls.getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    field.set(obj, entry.getValue());
                } catch (NoSuchFieldException e) {
                    System.out.println("Field not found: " + entry.getKey());
                }
            }
            
            return obj;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Map<String, Object> data = new HashMap<>();
        
        System.out.print("Enter name: ");
        data.put("name", sc.nextLine());
        
        System.out.print("Enter age: ");
        data.put("age", sc.nextInt());
        
        sc.nextLine(); // consume newline
        System.out.print("Enter department: ");
        data.put("department", sc.nextLine());
        
        System.out.print("Enter salary: ");
        data.put("salary", sc.nextDouble());
        
        Employee emp = toObject(Employee.class, data);
        System.out.println("\nCreated Object: " + emp);
        
        sc.close();
    }
}
