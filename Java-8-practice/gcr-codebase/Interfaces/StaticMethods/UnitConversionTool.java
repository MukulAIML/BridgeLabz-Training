// Unit Conversion Tool with Static Interface Methods
import java.util.Scanner;

interface UnitConverter {
    static double kmToMiles(double km) {
        return km * 0.621371;
    }
    
    static double milesToKm(double miles) {
        return miles * 1.60934;
    }
    
    static double kgToLbs(double kg) {
        return kg * 2.20462;
    }
    
    static double lbsToKg(double lbs) {
        return lbs * 0.453592;
    }
}

public class UnitConversionTool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Unit Conversion Tool");
        System.out.println("1. Km to Miles");
        System.out.println("2. Miles to Km");
        System.out.println("3. Kg to Lbs");
        System.out.println("4. Lbs to Kg");
        System.out.print("Choose conversion type: ");
        int choice = scanner.nextInt();
        
        System.out.print("Enter value: ");
        double value = scanner.nextDouble();
        
        double result = 0;
        String unit = "";
        
        switch (choice) {
            case 1:
                result = UnitConverter.kmToMiles(value);
                unit = "miles";
                break;
            case 2:
                result = UnitConverter.milesToKm(value);
                unit = "km";
                break;
            case 3:
                result = UnitConverter.kgToLbs(value);
                unit = "lbs";
                break;
            case 4:
                result = UnitConverter.lbsToKg(value);
                unit = "kg";
                break;
            default:
                System.out.println("Invalid choice");
                scanner.close();
                return;
        }
        
        System.out.printf("Result: %.2f %s%n", result, unit);
        
        scanner.close();
    }
}
