import java.util.*;
import java.util.stream.*;

class Claim {
    private String claimType;
    private double claimAmount;
    
    public Claim(String claimType, double claimAmount) {
        this.claimType = claimType;
        this.claimAmount = claimAmount;
    }
    
    public String getClaimType() { return claimType; }
    public double getClaimAmount() { return claimAmount; }
}

public class InsuranceClaimAnalysis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of claims: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        List<Claim> claims = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.println("\nClaim " + (i + 1) + ":");
            System.out.print("Enter claim type (Health/Auto/Home): ");
            String type = sc.nextLine();
            System.out.print("Enter claim amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            
            claims.add(new Claim(type, amount));
        }
        
        System.out.println("\n=== Average Claim Amount by Type ===");
        Map<String, Double> averages = claims.stream()
            .collect(Collectors.groupingBy(
                Claim::getClaimType,
                Collectors.averagingDouble(Claim::getClaimAmount)
            ));
        
        averages.forEach((type, avg) -> 
            System.out.printf("%s: $%.2f%n", type, avg)
        );
        
        sc.close();
    }
}
