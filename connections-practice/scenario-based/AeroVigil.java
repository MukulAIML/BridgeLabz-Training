import java.util.Scanner;

// Custom exception class for invalid flight details
class InvalidFlightException extends Exception {
    public InvalidFlightException(String message) {
        super(message);
    }
}

// Utility class for flight validation and calculations
class FlightUtil {
    
    // Validates flight number format (FL-XXXX where X is digit)
    public boolean validateFlightNumber(String flightNumber) throws InvalidFlightException {
        if (flightNumber == null || !flightNumber.matches("FL-\\d{4}")) {
            throw new InvalidFlightException("The flight number " + flightNumber + " is invalid");
        }
        return true;
    }
    
    // Validates flight name against allowed airlines
    public boolean validateFlightName(String flightName) throws InvalidFlightException {
        if (flightName == null || 
            (!flightName.equals("SpiceJet") && 
             !flightName.equals("Vistara") && 
             !flightName.equals("IndiGo") && 
             !flightName.equals("Air Arabia"))) {
            throw new InvalidFlightException("The flight name " + flightName + " is invalid");
        }
        return true;
    }
    
    // Validates passenger count based on airline capacity
    public boolean validatePassengerCount(int passengerCount, String flightName) throws InvalidFlightException {
        int maxCapacity = 0;
        
        switch (flightName) {
            case "SpiceJet":
                maxCapacity = 500;
                break;
            case "Vistara":
                maxCapacity = 615;
                break;
            case "IndiGo":
                maxCapacity = 230;
                break;
            case "Air Arabia":
                maxCapacity = 130;
                break;
        }
        
        if (passengerCount <= 0 || passengerCount > maxCapacity) {
            throw new InvalidFlightException("The passenger count " + passengerCount + " is invalid for " + flightName);
        }
        return true;
    }
    
    // Calculates fuel required to fill the tank
    public double calculateFuelToFillTank(String flightName, double currentFuelLevel) throws InvalidFlightException {
        double maxFuelCapacity = 0;
        
        switch (flightName) {
            case "SpiceJet":
                maxFuelCapacity = 200000;
                break;
            case "Vistara":
                maxFuelCapacity = 300000;
                break;
            case "IndiGo":
                maxFuelCapacity = 250000;
                break;
            case "Air Arabia":
                maxFuelCapacity = 150000;
                break;
        }
        
        if (currentFuelLevel < 0 || currentFuelLevel > maxFuelCapacity) {
            throw new InvalidFlightException("Invalid fuel level for " + flightName);
        }
        
        return maxFuelCapacity - currentFuelLevel;
    }
}

// Main class for user interaction
public class AeroVigil {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlightUtil flightUtil = new FlightUtil();
        
        try {
            System.out.println("Enter flight details");
            String input = scanner.nextLine();
            
            // Split input by colon
            String[] details = input.split(":");
            
            String flightNumber = details[0];
            String flightName = details[1];
            int passengerCount = Integer.parseInt(details[2]);
            double currentFuelLevel = Double.parseDouble(details[3]);
            
            // Validate all flight details
            flightUtil.validateFlightNumber(flightNumber);
            flightUtil.validateFlightName(flightName);
            flightUtil.validatePassengerCount(passengerCount, flightName);
            
            // Calculate and display required fuel
            double fuelRequired = flightUtil.calculateFuelToFillTank(flightName, currentFuelLevel);
            System.out.println("Fuel required to fill the tank: " + fuelRequired + " liters");
            
        } catch (InvalidFlightException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input format");
        } finally {
            scanner.close();
        }
    }
}
