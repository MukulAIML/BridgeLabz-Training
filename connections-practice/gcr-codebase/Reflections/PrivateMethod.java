import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * BASIC LEVEL - Problem 3: Invoke Private Method
 * Call private methods using Reflection
 */
class Calculator {
    private int multiply(int a, int b) {
        return a * b;
    }
}

public class PrivateMethod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            Calculator calc = new Calculator();
            
            System.out.print("Enter first number: ");
            int num1 = sc.nextInt();
            
            System.out.print("Enter second number: ");
            int num2 = sc.nextInt();
            
            Method method = Calculator.class.getDeclaredMethod("multiply", int.class, int.class);
            method.setAccessible(true);
            
            int result = (int) method.invoke(calc, num1, num2);
            System.out.println("Result: " + result);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
