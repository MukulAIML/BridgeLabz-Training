public class MathOperations {
    
    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return (double) a / b;
    }
}

// MathOperationsTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class MathOperationsTest {
    
    private MathOperations mathOps;
    
    @BeforeEach
    void setUp() {
        mathOps = new MathOperations();
    }
    
    @Test
    void testDivideNormal() {
        assertEquals(5.0, mathOps.divide(10, 2));
        assertEquals(2.5, mathOps.divide(5, 2));
    }
    
    @Test
    void testDivideByZeroThrowsException() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            mathOps.divide(10, 0);
        });
        
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    @Test
    void testDivideByZeroWithNegativeNumber() {
        assertThrows(ArithmeticException.class, () -> {
            mathOps.divide(-10, 0);
        });
    }
}
