public class PasswordValidator {
    
    public boolean isValid(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUppercase = false;
        boolean hasDigit = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        
        return hasUppercase && hasDigit;
    }
    
    public String validateAndGetMessage(String password) {
        if (password == null) {
            return "Password cannot be null";
        }
        if (password.length() < 8) {
            return "Password must be at least 8 characters long";
        }
        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter";
        }
        if (!password.matches(".*\\d.*")) {
            return "Password must contain at least one digit";
        }
        return "Password is valid";
    }
}

// PasswordValidatorTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    
    private PasswordValidator validator;
    
    @BeforeEach
    void setUp() {
        validator = new PasswordValidator();
    }
    
    @Test
    void testValidPassword() {
        assertTrue(validator.isValid("Password123"));
        assertTrue(validator.isValid("MyPass99"));
        assertTrue(validator.isValid("Secure1234"));
    }
    
    @Test
    void testPasswordTooShort() {
        assertFalse(validator.isValid("Pass1"));
        assertFalse(validator.isValid("Ab12"));
    }
    
    @Test
    void testPasswordWithoutUppercase() {
        assertFalse(validator.isValid("password123"));
        assertFalse(validator.isValid("mypass999"));
    }
    
    @Test
    void testPasswordWithoutDigit() {
        assertFalse(validator.isValid("Password"));
        assertFalse(validator.isValid("MyPasswordTest"));
    }
    
    @Test
    void testNullPassword() {
        assertFalse(validator.isValid(null));
    }
    
    @Test
    void testValidationMessages() {
        assertEquals("Password is valid", 
            validator.validateAndGetMessage("Password123"));
        assertEquals("Password must be at least 8 characters long", 
            validator.validateAndGetMessage("Pass1"));
        assertEquals("Password must contain at least one uppercase letter", 
            validator.validateAndGetMessage("password123"));
        assertEquals("Password must contain at least one digit", 
            validator.validateAndGetMessage("Password"));
        assertEquals("Password cannot be null", 
            validator.validateAndGetMessage(null));
    }
}
