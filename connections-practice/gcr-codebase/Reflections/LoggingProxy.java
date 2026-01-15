import java.lang.reflect.*;
import java.util.Scanner;

/**
 * ADVANCED LEVEL - Problem 3: Custom Logging Proxy
 * Intercept method calls and log them before execution
 */
interface Greeting {
    void sayHello(String name);
    String getMessage();
    void greet(String name, String time);
}

class GreetingImpl implements Greeting {
    public void sayHello(String name) {
        System.out.println("Hello, " + name + "!");
    }
    
    public String getMessage() {
        return "Welcome to Java Reflection!";
    }
    
    public void greet(String name, String time) {
        System.out.println("Good " + time + ", " + name + "!");
    }
}

public class LoggingProxy {
    
    public static <T> T createLoggingProxy(Class<T> interfaceClass, T implementation) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class[]{interfaceClass},
            new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("\n[LOG] Method called: " + method.getName());
                    
                    if (args != null) {
                        System.out.print("[LOG] Arguments: ");
                        for (Object arg : args) {
                            System.out.print(arg + " ");
                        }
                        System.out.println();
                    }
                    
                    long start = System.currentTimeMillis();
                    Object result = method.invoke(implementation, args);
                    long end = System.currentTimeMillis();
                    
                    System.out.println("[LOG] Execution time: " + (end - start) + "ms");
                    System.out.println("[LOG] Method completed\n");
                    
                    return result;
                }
            }
        );
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Greeting original = new GreetingImpl();
        Greeting proxy = createLoggingProxy(Greeting.class, original);
        
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        
        proxy.sayHello(name);
        
        String msg = proxy.getMessage();
        System.out.println("Received: " + msg);
        
        System.out.print("Enter time of day (morning/afternoon/evening): ");
        String time = sc.nextLine();
        
        proxy.greet(name, time);
        
        sc.close();
    }
}
