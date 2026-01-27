import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Scanner;

public class ReadSpecificFields {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter JSON file path: ");
        String filePath = sc.nextLine();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(filePath));
            
            System.out.println("\nExtracting name and email:");
            
            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    String name = node.has("name") ? node.get("name").asText() : "N/A";
                    String email = node.has("email") ? node.get("email").asText() : "N/A";
                    System.out.println("Name: " + name + ", Email: " + email);
                }
            } else {
                String name = rootNode.has("name") ? rootNode.get("name").asText() : "N/A";
                String email = rootNode.has("email") ? rootNode.get("email").asText() : "N/A";
                System.out.println("Name: " + name + ", Email: " + email);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        sc.close();
    }
}
