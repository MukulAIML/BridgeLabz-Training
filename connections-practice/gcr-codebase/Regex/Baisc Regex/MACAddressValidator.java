import java.util.Scanner;
import java.util.regex.Pattern;

public class MACAddressValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== MAC Address Validator ===");
        System.out.println("Format: XX:XX:XX:XX:XX:XX or XX-XX-XX-XX-XX-XX");
        System.out.print("Enter MAC address: ");
        String mac = sc.nextLine();
        
        // Pattern: MAC address with : or - separator
        String regex = "^([0-9A-Fa-f]{2}[:-]){5}[0-9A-Fa-f]{2}$";
        boolean isValid = Pattern.matches(regex, mac);
        
        if (isValid) {
            System.out.println("✅ Valid MAC address!");
        } else {
            System.out.println("❌ Invalid MAC address!");
        }
        
        sc.close();
    }
}