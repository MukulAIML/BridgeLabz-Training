import java.util.*;
import java.util.stream.*;

class SensorReading {
    private String sensorId;
    private double reading;
    
    public SensorReading(String sensorId, double reading) {
        this.sensorId = sensorId;
        this.reading = reading;
    }
    
    public String getSensorId() { return sensorId; }
    public double getReading() { return reading; }
    
    @Override
    public String toString() {
        return "Sensor " + sensorId + ": " + reading;
    }
}

public class IoTSensorReadings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter threshold value: ");
        double threshold = sc.nextDouble();
        
        System.out.print("Enter number of sensors: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        List<SensorReading> readings = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.println("\nSensor " + (i + 1) + ":");
            System.out.print("Enter sensor ID: ");
            String id = sc.nextLine();
            System.out.print("Enter reading value: ");
            double value = sc.nextDouble();
            sc.nextLine();
            
            readings.add(new SensorReading(id, value));
        }
        
        System.out.println("\n=== Readings Above Threshold (" + threshold + ") ===");
        readings.stream()
            .filter(r -> r.getReading() > threshold)
            .forEach(System.out::println);
        
        sc.close();
    }
}
