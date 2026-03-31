import java.util.*;
import java.util.stream.Collectors;

class Order {
    private String customerName;
    private double orderTotal;
    
    public Order(String customerName, double orderTotal) {
        this.customerName = customerName;
        this.orderTotal = orderTotal;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public double getOrderTotal() {
        return orderTotal;
    }
}

public class OrderRevenueSummary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Order> orders = new ArrayList<>();
        
        System.out.print("Enter number of orders: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();
            System.out.print("Enter order total: ");
            double total = scanner.nextDouble();
            scanner.nextLine();
            orders.add(new Order(name, total));
        }
        
        Map<String, Double> revenueByCustomer = orders.stream()
            .collect(Collectors.groupingBy(
                Order::getCustomerName,
                Collectors.summingDouble(Order::getOrderTotal)
            ));
        
        System.out.println("\nRevenue Summary by Customer:");
        revenueByCustomer.forEach((customer, revenue) -> 
            System.out.printf("%s: $%.2f%n", customer, revenue)
        );
        
        scanner.close();
    }
}
