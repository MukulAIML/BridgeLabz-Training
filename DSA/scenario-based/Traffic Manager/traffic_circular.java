// File: trafficmanager/CircularLinkedList.java
package trafficmanager;

public class CircularLinkedList {
    private Vehicle head;
    private int size;
    
    public CircularLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    // Add vehicle to roundabout
    public void addVehicle(Vehicle vehicle) {
        if (head == null) {
            head = vehicle;
            head.setNext(head); // Point to itself
        } else {
            Vehicle temp = head;
            while (temp.getNext() != head) {
                temp = temp.getNext();
            }
            temp.setNext(vehicle);
            vehicle.setNext(head);
        }
        size++;
        System.out.println("Vehicle " + vehicle.getVehicleNumber() + " entered the roundabout.");
    }
    
    // Remove vehicle from roundabout
    public boolean removeVehicle(String vehicleNumber) {
        if (head == null) {
            System.out.println("Roundabout is empty!");
            return false;
        }
        
        Vehicle current = head;
        Vehicle previous = null;
        
        // Find the last node
        Vehicle last = head;
        while (last.getNext() != head) {
            last = last.getNext();
        }
        
        // If only one node
        if (head.getNext() == head && head.getVehicleNumber().equals(vehicleNumber)) {
            System.out.println("Vehicle " + head.getVehicleNumber() + " exited the roundabout.");
            head = null;
            size--;
            return true;
        }
        
        // If head needs to be removed
        if (head.getVehicleNumber().equals(vehicleNumber)) {
            last.setNext(head.getNext());
            System.out.println("Vehicle " + head.getVehicleNumber() + " exited the roundabout.");
            head = head.getNext();
            size--;
            return true;
        }
        
        // Search for the vehicle
        previous = head;
        current = head.getNext();
        
        while (current != head) {
            if (current.getVehicleNumber().equals(vehicleNumber)) {
                previous.setNext(current.getNext());
                System.out.println("Vehicle " + current.getVehicleNumber() + " exited the roundabout.");
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        
        System.out.println("Vehicle " + vehicleNumber + " not found in roundabout.");
        return false;
    }
    
    // Display roundabout state
    public void displayRoundabout() {
        if (head == null) {
            System.out.println("Roundabout is currently empty.");
            return;
        }
        
        System.out.println("\n===== Roundabout Status =====");
        System.out.println("Total vehicles in roundabout: " + size);
        System.out.print("Vehicles: ");
        
        Vehicle temp = head;
        do {
            System.out.print(temp.toString() + " -> ");
            temp = temp.getNext();
        } while (temp != head);
        
        System.out.println("(back to start)");
        System.out.println("=============================\n");
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int getSize() {
        return size;
    }
}