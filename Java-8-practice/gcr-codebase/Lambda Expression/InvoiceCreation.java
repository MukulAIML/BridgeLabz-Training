import java.util.*;
import java.util.stream.Collectors;

/**
 * Invoice Object Creation using Constructor References
 * Demonstrates constructor reference for object creation from IDs
 */
public class InvoiceCreation {
    
    static class Invoice {
        String invoiceId;
        String transactionId;
        double amount;
        String status;
        
        // Constructor that takes transaction ID
        public Invoice(String transactionId) {
            this.transactionId = transactionId;
            this.invoiceId = "INV-" + transactionId;
            this.amount = 0.0;
            this.status = "PENDING";
        }
        
        // Setter for amount
        public void setAmount(double amount) {
            this.amount = amount;
        }
        
        @Override
        public String toString() {
            return String.format("Invoice ID: %s | Transaction: %s | Amount: $%.2f | Status: %s",
                               invoiceId, transactionId, amount, status);
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Invoice Generation System ===\n");
        
        System.out.print("Enter number of transactions: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        // Collect transaction IDs
        List<String> transactionIds = new ArrayList<>();
        Map<String, Double> transactionAmounts = new HashMap<>();
        
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter Transaction " + i + " ID: ");
            String txnId = scanner.nextLine();
            System.out.print("Enter Transaction " + i + " Amount: $");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            
            transactionIds.add(txnId);
            transactionAmounts.put(txnId, amount);
        }
        
        System.out.println("\n--- Generating Invoices ---");
        
        // Using constructor reference Invoice::new to create invoice objects
        // Instead of: transactionIds.stream().map(id -> new Invoice(id))
        List<Invoice> invoices = transactionIds.stream()
                                               .map(Invoice::new)
                                               .collect(Collectors.toList());
        
        // Set amounts for each invoice
        invoices.forEach(invoice -> 
            invoice.setAmount(transactionAmounts.get(invoice.transactionId))
        );
        
        System.out.println("\n--- Generated Invoices ---");
        invoices.forEach(System.out::println);
        
        System.out.println("\nTotal Invoices Generated: " + invoices.size());
        double totalAmount = invoices.stream()
                                     .mapToDouble(inv -> inv.amount)
                                     .sum();
        System.out.printf("Total Amount: $%.2f\n", totalAmount);
        
        scanner.close();
    }
}
