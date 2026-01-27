import java.util.*;

/**
 * E-Commerce Product Sorting using Lambda with Comparator
 * Demonstrates dynamic sorting based on different criteria
 */
public class CustomSorting {
    
    static class Product {
        String name;
        double price;
        double rating;
        int discount;
        
        public Product(String name, double price, double rating, int discount) {
            this.name = name;
            this.price = price;
            this.rating = rating;
            this.discount = discount;
        }
        
        @Override
        public String toString() {
            return String.format("%-20s | Price: $%-6.2f | Rating: %.1f | Discount: %d%%", 
                               name, price, rating, discount);
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create product list
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 899.99, 4.5, 10));
        products.add(new Product("Smartphone", 599.99, 4.7, 15));
        products.add(new Product("Headphones", 149.99, 4.2, 20));
        products.add(new Product("Tablet", 399.99, 4.6, 5));
        products.add(new Product("Smartwatch", 249.99, 4.3, 25));
        
        System.out.println("=== E-Commerce Product Sorting System ===\n");
        
        System.out.println("Original Product List:");
        products.forEach(System.out::println);
        
        System.out.println("\nSelect sorting criteria:");
        System.out.println("1. Sort by Price (Low to High)");
        System.out.println("2. Sort by Price (High to Low)");
        System.out.println("3. Sort by Rating (High to Low)");
        System.out.println("4. Sort by Discount (High to Low)");
        System.out.print("Enter choice (1-4): ");
        
        int choice = scanner.nextInt();
        
        System.out.println("\n--- Sorted Products ---");
        
        // Use lambda expressions with Comparator to sort dynamically
        switch (choice) {
            case 1:
                products.sort((p1, p2) -> Double.compare(p1.price, p2.price));
                System.out.println("Sorted by Price (Low to High):");
                break;
            case 2:
                products.sort((p1, p2) -> Double.compare(p2.price, p1.price));
                System.out.println("Sorted by Price (High to Low):");
                break;
            case 3:
                products.sort((p1, p2) -> Double.compare(p2.rating, p1.rating));
                System.out.println("Sorted by Rating (High to Low):");
                break;
            case 4:
                products.sort((p1, p2) -> Integer.compare(p2.discount, p1.discount));
                System.out.println("Sorted by Discount (High to Low):");
                break;
            default:
                System.out.println("Invalid choice!");
                scanner.close();
                return;
        }
        
        products.forEach(System.out::println);
        scanner.close();
    }
}
