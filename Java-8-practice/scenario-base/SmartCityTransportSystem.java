import java.util.*;
import java.util.stream.*;

// ============ INTERFACES SECTION ============

// Functional Interface for calculating fare
@FunctionalInterface
interface FareCalculator {
    double calculateFare(double distance, int passengers);
}

// Marker interface for emergency services
interface EmergencyService {
    // marker interface - no methods needed
}

// Main transport service interface
interface TransportService {
    String getServiceName();
    double getFare();
    String getRoute();
    String getDepartureTime();
    
    // Default method - prints service details
    default void printServiceDetails() {
        System.out.println(getServiceName() + " | Route: " + getRoute() + 
                         " | Fare: Rs." + getFare() + " | Time: " + getDepartureTime());
    }
    
    // Static method
    static String getSystemInfo() {
        return "Smart City Transport System v1.0";
    }
}

// Using GeoUtils interface with static method for distance calculation
interface GeoUtils {
    static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Formula to calculate distance between two coordinates
        // Haversine formula
        double R = 6371; 
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        return 2 * R * Math.asin(Math.sqrt(a));
    }
}


// ============ SERVICE CLASSES ============

// Bus Service implementation
class BusService implements TransportService {
    private String name;
    private double fare;
    private String route;
    private String time;
    
    public BusService(String name, double fare, String route, String time) {
        this.name = name;
        this.fare = fare;
        this.route = route;
        this.time = time;
    }
    
    @Override
    public String getServiceName() { 
        return name; 
    }
    
    @Override
    public double getFare() { 
        return fare; 
    }
    
    @Override
    public String getRoute() { 
        return route; 
    }
    
    @Override
    public String getDepartureTime() { 
        return time; 
    }
}

// Creating MetroService class similar to BusService
class MetroService implements TransportService {
    private String name;
    private double fare;
    private String route;
    private String time;
    
    public MetroService(String name, double fare, String route, String time) {
        this.name = name;
        this.fare = fare;
        this.route = route;
        this.time = time;
    }
    
    // Implementing all required methods from interface
    @Override
    public String getServiceName() { 
        return name; 
    }
    
    @Override
    public double getFare() { 
        return fare; 
    }
    
    @Override
    public String getRoute() { 
        return route; 
    }
    
    @Override
    public String getDepartureTime() { 
        return time; 
    }
}

// Creating TaxiService class
class TaxiService implements TransportService {
    private String name;
    private double fare;
    private String route;
    private String time;
    
    // Constructor
    public TaxiService(String name, double fare, String route, String time) {
        this.name = name;
        this.fare = fare;
        this.route = route;
        this.time = time;
    }
    
    // Override interface methods
    @Override
    public String getServiceName() { 
        return name; 
    }
    
    @Override
    public double getFare() { 
        return fare; 
    }
    
    @Override
    public String getRoute() { 
        return route; 
    }
    
    @Override
    public String getDepartureTime() { 
        return time; 
    }
}

// Ambulance service - emergency vehicle
class AmbulanceService implements TransportService, EmergencyService {
    private String name;
    private String route;
    
    public AmbulanceService(String name, String route) {
        this.name = name;
        this.route = route;
    }
    
    @Override
    public String getServiceName() { 
        return name; 
    }
    
    @Override
    public double getFare() { 
        return 0.0; // free service
    }
    
    @Override
    public String getRoute() { 
        return route; 
    }
    
    @Override
    public String getDepartureTime() { 
        return "IMMEDIATE"; 
    }
    
    // Override default method for custom output
    @Override
    public void printServiceDetails() {
        System.out.println("[EMERGENCY] " + name + " | Route: " + route + " | Priority Access");
    }
}


// ============ PASSENGER CLASS ============

class Passenger {
    private String name;
    private String route;
    private double fare;
    private boolean isPeakTime;
    
    // Constructor
    public Passenger(String name, String route, double fare, boolean isPeakTime) {
        this.name = name;
        this.route = route;
        this.fare = fare;
        this.isPeakTime = isPeakTime;
    }
    
    // Getters
    public String getName() { return name; }
    public String getRoute() { return route; }
    public double getFare() { return fare; }
    public boolean isPeakTime() { return isPeakTime; }
}


// ============ MAIN CLASS ============

public class SmartCityTransportSystem {
    
