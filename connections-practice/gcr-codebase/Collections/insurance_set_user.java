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
        return policyNumber + " - " + holderName + " (" + coverageType + ", Exp: " + expiryDate + ")";
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
            if (p.coverageType.equalsIgnoreCase(coverageType)) {
                result.add(p);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InsurancePolicySet system = new InsurancePolicySet();
        
        System.out.print("Enter number of policies: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        for (int i = 0; i < n; i++) {
            System.out.println("\nPolicy " + (i + 1) + ":");
            System.out.print("Policy Number: ");
            String policyNum = sc.nextLine();
            System.out.print("Holder Name: ");
            String name = sc.nextLine();
            System.out.print("Days until expiry: ");
            int days = sc.nextInt();
            sc.nextLine();
            System.out.print("Coverage Type (Health/Auto/Home): ");
            String coverage = sc.nextLine();
            System.out.print("Premium Amount: ");
            double premium = sc.nextDouble();
            sc.nextLine();
            
            LocalDate expiryDate = LocalDate.now().plusDays(days);
            system.addPolicy(new Policy(policyNum, name, expiryDate, coverage, premium));
        }
        
        System.out.println("\n=== All Policies (LinkedHashSet - Insertion Order) ===");
        for (Policy p : system.linkedHashSet) {
            System.out.println(p);
        }
        
        System.out.println("\n=== Policies Expiring Soon (within 30 days) ===");
        List<Policy> expiring = system.getPoliciesExpiringSoon();
        if (expiring.isEmpty()) {
            System.out.println("No policies expiring soon.");
        } else {
            for (Policy p : expiring) {
                System.out.println(p);
            }
        }
        
        System.out.print("\nEnter coverage type to search: ");
        String searchType = sc.nextLine();
        System.out.println("=== " + searchType + " Policies ===");
        List<Policy> filtered = system.getPoliciesByCoverageType(searchType);
        if (filtered.isEmpty()) {
            System.out.println("No policies found.");
        } else {
            for (Policy p : filtered) {
                System.out.println(p);
            }
        }
        
        sc.close();
    }
}