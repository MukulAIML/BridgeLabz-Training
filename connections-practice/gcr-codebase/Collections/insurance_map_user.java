import java.util.*;
import java.time.*;

class PolicyDetails {
    String holderName;
    LocalDate expiryDate;
    String coverageType;
    double premium;

    public PolicyDetails(String holderName, LocalDate expiryDate, 
                        String coverageType, double premium) {
        this.holderName = holderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premium = premium;
    }

    @Override
    public String toString() {
        return holderName + " - " + coverageType + " (Expires: " + expiryDate + ", Premium: $" + premium + ")";
    }
}

public class InsurancePolicyMap {
    private Map<String, PolicyDetails> hashMap = new HashMap<>();
    private Map<String, PolicyDetails> linkedHashMap = new LinkedHashMap<>();
    private TreeMap<LocalDate, List<PolicyDetails>> treeMap = new TreeMap<>();

    public void addPolicy(String policyNumber, PolicyDetails details) {
        hashMap.put(policyNumber, details);
        linkedHashMap.put(policyNumber, details);
        
        if (!treeMap.containsKey(details.expiryDate)) {
            treeMap.put(details.expiryDate, new ArrayList<>());
        }
        treeMap.get(details.expiryDate).add(details);
    }

    public PolicyDetails getPolicy(String policyNumber) {
        return hashMap.get(policyNumber);
    }

    public List<PolicyDetails> getPoliciesExpiringSoon() {
        List<PolicyDetails> result = new ArrayList<>();
        LocalDate thirtyDaysLater = LocalDate.now().plusDays(30);
        
        for (Map.Entry<LocalDate, List<PolicyDetails>> entry : treeMap.entrySet()) {
            if (entry.getKey().isBefore(thirtyDaysLater)) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }

    public List<PolicyDetails> getPoliciesByHolder(String holderName) {
        List<PolicyDetails> result = new ArrayList<>();
        for (PolicyDetails policy : hashMap.values()) {
            if (policy.holderName.equalsIgnoreCase(holderName)) {
                result.add(policy);
            }
        }
        return result;
    }

    public void removeExpiredPolicies() {
        LocalDate today = LocalDate.now();
        hashMap.entrySet().removeIf(e -> e.getValue().expiryDate.isBefore(today));
        linkedHashMap.entrySet().removeIf(e -> e.getValue().expiryDate.isBefore(today));
        treeMap.headMap(today).clear();
        System.out.println("Expired policies removed.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InsurancePolicyMap system = new InsurancePolicyMap();
        
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
            System.out.print("Coverage Type: ");
            String coverage = sc.nextLine();
            System.out.print("Premium Amount: ");
            double premium = sc.nextDouble();
            sc.nextLine();
            
            LocalDate expiryDate = LocalDate.now().plusDays(days);
            system.addPolicy(policyNum, new PolicyDetails(name, expiryDate, coverage, premium));
        }
        
        System.out.println("\n=== Menu ===");
        System.out.println("1. Retrieve policy by number");
        System.out.println("2. Policies expiring soon");
        System.out.println("3. Policies by holder name");
        System.out.println("4. Remove expired policies");
        System.out.print("Choose option: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Enter policy number: ");
                String pNum = sc.nextLine();
                PolicyDetails pd = system.getPolicy(pNum);
                System.out.println(pd != null ? pd : "Policy not found");
                break;
            case 2:
                System.out.println("Policies expiring within 30 days:");
                for (PolicyDetails p : system.getPoliciesExpiringSoon()) {
                    System.out.println(p);
                }
                break;
            case 3:
                System.out.print("Enter holder name: ");
                String hName = sc.nextLine();
                System.out.println("Policies for " + hName + ":");
                for (PolicyDetails p : system.getPoliciesByHolder(hName)) {
                    System.out.println(p);
                }
                break;
            case 4:
                system.removeExpiredPolicies();
                break;
        }
        
        sc.close();
    }
}