    public static void main(String[] args) {
        
        // Print system header
        System.out.println("=".repeat(50));
        System.out.println(TransportService.getSystemInfo());
        System.out.println("=".repeat(50));
        
        // Create list of transport services
        List<TransportService> services = new ArrayList<>();
        services.add(new BusService("City Bus 101", 20.0, "Railway Station - Airport", "08:00 AM"));
        services.add(new BusService("Express 202", 30.0, "Mall - University", "08:30 AM"));
        services.add(new MetroService("Blue Line", 40.0, "North - South", "08:15 AM"));
        services.add(new MetroService("Red Line", 35.0, "East - West", "08:00 AM"));
        services.add(new TaxiService("Uber Premium", 150.0, "Custom Route", "09:00 AM"));
        services.add(new TaxiService("Ola Mini", 80.0, "Railway Station - Airport", "08:45 AM"));
        services.add(new AmbulanceService("Emergency 108", "Hospital Route"));
        
        
        // ========== FEATURE 1: Lambda Expressions ==========
        System.out.println("\n--- Lambda Expressions (Filter & Sort) ---");
        System.out.println("Services with fare less than Rs.50:");
        
        // Filterinf services with fare < 50 and sort by fare
        services.stream()
                .filter(s -> s.getFare() < 50)
                .sorted((s1, s2) -> Double.compare(s1.getFare(), s2.getFare()))
                .forEach(s -> s.printServiceDetails());
        
        
        // ========== FEATURE 2: Method References ==========
        System.out.println("\n--- Method References ---");
        System.out.println("All services:");
        
        // Using method reference to print all services
        services.forEach(TransportService::printServiceDetails);
        
        
        // ========== FEATURE 3: Static Method in Interface ==========
        System.out.println("\n--- Static Method in Interface ---");
        
        // Calculating distance using GeoUtils static method
        double distance = GeoUtils.calculateDistance(28.4595, 77.0266, 28.7041, 77.1025);
        System.out.println("Distance: " + String.format("%.2f", distance) + " km");
        
        
        // ========== FEATURE 4: Stream APIs ==========
        System.out.println("\n--- Stream APIs ---");
        System.out.println("Services sorted by departure time:");
        
        // Sorting services by departure time and display
        services.stream()
                .filter(s -> !s.getDepartureTime().equals("IMMEDIATE"))
                .sorted(Comparator.comparing(TransportService::getDepartureTime))
                .forEach(s -> System.out.println(s.getDepartureTime() + " - " + s.getServiceName()));
        
        
        // ========== FEATURE 5: forEach Method ==========
        System.out.println("\n--- forEach() Method ---");
        System.out.println("Dashboard:");
        
        // Useing forEach to display all services
        services.forEach(service -> 
            System.out.println("✓ " + service.getServiceName() + " - " + service.getDepartureTime())
        );
        
        
        // Create passenger data
        List<Passenger> passengers = Arrays.asList(
            new Passenger("Amit", "Railway Station - Airport", 20.0, true),
            new Passenger("Priya", "Railway Station - Airport", 30.0, true),
            new Passenger("Rahul", "Mall - University", 30.0, false),
            new Passenger("Sneha", "North - South", 40.0, true),
            new Passenger("Vijay", "East - West", 35.0, false),
            new Passenger("Anjali", "Railway Station - Airport", 80.0, true)
        );
        
        
        // ========== FEATURE 6: Collectors.groupingBy() ==========
        System.out.println("\n--- Collectors.groupingBy() ---");
        System.out.println("Passengers by route:");
        
        // Grouping passengers by route
        Map<String, List<Passenger>> passengersByRoute = passengers.stream()
                .collect(Collectors.groupingBy(Passenger::getRoute));
        
        passengersByRoute.forEach((route, list) -> 
            System.out.println(route + ": " + list.size() + " passengers")
        );
        
        
        // ========== FEATURE 7: Collectors.partitioningBy() ==========
        System.out.println("\n--- Collectors.partitioningBy() ---");
        
        // Partition passengers into peak and non-peak time
        Map<Boolean, List<Passenger>> peakPartition = passengers.stream()
                .collect(Collectors.partitioningBy(Passenger::isPeakTime));
        
        System.out.println("Peak time: " + peakPartition.get(true).size());
        System.out.println("Non-peak: " + peakPartition.get(false).size());
        
        
        // ========== FEATURE 8: Collectors.summarizingDouble() ==========
        System.out.println("\n--- Collectors.summarizingDouble() ---");
        
        // Geting fare statistics
        DoubleSummaryStatistics fareStats = passengers.stream()
                .collect(Collectors.summarizingDouble(Passenger::getFare));
        
        System.out.println("Total Revenue: Rs." + fareStats.getSum());
        System.out.println("Average Fare: Rs." + String.format("%.2f", fareStats.getAverage()));
        System.out.println("Max Fare: Rs." + fareStats.getMax());
        System.out.println("Min Fare: Rs." + fareStats.getMin());
        System.out.println("Total Passengers: " + fareStats.getCount());
        
        
        // ========== FEATURE 9: Functional Interface ==========
        System.out.println("\n--- Functional Interface ---");
        
        // Creating lambda implementations of FareCalculator
        FareCalculator normalFare = (dist, pass) -> dist * 10 * pass;
        FareCalculator acFare = (dist, pass) -> dist * 15 * pass;
        
        double tripDist = 15.0;
        int passCount = 2;
        
        System.out.println("Trip: " + tripDist + " km, " + passCount + " passengers");
        System.out.println("Normal Fare: Rs." + normalFare.calculateFare(tripDist, passCount));
        System.out.println("AC Fare: Rs." + acFare.calculateFare(tripDist, passCount));
        
        
        // ========== FEATURE 10: Marker Interface ==========
        System.out.println("\n--- Marker Interface ---");
        
        // Filtering and display emergency services
        services.stream()
                .filter(s -> s instanceof EmergencyService)
                .forEach(s -> System.out.println("🚨 " + s.getServiceName()));
        
        
        // ========== Real-Time Scenario ==========
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SCENARIO: Find services for Railway Station - Airport");
        System.out.println("=".repeat(50));
        
        String route = "Railway Station - Airport";
        
        // Filtering by route and sort by fare
        services.stream()
                .filter(s -> s.getRoute().equals(route))
                .sorted(Comparator.comparingDouble(TransportService::getFare))
                .forEach(s -> System.out.println("→ " + s.getServiceName() + 
                         " | Rs." + s.getFare() + " | " + s.getDepartureTime()));
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("✓ All Java 8 Features Demonstrated!");
        System.out.println("=".repeat(50));
    }
}
