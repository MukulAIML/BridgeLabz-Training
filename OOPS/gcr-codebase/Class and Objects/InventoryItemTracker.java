public class InventoryItemTracker {
    int itemCode;
    String itemName;
    double price;

    // Constructor to initialize item details
    public InventoryItemTracker(int itemCode, String itemName, double price) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.price = price;
    }

    // Method to display item and compute total cost
    public void displayDetails(int quantity) {
        System.out.println("Item Code: " + itemCode);
        System.out.println("Item Name: " + itemName);
        System.out.println("Price: " + price);
        System.out.println("Total Cost for " + quantity + " items: " + (price * quantity));
    }

    // Main method
    public static void main(String[] args) {
        InventoryItemTracker item = new InventoryItemTracker(201, "Pen", 10);
        item.displayDetails(3);
    }
}