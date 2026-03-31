import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputToFile {
    
    public static void writeUserInputToFile(String filePath) {
        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             FileWriter fileWriter = new FileWriter(filePath)) {
            
            String input;
            System.out.println("Enter text (type 'exit' to stop):");
            
            while (true) {
                input = bufferedReader.readLine();
                if (input == null || input.equalsIgnoreCase("exit")) {
                    break;
                }
                fileWriter.write(input + "\n");
            }
            
            System.out.println("Input saved to file: " + filePath);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        String filePath = "userInput.txt";
        writeUserInputToFile(filePath);
    }
}

