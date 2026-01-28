// Cloning Prototype Objects using Cloneable Marker Interface
import java.util.Scanner;

class Product implements Cloneable {
    private String name;
    private double price;
    private int quantity;
    
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported");
            return null;
        }
    }
    
    public String toString() {
        return "Product: " + name + ", Price: ₹" + price + ", Quantity: " + quantity;
    }
}

class Document implements Cloneable {
    private String title;
    private String content;
    
    public Document(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public Document clone() {
        try {
            return (Document) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported");
            return null;
        }
    }
    
    public String toString() {
        return "Document: " + title + "\nContent: " + content;
    }
}

public class CloningPrototypeObjects {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        
        System.out.print("Enter product price: ₹");
        double price = scanner.nextDouble();
        
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        
        Product original = new Product(productName, price, quantity);
        Product clone1 = original.clone();
        Product clone2 = original.clone();
        
        clone1.setQuantity(50);
        clone2.setPrice(price + 100);
        
        System.out.println("\nOriginal: " + original);
        System.out.println("Clone 1: " + clone1);
        System.out.println("Clone 2: " + clone2);
        
        scanner.nextLine(); // consume newline
        
        System.out.print("\nEnter document title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter content: ");
        String content = scanner.nextLine();
        
        Document doc = new Document(title, content);
        Document docClone = doc.clone();
        
        docClone.setContent("Modified: " + content);
        
        System.out.println("\n" + doc);
        System.out.println("\n" + docClone);
        
        scanner.close();
    }
}
