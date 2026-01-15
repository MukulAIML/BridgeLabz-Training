import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * ADVANCED LEVEL - Problem 5: Method Execution Timing
 * Measure execution time of methods dynamically using Reflection
 */
class TestClass {
    
    public void fastMethod() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }
        System.out.println("Fast method executed - Sum: " + sum);
    }
    
    public void slowMethod() {
        try {
            Thread.sleep(500);
            System.out.println("Slow method executed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void mediumMethod() {
        int result = 1;
        for (int i = 1; i <= 100000; i++) {
            result = (result * i) % 1000000;
        }
        System.out.println("Medium method executed - Result: " + result);
    }
    
    public void printMessage(String message) {
        System.out.println("Message: " + message);
    }
}

public class MethodTimingAnalyzer {
    
    public static void measureMethodTime(Object obj, String methodName, Object... args) {
        try {
            Class<?>[] paramTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                paramTypes[i] = args[i].getClass();
            }
            
            Method method;
            if (args.length == 0) {
                method = obj.getClass().getMethod(methodName);
            } else {
                method = obj.getClass().getMethod(methodName, paramTypes);
            }
            
            System.out.println("\n===== EXECUTING METHOD: " + methodName + " =====");
            
            long startTime = System.nanoTime();
            method.invoke(obj, args);
            long endTime = System.nanoTime();
            
            long durationNano = endTime - startTime;
            double durationMilli = durationNano / 1_000_000.0;
            
            System.out.println("\n[TIMING] Execution time: " + durationMilli + " ms");
            System.out.println("[TIMING] Execution time: " + durationNano + " ns");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TestClass test = new TestClass();
        
        while (true) {
            System.out.println("\n===== METHOD TIMING ANALYZER =====");
            System.out.println("1. fastMethod");
            System.out.println("2. slowMethod");
            System.out.println("3. mediumMethod");
            System.out.println("4. printMessage (with parameter)");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    measureMethodTime(test, "fastMethod");
                    break;
                case 2:
                    measureMethodTime(test, "slowMethod");
                    break;
                case 3:
                    measureMethodTime(test, "mediumMethod");
                    break;
                case 4:
                    System.out.print("Enter message: ");
                    String msg = sc.nextLine();
                    measureMethodTime(test, "printMessage", msg);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
