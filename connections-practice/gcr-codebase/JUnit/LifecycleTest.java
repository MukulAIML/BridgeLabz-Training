public class DatabaseConnection {
    
    private boolean connected = false;
    
    public void connect() {
        if (!connected) {
            connected = true;
            System.out.println("Database connected successfully");
        }
    }
    
    public void disconnect() {
        if (connected) {
            connected = false;
            System.out.println("Database disconnected successfully");
        }
    }
    
    public boolean isConnected() {
        return connected;
    }
    
    public String fetchData() {
        if (connected) {
            return "Sample Data from Database";
        }
        return null;
    }
}

// DatabaseConnectionTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {
    
    private DatabaseConnection dbConnection;
    
    @BeforeEach
    void setUp() {
        dbConnection = new DatabaseConnection();
        dbConnection.connect();
        System.out.println("Setup: Connection established");
    }
    
    @AfterEach
    void tearDown() {
        dbConnection.disconnect();
        System.out.println("Teardown: Connection closed");
    }
    
    @Test
    void testConnectionIsEstablished() {
        assertTrue(dbConnection.isConnected());
    }
    
    @Test
    void testFetchDataWhenConnected() {
        String data = dbConnection.fetchData();
        assertNotNull(data);
        assertEquals("Sample Data from Database", data);
    }
    
    @Test
    void testMultipleOperations() {
        assertTrue(dbConnection.isConnected());
        assertNotNull(dbConnection.fetchData());
        assertTrue(dbConnection.isConnected());
    }
}
