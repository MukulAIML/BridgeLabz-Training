import java.util.*;
import java.time.*;
import java.time.format.*;
import java.util.stream.*;

class Member {
    private String name;
    private LocalDate expiryDate;
    
    public Member(String name, LocalDate expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
    }
    
    public String getName() { return name; }
    public LocalDate getExpiryDate() { return expiryDate; }
    
    @Override
    public String toString() {
        return name + " - Expires: " + expiryDate;
    }
}

public class FilteringExpiringMemberships {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        System.out.print("Enter number of members: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        List<Member> members = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.println("\nMember " + (i + 1) + ":");
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter expiry date (yyyy-MM-dd): ");
            LocalDate expiryDate = LocalDate.parse(sc.nextLine(), formatter);
            
            members.add(new Member(name, expiryDate));
        }
        
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);
        
        System.out.println("\n=== Members Expiring Within 30 Days ===");
        System.out.println("Today's Date: " + today);
        
        members.stream()
            .filter(m -> !m.getExpiryDate().isBefore(today) && 
                        !m.getExpiryDate().isAfter(thirtyDaysLater))
            .forEach(System.out::println);
        
        sc.close();
    }
}
