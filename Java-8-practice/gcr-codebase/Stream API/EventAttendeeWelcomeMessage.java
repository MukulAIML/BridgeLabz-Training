import java.util.*;

public class EventAttendeeWelcomeMessage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of attendees: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        List<String> attendees = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter attendee name " + (i + 1) + ": ");
            attendees.add(sc.nextLine());
        }
        
        System.out.println("\n=== Welcome Messages ===");
        attendees.forEach(name -> System.out.println("Welcome to the event, " + name + "!"));
        
        sc.close();
    }
}
