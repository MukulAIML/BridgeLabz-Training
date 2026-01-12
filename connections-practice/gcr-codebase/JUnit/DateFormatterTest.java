import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {
    
    private static final DateTimeFormatter INPUT_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = 
        DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    public String formatDate(String inputDate) {
        if (inputDate == null || inputDate.isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        
        try {
            LocalDate date = LocalDate.parse(inputDate, INPUT_FORMAT);
            return date.format(OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd");
        }
    }
    
    public boolean isValidDate(String inputDate) {
        try {
            LocalDate.parse(inputDate, INPUT_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

// DateFormatterTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class DateFormatterTest {
    
    private DateFormatter formatter;
    
    @BeforeEach
    void setUp() {
        formatter = new DateFormatter();
    }
    
    @Test
    void testValidDateFormatting() {
        assertEquals("25-12-2024", formatter.formatDate("2024-12-25"));
        assertEquals("01-01-2025", formatter.formatDate("2025-01-01"));
        assertEquals("15-06-2023", formatter.formatDate("2023-06-15"));
    }
    
    @ParameterizedTest
    @CsvSource({
        "2024-12-25, 25-12-2024",
        "2025-01-01, 01-01-2025",
        "2023-06-15, 15-06-2023",
        "2000-02-29, 29-02-2000"
    })
    void testDateConversions(String input, String expected) {
        assertEquals(expected, formatter.formatDate(input));
    }
    
    @Test
    void testInvalidDateFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            formatter.formatDate("25-12-2024");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            formatter.formatDate("2024/12/25");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            formatter.formatDate("invalid-date");
        });
    }
    
    @Test
    void testNullDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            formatter.formatDate(null);
        });
    }
    
    @Test
    void testEmptyDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            formatter.formatDate("");
        });
    }
    
    @Test
    void testInvalidDates() {
        assertThrows(IllegalArgumentException.class, () -> {
            formatter.formatDate("2024-13-01"); // Invalid month
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            formatter.formatDate("2024-02-30"); // Invalid day
        });
    }
    
    @Test
    void testIsValidDate() {
        assertTrue(formatter.isValidDate("2024-12-25"));
        assertFalse(formatter.isValidDate("25-12-2024"));
        assertFalse(formatter.isValidDate("invalid"));
    }
}
