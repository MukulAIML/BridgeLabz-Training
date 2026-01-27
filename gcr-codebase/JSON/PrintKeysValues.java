import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class PrintKeysValues {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter JSON file path: ");
        String filePath = sc.nextLine();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(filePath));
            
            System.out.println("\nKeys and Values:");
            printKeysAndValues(rootNode, "");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
    
    private static void printKeysAndValues(JsonNode node, String prefix) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
                
                if (entry.getValue().isValueNode()) {
                    System.out.println(key + " = " + entry.getValue().asText());
                } else {
                    printKeysAndValues(entry.getValue(), key);
                }
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                printKeysAndValues(node.get(i), prefix + "[" + i + "]");
            }
        }
    }
}
