// Problem 1: Create an Annotation to Mark Important Methods

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
    String level() default "HIGH";
}

class MyService {
    @ImportantMethod(level = "HIGH")
    public void criticalOperation() {
        System.out.println("Critical task executed");
    }
    
    @ImportantMethod(level = "MEDIUM")
    public void normalOperation() {
        System.out.println("Normal task executed");
    }
    
    public void regularTask() {
        System.out.println("Regular task");
    }
}

public class ImportantMethod {
    public static void main(String[] args) {
        Method[] methods = MyService.class.getDeclaredMethods();
        
        for (Method m : methods) {
            if (m.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod annotation = m.getAnnotation(ImportantMethod.class);
                System.out.println("Method: " + m.getName() + 
                                 " | Level: " + annotation.level());
            }
        }
    }
}
