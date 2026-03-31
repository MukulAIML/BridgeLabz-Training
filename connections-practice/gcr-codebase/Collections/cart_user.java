import java.util.*;

public class ShoppingCart {
    private Map<String, Double> prices = new HashMap<>();
    private LinkedHashMap<String, Integer> cartOrder = new LinkedHashMap<>();

    public void addProductPrice(String product, double price) {
        prices.put(product, price);
    }

    public void addToCart(String product, int quantity) {
        if (prices.containsKey(product)) {
            cartOrder.put(product, cartOrder.getOrDefault(product, 0) + quantity);
            System.out.println(product + " added to cart.");
        } else {
            System.out.println("Product not found in catalog!");
        }
    }

    public void displayCart() {
        System.out.println("\n=== Shopping Cart (Order Added) ===");
        double total = 0;
        for (Map.Entry<String, Integer> entry : cartOrder.entrySet()) {
            String product = entry.getKey();
            int qty = entry.getValue();
            double price = prices.get(product);
            double subtotal = price * qty;
            System.out.println(product + " x" + qty + " - $" + price + " each = $" + subtotal);
            total += subtotal;
        }
        System.out.println("Total: $" + total);
    }

    public void displaySortedByPrice() {
        System.out.println("\n=== Items Sorted by Price ===");
        TreeMap<Double, List<String>> sortedByPrice = new TreeMap<>();
        
        for (String product : cartOrder.keySet()) {
            double price = prices.get(product);
            if (!sortedByPrice.containsKey(price)) {
                sortedByPrice.put(price, new ArrayList<>());
            }
            sortedByPrice.get(price).add(product);
        }
        
        for (Map.Entry<Double, List<String>> entry : sortedByPrice.entrySet()) {
            for (String product : entry.getValue()) {
                int qty = cartOrder.get(product);
                System.out.println(product + " - $" + entry.getKey() + " x" + qty);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        
        System.out.print("Enter number of products in catalog: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Enter product details:");
        for (int i = 0; i < n; i++) {
            System.out.print("Product name: ");
            String name = sc.nextLine();
            System.out.print("Price: $");
            double price = sc.nextDouble();
            sc.nextLine();
            cart.addProductPrice(name, price);
        }
        
        System.out.print("\nHow many items to add to cart? ");
        int cartItems = sc.nextInt();
        sc.nextLine();
        
        for (int i = 0; i < cartItems; i++) {
            System.out.print("Product name: ");
            String name = sc.nextLine();
            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();
            cart.addToCart(name, qty);
        }
        
        cart.displayCart();
        cart.displaySortedByPrice();
        
        sc.close();
    }
}