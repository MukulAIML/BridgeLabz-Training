import java.util.*;

public class FlightBooking {
    static String[][] flights = {
        {"FL101", "New York", "London", "650"},
        {"FL102", "Paris", "Tokyo", "890"},
        {"FL103", "Dubai", "Sydney", "720"}
    };
    
    static List<String> bookings = new ArrayList<>();
    
    static void searchFlights(String city) {
        System.out.println("\n--- Available Flights ---");
        boolean found = false;
        for (String[] f : flights) {
            if (f[1].toLowerCase().contains(city.toLowerCase()) || 
                f[2].toLowerCase().contains(city.toLowerCase())) {
                System.out.println(f[0] + ": " + f[1] + " -> " + f[2] + " ($" + f[3] + ")");
                found = true;
            }
        }
        if (!found) System.out.println("No flights found for: " + city);
    }
    
    static void bookFlight(String name, String id) {
        for (String[] f : flights) {
            if (f[0].equalsIgnoreCase(id)) {
                bookings.add(name + " booked " + id);
                System.out.println("✓ Booking confirmed for " + name);
                return;
            }
        }
        System.out.println("✗ Flight ID not found");
    }
    
    static void viewBookings() {
        System.out.println("\n--- All Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (String b : bookings) System.out.println(b);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Flight Booking System ===");
        
        while (true) {
            System.out.println("\n1. Search Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            if (choice == 1) {
                System.out.print("Enter city (origin/destination): ");
                String city = sc.nextLine();
                searchFlights(city);
                
            } else if (choice == 2) {
                System.out.print("Enter passenger name: ");
                String name = sc.nextLine();
                System.out.print("Enter flight ID: ");
                String id = sc.nextLine();
                bookFlight(name, id);
                
            } else if (choice == 3) {
                viewBookings();
                
            } else if (choice == 4) {
                System.out.println("Thank you for using Flight Booking System!");
                break;
                
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        sc.close();
    }
}