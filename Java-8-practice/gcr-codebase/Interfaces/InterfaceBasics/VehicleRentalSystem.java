// Multi-Vehicle Rental System
import java.util.Scanner;

interface Rentable {
    void rent(String customerName);
    void returnVehicle();
}

class Car implements Rentable {
    private String model;
    private boolean isRented;
    
    public Car(String model) {
        this.model = model;
        this.isRented = false;
    }
    
    public void rent(String customerName) {
        if (!isRented) {
            isRented = true;
            System.out.println("Car " + model + " rented to " + customerName);
        } else {
            System.out.println("Car already rented");
        }
    }
    
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println("Car " + model + " returned successfully");
        } else {
            System.out.println("Car was not rented");
        }
    }
}

class Bike implements Rentable {
    private String type;
    private boolean isRented;
    
    public Bike(String type) {
        this.type = type;
        this.isRented = false;
    }
    
    public void rent(String customerName) {
        if (!isRented) {
            isRented = true;
            System.out.println("Bike " + type + " rented to " + customerName);
        } else {
            System.out.println("Bike already rented");
        }
    }
    
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println("Bike " + type + " returned successfully");
        } else {
            System.out.println("Bike was not rented");
        }
    }
}

class Bus implements Rentable {
    private int capacity;
    private boolean isRented;
    
    public Bus(int capacity) {
        this.capacity = capacity;
        this.isRented = false;
    }
    
    public void rent(String customerName) {
        if (!isRented) {
            isRented = true;
            System.out.println(capacity + "-seater Bus rented to " + customerName);
        } else {
            System.out.println("Bus already rented");
        }
    }
    
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println(capacity + "-seater Bus returned successfully");
        } else {
            System.out.println("Bus was not rented");
        }
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Rentable car = new Car("Honda City");
        Rentable bike = new Bike("Yamaha R15");
        Rentable bus = new Bus(50);
        
        System.out.print("Enter customer name: ");
        String customer = scanner.nextLine();
        
        car.rent(customer);
        bike.rent(customer);
        bus.rent(customer);
        
        System.out.println();
        
        car.returnVehicle();
        bike.returnVehicle();
        bus.returnVehicle();
        
        scanner.close();
    }
}
