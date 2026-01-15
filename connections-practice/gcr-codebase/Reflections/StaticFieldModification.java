import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * INTERMEDIATE LEVEL - Problem 3: Access and Modify Static Fields
 * Modify private static fields using Reflection
 */
class Configuration {
    private static String API_KEY = "default_key_12345";
    private static String DATABASE_URL = "localhost:3306";
    
    public static void displayConfig() {
        System.out.println("API_KEY: " + API_KEY);
        System.out.println("DATABASE_URL: " + DATABASE_URL);
    }
}

public class StaticFieldModification {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.println("===== ORIGINAL CONFIGURATION =====");
            Configuration.displayConfig();
            
            Field apiKeyField = Configuration.class.getDeclaredField("API_KEY");
            apiKeyField.setAccessible(true);
            
            System.out.print("\nEnter new API_KEY: ");
            String newKey = sc.nextLine();
            
            apiKeyField.set(null, newKey);
            
            System.out.println("\n===== MODIFIED CONFIGURATION =====");
            Configuration.displayConfig();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
