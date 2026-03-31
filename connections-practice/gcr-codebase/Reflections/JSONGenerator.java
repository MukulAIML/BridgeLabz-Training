import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * ADVANCED LEVEL - Problem 2: Generate JSON Representation
 * Convert objects to JSON-like strings using Reflection
 */
class Product {
    private String name;
    private double price;
    private int quantity;
    private boolean inStock;
    
    public Product(String name, double price, int quantity, boolean inStock) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.inStock = inStock;
    }
}

public class JSONGenerator {
    
    public static String toJSON(Object obj) {
        StringBuilder json = new StringBuilder("{");
        
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(obj);
                
                json.append("\"").append(fields[i].getName()).append("\":");
                
                if (value instanceof String) {
                    json.append("\"").append(value).append("\"");
                } else if (value instanceof Boolean) {
                    json.append(value);
                } else {
                    json.append(value);
                }
                
                if (i < fields.length - 1) {
                    json.append(",");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        json.append("}");
        return json.toString();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        
        System.out.print("In stock (true/false): ");
        boolean inStock = sc.nextBoolean();
        
        Product product = new Product(name, price, quantity, inStock);
        String json = toJSON(product);
        
        System.out.println("\nGenerated JSON:");
        System.out.println(json);
        
        sc.close();
    }
}
