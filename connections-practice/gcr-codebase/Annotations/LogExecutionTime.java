// Problem 3: Create an Annotation for Logging Method Execution Time

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {}

class Calculator {
    @LogExecutionTime
    public void fastMethod() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }
        System.out.println("Fast method completed");
    }
    
    @LogExecutionTime
    public void slowMethod() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("Slow method completed");
    }
}

public class LogExecutionTime {
    public static void main(String[] args) throws Exception {
        Calculator calc = new Calculator();
        
        executeWithTimer(calc, "fastMethod");
        executeWithTimer(calc, "slowMethod");
    }
    
    static void executeWithTimer(Object obj, String methodName) throws Exception {
        Method method = obj.getClass().getMethod(methodName);
        
        if (method.isAnnotationPresent(LogExecutionTime.class)) {
            long start = System.nanoTime();
            method.invoke(obj);
            long end = System.nanoTime();
            
            System.out.println("Execution time: " + (end - start) / 1000000.0 + " ms\n");
        }
    }
}
