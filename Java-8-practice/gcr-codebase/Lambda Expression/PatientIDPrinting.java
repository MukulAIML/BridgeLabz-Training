import java.util.*;

/**
 * Hospital Patient ID Printing using Method References
 * Demonstrates method references instead of lambda expressions
 */
public class PatientIDPrinting {
    
    static class Patient {
        String id;
        String name;
        
        public Patient(String id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public String getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
    }
    
    // Method to print patient ID
    public static void printPatientId(Patient patient) {
        System.out.println("Patient ID: " + patient.getId());
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create patient list
        List<Patient> patients = new ArrayList<>();
        
        System.out.println("=== Hospital Patient ID Verification System ===\n");
        
        System.out.print("Enter number of patients to add: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        // Input patient data
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter Patient " + i + " ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Patient " + i + " Name: ");
            String name = scanner.nextLine();
            patients.add(new Patient(id, name));
        }
        
        System.out.println("\n--- Admin Verification: All Patient IDs ---");
        
        // Using method reference instead of lambda
        // Instead of: patients.forEach(p -> printPatientId(p));
        patients.forEach(PatientIDPrinting::printPatientId);
        
        System.out.println("\n--- Patient Details ---");
        // Another method reference example using System.out::println
        patients.forEach(p -> System.out.println("ID: " + p.getId() + " | Name: " + p.getName()));
        
        scanner.close();
    }
}
