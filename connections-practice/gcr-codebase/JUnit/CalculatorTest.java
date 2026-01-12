public class Calculator {
    
    public int add(int a, int b) {
        return a + b;
    }
    
    public int subtract(int a, int b) {
        return a - b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return (double) a / b;
    }
}

// CalculatorTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    
    private Calculator calc;
    
    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }
    
    @Test
    void testAddition() {
        assertEquals(10, calc.add(6, 4));
        assertEquals(0, calc.add(-5, 5));
        assertEquals(-8, calc.add(-3, -5));
    }
    
    @Test
    void testSubtraction() {
        assertEquals(5, calc.subtract(10, 5));
        assertEquals(-10, calc.subtract(5, 15));
        assertEquals(0, calc.subtract(7, 7));
    }
    
    @Test
    void testMultiplication() {
        assertEquals(20, calc.multiply(4, 5));
        assertEquals(-15, calc.multiply(3, -5));
        assertEquals(0, calc.multiply(0, 10));
    }
    
    @Test
    void testDivision() {
        assertEquals(2.0, calc.divide(10, 5));
        assertEquals(2.5, calc.divide(5, 2));
        assertEquals(-3.0, calc.divide(-9, 3));
    }
    
    @Test
    void testDivisionByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calc.divide(10, 0);
        });
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
}
