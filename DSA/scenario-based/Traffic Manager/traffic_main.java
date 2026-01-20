// File: trafficmanager/TrafficMain.java
package trafficmanager;

import java.util.Scanner;

public class TrafficMain {
    private CircularLinkedList roundabout;
    private VehicleQueue waitingQueue;
    private Scanner scanner;
    
    public TrafficMain() {
        this.roundabout = new CircularLinkedList();
        this.scanner = new Scanner(System.in);
        
        System.out.print("Enter waiting queue capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        this.waitingQueue = new VehicleQueue(capacity);
    }
    
    public void showMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   SMART CITY ROUNDABOUT MANAGER       ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. Add vehicle to waiting queue");
        System.out.println("2. Move vehicle from queue to roundabout");
        System.out.println("3. Remove vehicle from roundabout");
        System.out.println("4. Display roundabout status");
        System.out.println("5. Display waiting queue");
        System.out.println("6. Display complete system status");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }
    
    public void addToQueue() {
        System.out.print("Enter vehicle number: ");
        String number = scanner.nextLine();
        System.out.print("Enter vehicle type (Car/Bike/Truck): ");
        String type = scanner.nextLine();
        
        Vehicle vehicle = new Vehicle(number, type);
        waitingQueue.enqueue(vehicle);
    }
    
    public void moveToRoundabout() {
        Vehicle vehicle = waitingQueue.dequeue();
        if (vehicle != null) {
            roundabout.addVehicle(vehicle);
        }
    }
    
    public void removeFromRoundabout() {
        System.out.print("Enter vehicle number to remove: ");
        String number = scanner.nextLine();
        roundabout.removeVehicle(number);
    }
    
    public void displayCompleteStatus() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║      COMPLETE SYSTEM STATUS           ║");
        System.out.println("╚════════════════════════════════════════╝");
        waitingQueue.displayQueue();
        roundabout.displayRoundabout();
    }
    
    public void run() {
        boolean running = true;
        
        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addToQueue();
                    break;
                case 2:
                    moveToRoundabout();
                    break;
                case 3:
                    removeFromRoundabout();
                    break;
                case 4:
                    roundabout.displayRoundabout();
                    break;
                case 5:
                    waitingQueue.displayQueue();
                    break;
                case 6:
                    displayCompleteStatus();
                    break;
                case 7:
                    System.out.println("Shutting down Traffic Management System...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
    
    public static void main(String[] args) {
        TrafficMain system = new TrafficMain();
        system.run();
    }
}