import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.*;
import java.util.Scanner;

public class IPLCensorAnalyzer {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== IPL Censor Analyzer ===");
        System.out.println("1. Process JSON file");
        System.out.println("2. Process CSV file");
        System.out.print("Choose option: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter input file path: ");
        String inputPath = sc.nextLine();
        
        System.out.print("Enter output file path: ");
        String outputPath = sc.nextLine();
        
        try {
            if (choice == 1) {
                processJSON(inputPath, outputPath);
            } else if (choice == 2) {
                processCSV(inputPath, outputPath);
            } else {
                System.out.println("Invalid option!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
    
    private static void processJSON(String inputPath, String outputPath) throws Exception {
        JsonNode rootNode = mapper.readTree(new File(inputPath));
        ArrayNode censoredArray = mapper.createArrayNode();
        
        for (JsonNode match : rootNode) {
            ObjectNode censoredMatch = mapper.createObjectNode();
            
            censoredMatch.put("match_id", match.get("match_id").asInt());
            censoredMatch.put("team1", censorTeamName(match.get("team1").asText()));
            censoredMatch.put("team2", censorTeamName(match.get("team2").asText()));
            
            ObjectNode censoredScore = mapper.createObjectNode();
            JsonNode scoreNode = match.get("score");
            scoreNode.fields().forEachRemaining(entry -> {
                String teamName = censorTeamName(entry.getKey());
                censoredScore.put(teamName, entry.getValue().asInt());
            });
            censoredMatch.set("score", censoredScore);
            
            censoredMatch.put("winner", censorTeamName(match.get("winner").asText()));
            censoredMatch.put("player_of_match", "REDACTED");
            
            censoredArray.add(censoredMatch);
        }
        
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), censoredArray);
        System.out.println("✓ Censored JSON saved to: " + outputPath);
    }
    
    private static void processCSV(String inputPath, String outputPath) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputPath));
        PrintWriter pw = new PrintWriter(new FileWriter(outputPath));
        
        String headerLine = br.readLine();
        pw.println(headerLine);
        
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            
            parts[1] = censorTeamName(parts[1]);
            parts[2] = censorTeamName(parts[2]);
            parts[5] = censorTeamName(parts[5]);
            parts[6] = "REDACTED";
            
            pw.println(String.join(",", parts));
        }
        
        br.close();
        pw.close();
        System.out.println("✓ Censored CSV saved to: " + outputPath);
    }
    
    private static String censorTeamName(String teamName) {
        String[] words = teamName.split(" ");
        if (words.length > 1) {
            return words[0] + " ***";
        }
        return teamName.substring(0, Math.min(3, teamName.length())) + "***";
    }
}