// Problem 2: Create a @Todo Annotation for Pending Tasks

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

class Project {
    @Todo(task = "Fix login bug", assignedTo = "Alice", priority = "HIGH")
    public void featureOne() {}
    
    @Todo(task = "Add dark mode", assignedTo = "Bob")
    public void featureTwo() {}
    
    @Todo(task = "Optimize database", assignedTo = "Charlie", priority = "LOW")
    public void featureThree() {}
}

public class TodoAnnotation {
    public static void main(String[] args) {
        Method[] methods = Project.class.getDeclaredMethods();
        
        System.out.println("=== Pending Tasks ===");
        for (Method m : methods) {
            if (m.isAnnotationPresent(Todo.class)) {
                Todo todo = m.getAnnotation(Todo.class);
                System.out.println("\nTask: " + todo.task());
                System.out.println("Assigned To: " + todo.assignedTo());
                System.out.println("Priority: " + todo.priority());
            }
        }
    }
}
