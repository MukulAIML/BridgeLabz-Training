import java.util.*;
import java.util.function.Predicate;

/**
 * Hospital Notification Filtering using Lambda with Predicate
 * Demonstrates filtering alerts based on user preferences
 */
public class NotificationFiltering {
    
    static class Alert {
        String type;
        String message;
        int priority;
        
        public Alert(String type, String message, int priority) {
            this.type = type;
            this.message = message;
            this.priority = priority;
        }
        
        @Override
        public String toString() {
            return String.format("[%s] Priority %d: %s", type, priority, message);
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create sample alerts
        List<Alert> alerts = new ArrayList<>();
        alerts.add(new Alert("EMERGENCY", "Patient in Room 301 needs immediate attention", 5));
        alerts.add(new Alert("MEDICATION", "Medication due for Patient ID 1234", 3));
        alerts.add(new Alert("APPOINTMENT", "Dr. Smith appointment in 30 minutes", 2));
        alerts.add(new Alert("EMERGENCY", "Code Blue in ICU Ward", 5));
        alerts.add(new Alert("LAB_RESULT", "Lab results ready for Patient ID 5678", 3));
        alerts.add(new Alert("APPOINTMENT", "Patient discharge paperwork ready", 2));
        alerts.add(new Alert("MEDICATION", "Pharmacy refill needed", 1));
        
        System.out.println("=== Hospital Alert Notification System ===\n");
        
        System.out.println("All Alerts:");
        alerts.forEach(System.out::println);
        
        System.out.println("\n--- Filter Preferences ---");
        System.out.println("1. Show only EMERGENCY alerts");
        System.out.println("2. Show only MEDICATION alerts");
        System.out.println("3. Show only High Priority alerts (Priority >= 4)");
        System.out.println("4. Show only Medium Priority alerts (Priority = 3)");
        System.out.println("5. Show EMERGENCY and MEDICATION alerts");
        System.out.print("Enter choice (1-5): ");
        
        int choice = scanner.nextInt();
        
        System.out.println("\n--- Filtered Alerts ---");
        
        // Define predicates using lambda expressions
        Predicate<Alert> emergencyOnly = alert -> alert.type.equals("EMERGENCY");
        Predicate<Alert> medicationOnly = alert -> alert.type.equals("MEDICATION");
        Predicate<Alert> highPriority = alert -> alert.priority >= 4;
        Predicate<Alert> mediumPriority = alert -> alert.priority == 3;
        Predicate<Alert> emergencyOrMedication = alert -> 
            alert.type.equals("EMERGENCY") || alert.type.equals("MEDICATION");
        
        // Filter based on user preference
        List<Alert> filteredAlerts = new ArrayList<>();
        switch (choice) {
            case 1:
                filteredAlerts = filterAlerts(alerts, emergencyOnly);
                break;
            case 2:
                filteredAlerts = filterAlerts(alerts, medicationOnly);
                break;
            case 3:
                filteredAlerts = filterAlerts(alerts, highPriority);
                break;
            case 4:
                filteredAlerts = filterAlerts(alerts, mediumPriority);
                break;
            case 5:
                filteredAlerts = filterAlerts(alerts, emergencyOrMedication);
                break;
            default:
                System.out.println("Invalid choice!");
                scanner.close();
                return;
        }
        
        if (filteredAlerts.isEmpty()) {
            System.out.println("No alerts match your filter criteria.");
        } else {
            filteredAlerts.forEach(System.out::println);
        }
        
        scanner.close();
    }
    
    // Helper method to filter alerts using predicate
    private static List<Alert> filterAlerts(List<Alert> alerts, Predicate<Alert> predicate) {
        List<Alert> filtered = new ArrayList<>();
        for (Alert alert : alerts) {
            if (predicate.test(alert)) {
                filtered.add(alert);
            }
        }
        return filtered;
    }
}
