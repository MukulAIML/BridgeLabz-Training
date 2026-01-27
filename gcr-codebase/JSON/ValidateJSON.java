import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;

public class ValidateJSON {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter JSON string to validate:");
        String jsonInput = sc.nextLine();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonInput);
            
            System.out.println("✓ JSON is valid!");
            System.out.println("\nParsed JSON:");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
            
            System.out.println("\nJSON Type: " + node.getNodeType());
            System.out.println("Is Object: " + node.isObject());
            System.out.println("Is Array: " + node.isArray());
            
        } catch (Exception e) {
            System.err.println("✗ JSON is invalid!");
            System.err.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
}
