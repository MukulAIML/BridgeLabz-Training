import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.util.Scanner;

public class MergeJSONFiles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter first JSON file path: ");
        String file1Path = sc.nextLine();
        
        System.out.print("Enter second JSON file path: ");
        String file2Path = sc.nextLine();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node1 = mapper.readTree(new File(file1Path));
            JsonNode node2 = mapper.readTree(new File(file2Path));
            
            ObjectNode merged = mapper.createObjectNode();
            merged.setAll((ObjectNode) node1);
            merged.setAll((ObjectNode) node2);
            
            System.out.println("\nMerged JSON:");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(merged));
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
}
