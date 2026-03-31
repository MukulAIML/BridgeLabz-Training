import java.util.*;
import java.util.stream.*;

public class TransformingNamesForDisplay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of customers: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        List<String> customerNames = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter customer name " + (i + 1) + ": ");
            customerNames.add(sc.nextLine());
        }
        
        System.out.println("\n=== Customer Names (Uppercase & Sorted) ===");
        customerNames.stream()
            .map(String::toUpperCase)
            .sorted()
            .forEach(System.out::println);
        
        sc.close();
    }
}
