import java.util.*;
import java.time.*;

public class LoggingTransactions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of transactions: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        List<String> transactionIds = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter transaction ID " + (i + 1) + ": ");
            transactionIds.add(sc.nextLine());
        }
        
        System.out.println("\n=== Transaction Log ===");
        transactionIds.forEach(id -> 
            System.out.println(LocalDateTime.now() + " - Transaction: " + id)
        );
        
        sc.close();
    }
}
