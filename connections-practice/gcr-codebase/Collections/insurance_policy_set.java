import java.util.*;
import java.time.*;

class Policy implements Comparable<Policy> {
    String policyNumber;
    String holderName;
    LocalDate expiryDate;
    String coverageType;
    double premium;

    public Policy(String policyNumber, String holderName, LocalDate expiryDate, 
                  String coverageType, double premium) {
        this.policyNumber = policyNumber;
        this.holderName = holderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premium = premium;
    }

    @Override
    public int compareTo(Policy other) {
        return this.expiryDate.compareTo(other.expiryDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Policy)) return false;
        Policy policy = (Policy) o;
        return policyNumber.equals(policy.policyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }

    @Override
    public String toString() {
        return policyNumber + " - " + holderName + " (" + coverageType + ")";
    }
}

public class InsurancePolicySet {
    private Set<Policy> hashSet = new HashSet<>();
    private Set<Policy> linkedHashSet = new LinkedHashSet<>();
    private Set<Policy> treeSet = new TreeSet<>();

    public void addPolicy(Policy policy) {
        hashSet.add(policy);
        linkedHashSet.add(policy);
        treeSet.add(policy);
    }

    public List<Policy> getPoliciesExpiringSoon() {
        List<Policy> result = new ArrayList<>();
        LocalDate thirtyDaysLater = LocalDate.now().plusDays(30);
        for (Policy p : treeSet) {
            if (p.expiryDate.isBefore(thirtyDaysLater)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Policy> getPoliciesByCoverageType(String coverageType) {
        List<Policy> result = new ArrayList<>();
        for (Policy p : hashSet) {
            if (p.coverageType.equals(coverageType)) {
                result.add(p);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        InsurancePolicySet system = new InsurancePolicySet();
        
        system.addPolicy(new Policy("P001", "John Doe", LocalDate.now().plusDays(15), "Health", 5000));
        system.addPolicy(new Policy("P002", "Jane Smith", LocalDate.now().plusDays(45), "Auto", 3000));
        system.addPolicy(new Policy("P003", "Bob Wilson", LocalDate.now().plusDays(20), "Home", 4000));
        
        System.out.println("Policies expiring soon: " + system.getPoliciesExpiringSoon());
        System.out.println("Health policies: " + system.getPoliciesByCoverageType("Health"));
    }
}