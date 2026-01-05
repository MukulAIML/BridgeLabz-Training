import java.util.*;

class Patient implements Comparable<Patient> {
    String name;
    int severity;

    public Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }

    @Override
    public int compareTo(Patient other) {
        return other.severity - this.severity; // Higher severity first
    }

    @Override
    public String toString() {
        return name + " (Severity: " + severity + ")";
    }
}

public class HospitalTriage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Patient> triage = new PriorityQueue<>();
        
        System.out.print("Enter number of patients: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter patient name: ");
            String name = sc.nextLine();
            System.out.print("Enter severity (1-10, higher is more severe): ");
            int severity = sc.nextInt();
            sc.nextLine();
            
            triage.add(new Patient(name, severity));
        }
        
        System.out.println("\n=== Treatment Order (By Priority) ===");
        while (!triage.isEmpty()) {
            System.out.println(triage.poll());
        }
        
        sc.close();
    }
}