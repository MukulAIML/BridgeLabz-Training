// File: trafficmanager/VehicleQueue.java
package trafficmanager;

public class VehicleQueue {
    private Vehicle front;
    private Vehicle rear;
    private int size;
    private int capacity;
    
    public VehicleQueue(int capacity) {
        this.front = null;
        this.rear = null;
        this.size = 0;
        this.capacity = capacity;
    }
    
    // Enqueue - Add vehicle to waiting queue
    public boolean enqueue(Vehicle vehicle) {
        if (isFull()) {
            System.out.println("QUEUE OVERFLOW! Cannot add vehicle " + vehicle.getVehicleNumber());
            System.out.println("Waiting queue is full (capacity: " + capacity + ")");
            return false;
        }
        
        if (rear == null) {
            front = rear = vehicle;
        } else {
            rear.setNext(vehicle);
            rear = vehicle;
        }
        rear.setNext(null);
        size++;
        System.out.println("Vehicle " + vehicle.getVehicleNumber() + " added to waiting queue.");
        return true;
    }
    
    // Dequeue - Remove vehicle from waiting queue
    public Vehicle dequeue() {
        if (isEmpty()) {
            System.out.println("QUEUE UNDERFLOW! No vehicles waiting.");
            return null;
        }
        
        Vehicle temp = front;
        front = front.getNext();
        
        if (front == null) {
            rear = null;
        }
        
        size--;
        System.out.println("Vehicle " + temp.getVehicleNumber() + " removed from waiting queue.");
        return temp;
    }
    
    // Display waiting queue
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Waiting queue is empty.");
            return;
        }
        
        System.out.println("\n===== Waiting Queue Status =====");
        System.out.println("Vehicles waiting: " + size + "/" + capacity);
        System.out.print("Queue: ");
        
        Vehicle temp = front;
        while (temp != null) {
            System.out.print(temp.toString());
            if (temp.getNext() != null) {
                System.out.print(" <- ");
            }
            temp = temp.getNext();
        }
        System.out.println("\n================================\n");
    }
    
    public boolean isEmpty() {
        return front == null;
    }
    
    public boolean isFull() {
        return size >= capacity;
    }
    
    public int getSize() {
        return size;
    }
    
    public int getCapacity() {
        return capacity;
    }
}