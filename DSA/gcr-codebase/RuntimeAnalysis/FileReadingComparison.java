import java.io.*;

public class FileReadingComparison {
    
    public static void readWithFileReader(String filename) throws IOException {
        FileReader reader = new FileReader(filename);
        int character;
        while ((character = reader.read()) != -1) {
            // Process character
        }
        reader.close();
    }
    
    public static void readWithInputStreamReader(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis);
        int character;
        while ((character = isr.read()) != -1) {
            // Process character
        }
        isr.close();
        fis.close();
    }
    
    public static void createTestFile(String filename, int sizeMB) throws IOException {
        FileWriter writer = new FileWriter(filename);
        String content = "This is a test line for file reading comparison.\n";
        int lines = (sizeMB * 1024 * 1024) / content.length();
        for (int i = 0; i < lines; i++) {
            writer.write(content);
        }
        writer.close();
    }
    
    public static void main(String[] args) {
        try {
            int[] sizes = {1, 100, 500};
            
            for (int size : sizes) {
                String filename = "test_" + size + "MB.txt";
                System.out.println("File Size: " + size + "MB");
                
                // Create test file
                createTestFile(filename, size);
                
                // FileReader
                long start = System.nanoTime();
                readWithFileReader(filename);
                long time = (System.nanoTime() - start) / 1_000_000;
                System.out.println("FileReader: " + time + "ms");
                
                // InputStreamReader
                start = System.nanoTime();
                readWithInputStreamReader(filename);
                time = (System.nanoTime() - start) / 1_000_000;
                System.out.println("InputStreamReader: " + time + "ms");
                
                // Clean up
                new File(filename).delete();
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

