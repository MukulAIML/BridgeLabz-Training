// Problem 2: Dynamic Online Marketplace

// Category interfaces
interface Category {
    String getCategoryName();
}

class BookCategory implements Category {
    public String getCategoryName() { return "Books"; }
}

class ClothingCategory implements Category {
    public String getCategoryName() { return "Clothing"; }
}

class GadgetCategory implements Category {
    public String getCategoryName() { return "Gadgets"; }
}

// Generic Product class with bounded type parameter
class Product<T extends Category> {
    private String name;
    private double price;
    private T category;
    
    public Product(String name, double price, T category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public T getCategory() { return category; }
    
    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price) + 
               " [" + category.getCategoryName() + "]";
    }
}

// Marketplace catalog with generic methods
class Catalog {
    private List<Product<? extends Category>> products;
    
    public Catalog() {
        this.products = new ArrayList<>();
    }
    
    // Generic method to add any product type
    public <T extends Category> void addProduct(Product<T> product) {
        products.add(product);
        System.out.println("Added: " + product);
    }
    
    // Generic method to apply discount
    public <T extends Category> void applyDiscount(Product<T> product, double percentage) {
        double originalPrice = product.getPrice();
        double discountedPrice = originalPrice * (1 - percentage / 100);
        product.setPrice(discountedPrice);
        System.out.println("Discount applied to " + product.getName() + 
                         ": $" + String.format("%.2f", originalPrice) + 
                         " -> $" + String.format("%.2f", discountedPrice));
    }
    
    // Display all products
    public void displayCatalog() {
        System.out.println("\n=== Product Catalog ===");
        for (Product<? extends Category> product : products) {
            System.out.println("- " + product);
        }
    }
    
    // Filter by category
    public <T extends Category> List<Product<T>> filterByCategory(Class<T> categoryClass) {
        List<Product<T>> filtered = new ArrayList<>();
        for (Product<? extends Category> product : products) {
            if (categoryClass.isInstance(product.getCategory())) {
                @SuppressWarnings("unchecked")
                Product<T> typedProduct = (Product<T>) product;
                filtered.add(typedProduct);
            }
        }
        return filtered;
    }
}

// Demo class
public class MarketplaceDemo {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        
        // Create products
        Product<BookCategory> book1 = new Product<>("Java Programming", 45.99, new BookCategory());
        Product<BookCategory> book2 = new Product<>("Design Patterns", 55.00, new BookCategory());
        Product<ClothingCategory> shirt = new Product<>("Cotton T-Shirt", 25.99, new ClothingCategory());
        Product<GadgetCategory> headphones = new Product<>("Wireless Headphones", 89.99, new GadgetCategory());
        
        // Add products to catalog
        catalog.addProduct(book1);
        catalog.addProduct(book2);
        catalog.addProduct(shirt);
        catalog.addProduct(headphones);
        
        // Display catalog
        catalog.displayCatalog();
        
        // Apply discounts using generic method
        System.out.println("\n=== Applying Discounts ===");
        catalog.applyDiscount(book1, 20);  // 20% off
        catalog.applyDiscount(shirt, 15);   // 15% off
        catalog.applyDiscount(headphones, 10); // 10% off
        
        // Display updated catalog
        catalog.displayCatalog();
    }
}