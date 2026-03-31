import java.util.Scanner;

// Custom exception class for robot safety violations
class RobotSafetyException extends Exception {
    public RobotSafetyException(String message) {
        super(message);
    }
}

// Class to audit robot hazard risks
class RobotHazardAuditor {
    
    public double CalculateHazardRisk(double armPrecision, int workerDensity, String machineryState) throws RobotSafetyException {
        
        // Validate arm precision range
        if (armPrecision < 0.0 || armPrecision > 1.0) {
            throw new RobotSafetyException("Error: Arm precision must be 0.0-1.0");
        }
        
        // Validate worker density range
        if (workerDensity < 1 || workerDensity > 20) {
            throw new RobotSafetyException("Error: Worker density must be 1-20");
        }
        
        // Determine machine risk factor based on machinery state
        double machineRiskFactor;
        
        if (machineryState.equals("Worn")) {
            machineRiskFactor = 1.3;
        } else if (machineryState.equals("Faulty")) {
            machineRiskFactor = 2.0;
        } else if (machineryState.equals("Critical")) {
            machineRiskFactor = 3.0;
        } else {
            throw new RobotSafetyException("Error: Unsupported machinery state");
        }
        
        // Calculate hazard risk score
        double hazardRisk = ((1.0 - armPrecision) * 15.0) + (workerDensity * machineRiskFactor);
        
        return hazardRisk;
    }
}

// Main program class
public class FactoryHazardAnalyzer {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RobotHazardAuditor auditor = new RobotHazardAuditor();
        
        try {
            // Get arm precision input
            System.out.println("Enter Arm Precision (0.0 - 1.0):");
            double armPrecision = scanner.nextDouble();
            
            // Get worker density input
            System.out.println("Enter Worker Density (1 - 20):");
            int workerDensity = scanner.nextInt();
            
            // Get machinery state input
            System.out.println("Enter Machinery State (Worn/Faulty/Critical):");
            String machineryState = scanner.next();
            
            // Calculate hazard risk
            double riskScore = auditor.CalculateHazardRisk(armPrecision, workerDensity, machineryState);
            
            // Display result
            System.out.println("Robot Hazard Risk Score: " + riskScore);
            
        } catch (RobotSafetyException e) {
            // Handle custom exception and display message
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
