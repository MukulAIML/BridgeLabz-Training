// File: trafficmanager/Vehicle.java
package trafficmanager;

public class Vehicle {
    private String vehicleNumber;
    private String vehicleType;
    private Vehicle next;
    
    public Vehicle(String vehicleNumber, String vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.next = null;
    }
    
    // Getters and Setters
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public Vehicle getNext() {
        return next;
    }
    
    public void setNext(Vehicle next) {
        this.next = next;
    }
    
    @Override
    public String toString() {
        return "Vehicle[" + vehicleNumber + ", " + vehicleType + "]";
    }
}