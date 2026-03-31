import java.util.*;
import java.util.stream.*;

class Doctor {
    private String name;
    private String specialty;
    private boolean availableOnWeekends;
    
    public Doctor(String name, String specialty, boolean availableOnWeekends) {
        this.name = name;
        this.specialty = specialty;
        this.availableOnWeekends = availableOnWeekends;
    }
    
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
    public boolean isAvailableOnWeekends() { return availableOnWeekends; }
    
    @Override
    public String toString() {
        return name + " - " + specialty + " (Weekend Available: " + availableOnWeekends + ")";
    }
}

public class HospitalDoctorAvailability {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of doctors: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        List<Doctor> doctors = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.println("\nDoctor " + (i + 1) + ":");
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter specialty: ");
            String specialty = sc.nextLine();
            System.out.print("Available on weekends? (yes/no): ");
            boolean available = sc.nextLine().equalsIgnoreCase("yes");
            
            doctors.add(new Doctor(name, specialty, available));
        }
        
        System.out.println("\n=== Doctors Available on Weekends (Sorted by Specialty) ===");
        doctors.stream()
            .filter(Doctor::isAvailableOnWeekends)
            .sorted(Comparator.comparing(Doctor::getSpecialty))
            .forEach(System.out::println);
        
        sc.close();
    }
}
