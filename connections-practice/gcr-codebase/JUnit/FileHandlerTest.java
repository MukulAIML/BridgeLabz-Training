import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProcessor {
    
    public void writeToFile(String filename, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        }
    }
    
    public String readFromFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }
    
    public boolean fileExists(String filename) {
        return new File(filename).exists();
    }
}

// FileProcessorTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;

class FileProcessorTest {
    
    private FileProcessor fileProcessor;
    private final String testFilename = "test_file.txt";
    
    @BeforeEach
    void setUp() {
        fileProcessor = new FileProcessor();
    }
    
    @AfterEach
    void tearDown() {
        File file = new File(testFilename);
        if (file.exists()) {
            file.delete();
        }
    }
    
    @Test
    void testWriteAndReadFile() throws IOException {
        String content = "Hello JUnit Testing!";
        
        fileProcessor.writeToFile(testFilename, content);
        String readContent = fileProcessor.readFromFile(testFilename);
        
        assertEquals(content, readContent);
    }
    
    @Test
    void testFileExistsAfterWriting() throws IOException {
        fileProcessor.writeToFile(testFilename, "Test content");
        
        assertTrue(fileProcessor.fileExists(testFilename));
    }
    
    @Test
    void testReadNonExistentFile() {
        assertThrows(IOException.class, () -> {
            fileProcessor.readFromFile("nonexistent_file.txt");
        });
    }
    
    @Test
    void testWriteEmptyContent() throws IOException {
        fileProcessor.writeToFile(testFilename, "");
        String content = fileProcessor.readFromFile(testFilename);
        
        assertEquals("", content);
        assertTrue(fileProcessor.fileExists(testFilename));
    }
}
