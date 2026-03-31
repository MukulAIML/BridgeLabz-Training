import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;

class Car {
    private String brand;
    private String model;
    private int year;
    private double price;

    public Car() {}

    public Car(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

public class CarJSON {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter car brand: ");
        String brand = sc.nextLine();
        
        System.out.print("Enter car model: ");
        String model = sc.nextLine();
        
        System.out.print("Enter car year: ");
        int year = sc.nextInt();
        
        System.out.print("Enter car price: ");
        double price = sc.nextDouble();
        
        Car car = new Car(brand, model, year, price);
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);
            System.out.println("\nCar JSON:");
            System.out.println(json);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
}
