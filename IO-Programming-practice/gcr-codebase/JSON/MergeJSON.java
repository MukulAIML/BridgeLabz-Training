import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Scanner;

public class MergeJSON {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter first JSON object:");
        String json1 = sc.nextLine();
        
        System.out.println("Enter second JSON object:");
        String json2 = sc.nextLine();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node1 = mapper.readTree(json1);
            JsonNode node2 = mapper.readTree(json2);
            
            ObjectNode merged = mapper.createObjectNode();
            merged.setAll((ObjectNode) node1);
            merged.setAll((ObjectNode) node2);
            
            String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(merged);
            System.out.println("\nMerged JSON:");
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Error merging JSON: " + e.getMessage());
        }
        
        sc.close();
    }
}
