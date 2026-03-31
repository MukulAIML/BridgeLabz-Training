import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class CSVToJSON {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter CSV file path: ");
        String filePath = sc.nextLine();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode jsonArray = mapper.createArrayNode();
            
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String headerLine = br.readLine();
            String[] headers = headerLine.split(",");
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                ObjectNode jsonObject = mapper.createObjectNode();
                
                for (int i = 0; i < headers.length && i < values.length; i++) {
                    jsonObject.put(headers[i].trim(), values[i].trim());
                }
                jsonArray.add(jsonObject);
            }
            br.close();
            
            System.out.println("\nJSON Output:");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonArray));
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
}
