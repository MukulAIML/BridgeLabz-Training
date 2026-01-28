// Temperature Alert System using Predicate
import java.util.Scanner;
import java.util.function.Predicate;

public class TemperatureAlertSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter threshold temperature (°C): ");
        double threshold = scanner.nextDouble();
        
        Predicate<Double> temperatureAlert = temp -> temp > threshold;
        
        System.out.print("Enter current temperature (°C): ");
        double currentTemp = scanner.nextDouble();
        
        if (temperatureAlert.test(currentTemp)) {
            System.out.println("ALERT! Temperature " + currentTemp + 
                             "°C exceeds threshold of " + threshold + "°C");
        } else {
            System.out.println("Temperature " + currentTemp + 
                             "°C is within safe limits");
        }
        
        scanner.close();
    }
}
