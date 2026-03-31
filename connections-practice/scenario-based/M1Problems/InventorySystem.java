package M1Problems;

import java.util.*;

// base class
abstract class Product {
    String name;
    double price;
    int quantity;

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    abstract void display();

    double getValue() {
        return price * quantity;
    }
}

// electronics class
class Electronics extends Product {
    int warranty;

    Electronics(String name, double price, int quantity, int warranty) {
        super(name, price, quantity);
        this.warranty = warranty;
    }

    void display() {
        System.out.println(name + " - Price: " + price + ", Quantity: " + quantity +
                ", Warranty: " + warranty + " months");
    }
}

// clothing class
class Clothing extends Product {
    String size;

    Clothing(String name, double price, int quantity, String size) {
        super(name, price, quantity);
        this.size = size;
    }

    void display() {
        System.out.println(name + " - Price: " + price + ", Quantity: " + quantity +
                ", Size: " + size);
    }
}

// main class
public class InventorySystem {

    static List<Product> inventory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {

            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                i--;
                continue;
            }

            String[] parts = line.split(",");

            String type = parts[0].trim();

            if (type.equals("Electronics")) {
                String name = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                int qty = Integer.parseInt(parts[3].trim());
                int warranty = Integer.parseInt(parts[4].trim());

                inventory.add(new Electronics(name, price, qty, warranty));
                System.out.println("Product added to inventory: " + name);
            }

            else if (type.equals("Clothing")) {
                String name = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                int qty = Integer.parseInt(parts[3].trim());
                String size = parts[4].trim();

                inventory.add(new Clothing(name, price, qty, size));
                System.out.println("Product added to inventory: " + name);
            }
        }

        // display inventory
        System.out.println("Inventory:");
        for (Product p : inventory) {
            p.display();
        }

        // total value
        double total = 0;
        for (Product p : inventory) {
            total += p.getValue();
        }

        System.out.printf("Total value of the inventory: %.2f\n", total);
    }
}
