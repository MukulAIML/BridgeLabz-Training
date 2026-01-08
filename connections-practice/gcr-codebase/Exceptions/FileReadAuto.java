import java.io.*;

public class FileReadAutoClose {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("info.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                System.out.println("First line: " + line);
            } else {
                System.out.println("File is empty");
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
}