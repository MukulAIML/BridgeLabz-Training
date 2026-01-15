import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * INTERMEDIATE LEVEL - Problem 2: Retrieve Annotations at Runtime
 * Create and retrieve custom annotations using Reflection
 */
@Retention(RetentionPolicy.RUNTIME)
@interface Author {
    String name();
    String date() default "Unknown";
}

@Author(name = "Alice Johnson", date = "2024-01-15")
class Book {
    @Author(name = "Bob Smith")
    public void read() {
        System.out.println("Reading book...");
    }
    
    @Author(name = "Carol White", date = "2024-02-20")
    public void write() {
        System.out.println("Writing book...");
    }
}

public class AnnotationRetrieval {
    public static void main(String[] args) {
        try {
            Class<?> cls = Book.class;
            
            // Class level annotation
            if (cls.isAnnotationPresent(Author.class)) {
                Author author = cls.getAnnotation(Author.class);
                System.out.println("Class Author: " + author.name() + ", Date: " + author.date());
            }
            
            // Method level annotations
            System.out.println("\nMethod Annotations:");
            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Author.class)) {
                    Author author = method.getAnnotation(Author.class);
                    System.out.println(method.getName() + " - Author: " + author.name() + ", Date: " + author.date());
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
