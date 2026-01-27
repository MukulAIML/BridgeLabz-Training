import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.Scanner;

public class FilterJSONByAge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter JSON array (with age field):");
        String jsonInput = sc.nextLine();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonInput);
            ArrayNode filteredArray = mapper.createArrayNode();
            
            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    if (node.has("age") && node.get("age").asInt() > 25) {
                        filteredArray.add(node);
                    }
                }
            }
            
            System.out.println("\nFiltered records (age > 25):");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(filteredArray));
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
}
