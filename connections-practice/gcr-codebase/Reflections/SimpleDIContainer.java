import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * ADVANCED LEVEL - Problem 4: Dependency Injection
 * Simple DI container that injects dependencies with @Inject annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@interface Inject {}

class DatabaseService {
    public void connect() {
        System.out.println("[DB] Database connected successfully");
    }
    
    public void disconnect() {
        System.out.println("[DB] Database disconnected");
    }
}

class LoggerService {
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

class EmailService {
    public void sendEmail(String to, String subject) {
        System.out.println("[EMAIL] Sending email to: " + to);
        System.out.println("[EMAIL] Subject: " + subject);
    }
}

class UserService {
    @Inject
    private DatabaseService db;
    
    @Inject
    private LoggerService logger;
    
    @Inject
    private EmailService email;
    
    public void createUser(String username, String emailAddr) {
        logger.log("Creating user: " + username);
        db.connect();
        System.out.println("User " + username + " created in database");
        email.sendEmail(emailAddr, "Welcome to our platform!");
        db.disconnect();
        logger.log("User creation completed");
    }
}

public class SimpleDIContainer {
    
    public static void inject(Object obj) {
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    Object dependency = field.getType().getDeclaredConstructor().newInstance();
                    field.set(obj, dependency);
                    System.out.println("[DI] Injected: " + field.getType().getSimpleName());
                }
            }
        } catch (Exception e) {
            System.out.println("Injection error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("===== DEPENDENCY INJECTION DEMO =====\n");
        
        UserService userService = new UserService();
        
        System.out.println("Injecting dependencies...");
        inject(userService);
        
        System.out.println("\n===== EXECUTING USER SERVICE =====\n");
        userService.createUser("john_doe", "john@example.com");
    }
}
