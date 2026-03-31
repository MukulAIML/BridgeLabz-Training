// Problem 5: Implement a Role-Based Access Control with @RoleAllowed

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed {
    String value();
}

class SecureSystem {
    @RoleAllowed("ADMIN")
    public void deleteDatabase() {
        System.out.println("Database deleted successfully!");
    }
    
    @RoleAllowed("ADMIN")
    public void viewLogs() {
        System.out.println("Viewing system logs...");
    }
}

public class RoleAllowed {
    static String currentUserRole = "USER"; // Simulated user role
    
    public static void main(String[] args) throws Exception {
        SecureSystem system = new SecureSystem();
        
        System.out.println("Current User Role: " + currentUserRole + "\n");
        
        invokeWithCheck(system, "deleteDatabase");
        
        currentUserRole = "ADMIN";
        System.out.println("\nCurrent User Role: " + currentUserRole + "\n");
        
        invokeWithCheck(system, "viewLogs");
    }
    
    static void invokeWithCheck(Object obj, String methodName) throws Exception {
        Method method = obj.getClass().getMethod(methodName);
        
        if (method.isAnnotationPresent(RoleAllowed.class)) {
            RoleAllowed roleAnnotation = method.getAnnotation(RoleAllowed.class);
            
            if (currentUserRole.equals(roleAnnotation.value())) {
                method.invoke(obj);
            } else {
                System.out.println("Access Denied! Only " + roleAnnotation.value() + " can access this.");
            }
        }
    }
}
