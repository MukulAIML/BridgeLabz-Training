import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String department;
    private double salary;
    
    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public double getSalary() {
        return salary;
    }
    
    @Override
    public String toString() {
        return name + " (" + department + ", $" + salary + ")";
    }
}

public class EmployeeSalaryCategorization {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();
        
        System.out.print("Enter number of employees: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();
            System.out.print("Enter department: ");
            String department = scanner.nextLine();
            System.out.print("Enter salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();
            employees.add(new Employee(name, department, salary));
        }
        
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ));
        
        System.out.println("\nAverage Salary by Department:");
        avgSalaryByDept.forEach((dept, avgSalary) -> 
            System.out.printf("%s: $%.2f%n", dept, avgSalary)
        );
        
        scanner.close();
    }
}
