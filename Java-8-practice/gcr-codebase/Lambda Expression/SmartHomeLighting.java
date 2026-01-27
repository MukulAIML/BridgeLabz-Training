import java.util.Scanner;

/**
 * Smart Home Lighting Automation using Lambda Expressions
 * Demonstrates dynamic light pattern execution based on different triggers
 */
public class SmartHomeLighting {
    
    // Functional interface for light behaviors
    @FunctionalInterface
    interface LightBehavior {
        void activate(String room);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Define light behaviors using lambda expressions
        LightBehavior motionActivated = (room) -> 
            System.out.println("Motion detected in " + room + " - Lights turned ON at 70% brightness");
        
        LightBehavior timeBasedMorning = (room) -> 
            System.out.println("Good Morning! " + room + " lights gradually brightening to 100%");
        
        LightBehavior timeBasedEvening = (room) -> 
            System.out.println("Evening mode activated in " + room + " - Warm lights at 50%");
        
        LightBehavior voiceCommand = (room) -> 
            System.out.println("Voice command received: " + room + " lights set to user preference");
        
        LightBehavior partyMode = (room) -> 
            System.out.println("Party mode activated in " + room + " - Color cycling enabled!");
        
        System.out.println("=== Smart Home Lighting System ===\n");
        
        System.out.print("Enter room name: ");
        String room = scanner.nextLine();
        
        System.out.println("\nSelect trigger type:");
        System.out.println("1. Motion Sensor");
        System.out.println("2. Morning Time");
        System.out.println("3. Evening Time");
        System.out.println("4. Voice Command");
        System.out.println("5. Party Mode");
        System.out.print("Enter choice (1-5): ");
        
        int choice = scanner.nextInt();
        
        System.out.println("\n--- Executing Light Pattern ---");
        
        // Execute appropriate behavior based on trigger
        switch (choice) {
            case 1:
                motionActivated.activate(room);
                break;
            case 2:
                timeBasedMorning.activate(room);
                break;
            case 3:
                timeBasedEvening.activate(room);
                break;
            case 4:
                voiceCommand.activate(room);
                break;
            case 5:
                partyMode.activate(room);
                break;
            default:
                System.out.println("Invalid choice!");
        }
        
        scanner.close();
    }
}
