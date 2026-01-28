// Data Serialization for Backup with Marker Interface
import java.io.*;
import java.util.Scanner;

interface BackupSerializable {
    // Marker interface - no methods
}

class UserData implements BackupSerializable, Serializable {
    private String username;
    private String email;
    
    public UserData(String username, String email) {
        this.username = username;
        this.email = email;
    }
    
    public String toString() {
        return "User: " + username + ", Email: " + email;
    }
}

class SystemConfig implements BackupSerializable, Serializable {
    private String configName;
    private String value;
    
    public SystemConfig(String configName, String value) {
        this.configName = configName;
        this.value = value;
    }
    
    public String toString() {
        return "Config: " + configName + " = " + value;
    }
}

class TempData {
    private String data;
    
    public TempData(String data) {
        this.data = data;
    }
}

public class DataSerializationBackup {
    public static void backupObject(Object obj, String filename) {
        if (obj instanceof BackupSerializable) {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(filename))) {
                oos.writeObject(obj);
                System.out.println("Backup successful: " + filename);
            } catch (IOException e) {
                System.out.println("Backup failed: " + e.getMessage());
            }
        } else {
            System.out.println("Object not marked for backup serialization");
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        UserData userData = new UserData(username, email);
        SystemConfig config = new SystemConfig("theme", "dark");
        TempData tempData = new TempData("temporary");
        
        System.out.println("\nBacking up objects:");
        backupObject(userData, "user_backup.ser");
        backupObject(config, "config_backup.ser");
        backupObject(tempData, "temp_backup.ser");
        
        scanner.close();
    }
}
