// Problem 1: Smart Warehouse Management System

// Base class for all warehouse items
abstract class WarehouseItem {
    private String name;
    private double weight;
    
    public WarehouseItem(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
    
    public String getName() { return name; }
    public double getWeight() { return weight; }
    
    @Override
    public String toString() {
        return name + " (" + weight + " kg)";
    }
}

// Specific item types
class Electronics extends WarehouseItem {
    private String brand;
    
    public Electronics(String name, double weight, String brand) {
        super(name, weight);
        this.brand = brand;
    }
    
    public String getBrand() { return brand; }
}

class Groceries extends WarehouseItem {
    private String expiryDate;
    
    public Groceries(String name, double weight, String expiryDate) {
        super(name, weight);
        this.expiryDate = expiryDate;
    }
    
    public String getExpiryDate() { return expiryDate; }
}

class Furniture extends WarehouseItem {
    private String material;
    
    public Furniture(String name, double weight, String material) {
        super(name, weight);
        this.material = material;
    }
    
    public String getMaterial() { return material; }
}

// Generic storage class with bounded type parameter
class Storage<T extends WarehouseItem> {
    private List<T> items;
    
    public Storage() {
        this.items = new ArrayList<>();
    }
    
    public void addItem(T item) {
        items.add(item);
        System.out.println("Added: " + item);
    }
    
    public List<T> getItems() {
        return items;
    }
    
    public int getCount() {
        return items.size();
    }
}

// Warehouse manager with wildcard methods
class WarehouseManager {
    // Wildcard method to display any type of warehouse items
    public void displayAllItems(List<? extends WarehouseItem> items) {
        System.out.println("\n=== Warehouse Inventory ===");
        for (WarehouseItem item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Total items: " + items.size());
    }
    
    // Calculate total weight using wildcards
    public double calculateTotalWeight(List<? extends WarehouseItem> items) {
        double total = 0;
        for (WarehouseItem item : items) {
            total += item.getWeight();
        }
        return total;
    }
}

// Demo class
public class WarehouseDemo {
    public static void main(String[] args) {
        // Create separate storages for different item types
        Storage<Electronics> electronicsStorage = new Storage<>();
        Storage<Groceries> groceriesStorage = new Storage<>();
        Storage<Furniture> furnitureStorage = new Storage<>();
        
        // Add items
        electronicsStorage.addItem(new Electronics("Laptop", 2.5, "Dell"));
        electronicsStorage.addItem(new Electronics("Phone", 0.3, "Samsung"));
        
        groceriesStorage.addItem(new Groceries("Milk", 1.0, "2024-01-15"));
        groceriesStorage.addItem(new Groceries("Bread", 0.5, "2024-01-10"));
        
        furnitureStorage.addItem(new Furniture("Chair", 8.0, "Wood"));
        furnitureStorage.addItem(new Furniture("Table", 15.0, "Metal"));
        
        // Use warehouse manager to display all items
        WarehouseManager manager = new WarehouseManager();
        
        manager.displayAllItems(electronicsStorage.getItems());
        manager.displayAllItems(groceriesStorage.getItems());
        manager.displayAllItems(furnitureStorage.getItems());
        
        // Calculate weights
        System.out.println("\nTotal Electronics Weight: " + 
            manager.calculateTotalWeight(electronicsStorage.getItems()) + " kg");
        System.out.println("Total Groceries Weight: " + 
            manager.calculateTotalWeight(groceriesStorage.getItems()) + " kg");
    }
}