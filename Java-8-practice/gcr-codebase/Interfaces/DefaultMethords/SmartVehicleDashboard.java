// Smart Vehicle Dashboard with Default Method
import java.util.Scanner;

interface Vehicle {
    void displaySpeed();
    
    default void displayBatteryPercentage() {
        System.out.println("Not applicable for this vehicle type");
    }
}

class PetrolCar implements Vehicle {
    private int speed;
    
    public PetrolCar(int speed) {
        this.speed = speed;
    }
    
    public void displaySpeed() {
        System.out.println("Petrol Car Speed: " + speed + " km/h");
    }
}

class ElectricCar implements Vehicle {
    private int speed;
    private int batteryPercentage;
    
    public ElectricCar(int speed, int batteryPercentage) {
        this.speed = speed;
        this.batteryPercentage = batteryPercentage;
    }
    
    public void displaySpeed() {
        System.out.println("Electric Car Speed: " + speed + " km/h");
    }
    
    @Override
    public void displayBatteryPercentage() {
        System.out.println("Battery Level: " + batteryPercentage + "%");
        if (batteryPercentage < 20) {
            System.out.println("WARNING: Low battery!");
        }
    }
}

class ElectricBike implements Vehicle {
    private int speed;
    private int batteryPercentage;
    
    public ElectricBike(int speed, int batteryPercentage) {
        this.speed = speed;
        this.batteryPercentage = batteryPercentage;
    }
    
    public void displaySpeed() {
        System.out.println("Electric Bike Speed: " + speed + " km/h");
    }
    
    @Override
    public void displayBatteryPercentage() {
        System.out.println("Battery Level: " + batteryPercentage + "%");
    }
}

public class SmartVehicleDashboard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter speed for petrol car (km/h): ");
        int petrolSpeed = scanner.nextInt();
        
        System.out.print("Enter speed for electric car (km/h): ");
        int electricCarSpeed = scanner.nextInt();
        
        System.out.print("Enter battery percentage for electric car: ");
        int carBattery = scanner.nextInt();
        
        System.out.print("Enter speed for electric bike (km/h): ");
        int bikeSpeed = scanner.nextInt();
        
        System.out.print("Enter battery percentage for electric bike: ");
        int bikeBattery = scanner.nextInt();
        
        Vehicle petrolCar = new PetrolCar(petrolSpeed);
        Vehicle electricCar = new ElectricCar(electricCarSpeed, carBattery);
        Vehicle electricBike = new ElectricBike(bikeSpeed, bikeBattery);
        
        System.out.println("\n--- Petrol Car Dashboard ---");
        petrolCar.displaySpeed();
        petrolCar.displayBatteryPercentage();
        
        System.out.println("\n--- Electric Car Dashboard ---");
        electricCar.displaySpeed();
        electricCar.displayBatteryPercentage();
        
        System.out.println("\n--- Electric Bike Dashboard ---");
        electricBike.displaySpeed();
        electricBike.displayBatteryPercentage();
        
        scanner.close();
    }
}
