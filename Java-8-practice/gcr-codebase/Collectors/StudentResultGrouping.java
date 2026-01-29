import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String name;
    private int grade;
    
    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
    
    public String getName() {
        return name;
    }
    
    public int getGrade() {
        return grade;
    }
    
    @Override
    public String toString() {
        return name + " (Grade " + grade + ")";
    }
}

public class StudentResultGrouping {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter grade level: ");
            int grade = scanner.nextInt();
            scanner.nextLine();
            students.add(new Student(name, grade));
        }
        
        Map<Integer, List<String>> groupedByGrade = students.stream()
            .collect(Collectors.groupingBy(
                Student::getGrade,
                Collectors.mapping(Student::getName, Collectors.toList())
            ));
        
        System.out.println("\nStudents grouped by grade:");
        groupedByGrade.forEach((grade, names) -> 
            System.out.println("Grade " + grade + ": " + names)
        );
        
        scanner.close();
    }
}
