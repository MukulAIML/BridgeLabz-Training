import java.util.*;

public class EmailNotifications {
    
    // Simulated email sending method
    public static void sendEmailNotification(String email) {
        System.out.println("Sending notification to: " + email);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of users: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        List<String> emails = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter email " + (i + 1) + ": ");
            emails.add(sc.nextLine());
        }
        
        System.out.println("\n=== Sending Email Notifications ===");
        emails.forEach(email -> sendEmailNotification(email));
        
        System.out.println("\nAll notifications sent successfully!");
        
        sc.close();
    }
}
