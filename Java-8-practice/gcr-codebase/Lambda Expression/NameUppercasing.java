import java.util.*;
import java.util.stream.Collectors;

/**
 * Employee Name Uppercasing using Method References
 * Demonstrates String::toUpperCase in stream operations
 */
public class NameUppercasing {
    
    static class Employee {
        String name;
        String department;
        
        public Employee(String name, String department) {
            this.name = name;
            this.department = department;
        }
        
        public String getName() {
            return name;
        }
        
        public String getDepartment() {
            return department;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        List<Employee> employees = new ArrayList<>();
        
        System.out.println("=== HR Letter Generation System ===\n");
        
        System.out.print("Enter number of employees: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        // Input employee data
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter Employee " + i + " Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Employee " + i + " Department: ");
            String department = scanner.nextLine();
            employees.add(new Employee(name, department));
        }
        
        System.out.println("\n--- Original Employee Names ---");
        employees.forEach(emp -> System.out.println(emp.getName() + " (" + emp.getDepartment() + ")"));
        
        System.out.println("\n--- HR Letter Format (UPPERCASE) ---");
        
        // Using method reference String::toUpperCase in stream
        List<String> uppercaseNames = employees.stream()
                                               .map(Employee::getName)
                                               .map(String::toUpperCase)
                                               .collect(Collectors.toList());
        
        // Display formatted letter
        System.out.println("\nDEAR EMPLOYEES,\n");
        System.out.println("This is to inform the following employees:");
        uppercaseNames.forEach(name -> System.out.println("- " + name));
        
        System.out.println("\nRegards,");
        System.out.println("HR Department");
        
        scanner.close();
    }
}
