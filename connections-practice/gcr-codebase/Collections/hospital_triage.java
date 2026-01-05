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
        PriorityQueue<Patient> triage = new PriorityQueue<>();
        
        triage.add(new Patient("John", 3));
        triage.add(new Patient("Alice", 5));
        triage.add(new Patient("Bob", 2));
        
        System.out.println("Treatment Order:");
        while (!triage.isEmpty()) {
            System.out.println(triage.poll());
        }
    }
}