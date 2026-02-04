import java.util.Scanner;

class TicketCounter {
    private int availableTickets;
    
    public TicketCounter(int totalTickets) {
        this.availableTickets = totalTickets;
    }
    
    public void bookTickets(String customerName, int requestedTickets) {
        System.out.println("[" + customerName + "] Requesting " + requestedTickets + " tickets");
        
        if (availableTickets >= requestedTickets) {
            System.out.println("[" + customerName + "] Processing booking...");
            try {
                Thread.sleep(200); // Simulate processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            availableTickets -= requestedTickets;
            System.out.println("✓ [" + customerName + "] Booking successful! Tickets: " + requestedTickets + ", Remaining: " + availableTickets);
        } else {
            System.out.println("✗ [" + customerName + "] Booking failed! Only " + availableTickets + " tickets available");
        }
    }
    
    public int getAvailableTickets() {
        return availableTickets;
    }
}

class BookingAgent implements Runnable {
    private TicketCounter counter;
    private String customerName;
    private int requestedTickets;
    
    public BookingAgent(TicketCounter counter, String customerName, int requestedTickets) {
        this.counter = counter;
        this.customerName = customerName;
        this.requestedTickets = requestedTickets;
    }
    
    @Override
    public void run() {
        counter.bookTickets(customerName, requestedTickets);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Ticket Booking System ===");
        System.out.print("Enter total available tickets: ");
        int totalTickets = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        TicketCounter counter = new TicketCounter(totalTickets);
        
        System.out.print("Enter number of customers: ");
        int numCustomers = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Thread[] threads = new Thread[numCustomers];
        
        for (int i = 0; i < numCustomers; i++) {
            System.out.print("Enter customer name " + (i + 1) + ": ");
            String customerName = scanner.nextLine();
            System.out.print("Enter tickets requested by " + customerName + ": ");
            int requestedTickets = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            threads[i] = new Thread(new BookingAgent(counter, customerName, requestedTickets));
        }
        
        System.out.println("\n--- Booking process started ---\n");
        
        // Start all booking threads
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Wait for all bookings to complete
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        
        System.out.println("\n=== Final tickets remaining: " + counter.getAvailableTickets() + " ===");
        System.out.println("Note: Race conditions may occur without synchronization");
        
        scanner.close();
    }
}
