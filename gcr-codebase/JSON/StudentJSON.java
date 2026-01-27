import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private List<String> subjects;

    public Student() {}

    public Student(String name, int age, List<String> subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public List<String> getSubjects() { return subjects; }
    public void setSubjects(List<String> subjects) { this.subjects = subjects; }
}

public class StudentJSON {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter student age: ");
        int age = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter subjects (comma-separated): ");
        String subjectsInput = sc.nextLine();
        List<String> subjects = Arrays.asList(subjectsInput.split(","));
        
        Student student = new Student(name, age, subjects);
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
            System.out.println("\nStudent JSON:");
            System.out.println(json);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
}
