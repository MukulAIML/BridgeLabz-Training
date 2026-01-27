import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class JSONToXML {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter JSON string:");
        String jsonInput = sc.nextLine();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonInput);
            
            System.out.println("\nXML Output:");
            System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            System.out.println("<root>");
            convertToXML(rootNode, 1);
            System.out.println("</root>");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
    
    private static void convertToXML(JsonNode node, int indent) {
        String indentStr = "  ".repeat(indent);
        
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String key = entry.getKey();
                JsonNode value = entry.getValue();
                
                if (value.isValueNode()) {
                    System.out.println(indentStr + "<" + key + ">" + value.asText() + "</" + key + ">");
                } else {
                    System.out.println(indentStr + "<" + key + ">");
                    convertToXML(value, indent + 1);
                    System.out.println(indentStr + "</" + key + ">");
                }
            }
        } else if (node.isArray()) {
            for (JsonNode item : node) {
                System.out.println(indentStr + "<item>");
                convertToXML(item, indent + 1);
                System.out.println(indentStr + "</item>");
            }
        }
    }
}
