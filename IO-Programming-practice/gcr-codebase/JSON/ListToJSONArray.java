import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Person {
    private String name;
    private int age;

    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

public class ListToJSONArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Person> people = new ArrayList<>();
        
        System.out.print("Enter number of persons: ");
        int count = sc.nextInt();
        sc.nextLine();
        
        for (int i = 0; i < count; i++) {
            System.out.println("\nPerson " + (i + 1) + ":");
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter age: ");
            int age = sc.nextInt();
            sc.nextLine();
            
            people.add(new Person(name, age));
        }
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(people);
            System.out.println("\nJSON Array:");
            System.out.println(json);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
}
