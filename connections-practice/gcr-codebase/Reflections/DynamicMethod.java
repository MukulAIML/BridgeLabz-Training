import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * INTERMEDIATE LEVEL - Problem 1: Dynamic Method Invocation
 * Call methods dynamically based on user input
 */
class MathOperations {
    public int add(int a, int b) {
        return a + b;
    }
    
    public int subtract(int a, int b) {
        return a - b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public int divide(int a, int b) {
        return b != 0 ? a / b : 0;
    }
}

public class DynamicMethod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.println("Available methods: add, subtract, multiply, divide");
            System.out.print("Enter method name: ");
            String methodName = sc.next();
            
            System.out.print("Enter first number: ");
            int a = sc.nextInt();
            
            System.out.print("Enter second number: ");
            int b = sc.nextInt();
            
            MathOperations math = new MathOperations();
            Method method = MathOperations.class.getMethod(methodName, int.class, int.class);
            
            int result = (int) method.invoke(math, a, b);
            System.out.println("Result: " + result);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
