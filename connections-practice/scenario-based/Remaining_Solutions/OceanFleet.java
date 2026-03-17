import java.util.*;

// Model class for Vessel
class Vessel {
    private String vesselId;
    private String vesselName;
    private double averageSpeed;
    private String vesselType;
    
    // No-argument constructor
    public Vessel() {
    }
    
    // Four-argument constructor
    public Vessel(String vesselId, String vesselName, double averageSpeed, String vesselType) {
        this.vesselId = vesselId;
        this.vesselName = vesselName;
        this.averageSpeed = averageSpeed;
        this.vesselType = vesselType;
    }
    
    // Getters
    public String getVesselId() {
        return vesselId;
    }
    
    public String getVesselName() {
        return vesselName;
    }
    
    public double getAverageSpeed() {
        return averageSpeed;
    }
    
    public String getVesselType() {
        return vesselType;
    }
    
    // Setters
    public void setVesselId(String vesselId) {
        this.vesselId = vesselId;
    }
    
    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }
    
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
    
    public void setVesselType(String vesselType) {
        this.vesselType = vesselType;
    }
}

// Utility class for vessel operations
class VesselUtil {
    private List<Vessel> vesselList;
    
    // Constructor
    public VesselUtil() {
        this.vesselList = new ArrayList<>();
    }
    
    // Getter
    public List<Vessel> getVesselList() {
        return vesselList;
    }
    
    // Setter
    public void setVesselList(List<Vessel> vesselList) {
        this.vesselList = vesselList;
    }
    
    // Requirement 1: Add vessel to the list
    public void addVesselPerformance(Vessel vessel) {
        vesselList.add(vessel);
    }
    
    // Requirement 2: Retrieve vessel by ID
    public Vessel getVesselById(String vesselId) {
        for (Vessel vessel : vesselList) {
            if (vessel.getVesselId().equals(vesselId)) {
                return vessel;
            }
        }
        return null;
    }
    
    // Requirement 3: Retrieve high performance vessels
    public List<Vessel> getHighPerformanceVessels() {
        List<Vessel> highPerformanceVessels = new ArrayList<>();
        
        if (vesselList.isEmpty()) {
            return highPerformanceVessels;
        }
        
        // Find maximum speed
        double maxSpeed = vesselList.get(0).getAverageSpeed();
        for (Vessel vessel : vesselList) {
            if (vessel.getAverageSpeed() > maxSpeed) {
                maxSpeed = vessel.getAverageSpeed();
            }
        }
        
        // Add all vessels with maximum speed
        for (Vessel vessel : vesselList) {
            if (vessel.getAverageSpeed() == maxSpeed) {
                highPerformanceVessels.add(vessel);
            }
        }
        
        return highPerformanceVessels;
    }
}

// Main class for user interface
public class OceanFleet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VesselUtil vesselUtil = new VesselUtil();
        
        // Input number of vessels
        System.out.println("Enter the number of vessels to be added");
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline
        
        // Input vessel details
        System.out.println("Enter vessel details");
        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            String[] details = input.split(":");
            
            String vesselId = details[0];
            String vesselName = details[1];
            double averageSpeed = Double.parseDouble(details[2]);
            String vesselType = details[3];
            
            Vessel vessel = new Vessel(vesselId, vesselName, averageSpeed, vesselType);
            vesselUtil.addVesselPerformance(vessel);
        }
        
        // Input vessel ID to search
        System.out.println("Enter the Vessel Id to check speed");
        String searchId = sc.nextLine();
        
        // Search and display vessel
        Vessel foundVessel = vesselUtil.getVesselById(searchId);
        if (foundVessel != null) {
            System.out.println(foundVessel.getVesselId() + " | " + 
                             foundVessel.getVesselName() + " | " + 
                             foundVessel.getVesselType() + " | " + 
                             foundVessel.getAverageSpeed() + " knots");
        } else {
            System.out.println("Vessel Id " + searchId + " not found");
        }
        
        // Display high performance vessels
        List<Vessel> highPerformers = vesselUtil.getHighPerformanceVessels();
        System.out.println("High performance vessels are");
        for (Vessel vessel : highPerformers) {
            System.out.println(vessel.getVesselId() + " | " + 
                             vessel.getVesselName() + " | " + 
                             vessel.getVesselType() + " | " + 
                             vessel.getAverageSpeed() + " knots");
        }
        
        sc.close();
    }
}
