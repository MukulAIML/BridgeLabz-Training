import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Problem 3: Date Formatting
 * This program displays the current date in three different formats:
 * - dd/MM/yyyy
 * - yyyy-MM-dd
 * - EEE, MMM dd, yyyy
 */
public class DateFormatting {
    
    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy");
        
        System.out.println("Current date in different formats:\n");
        System.out.println("dd/MM/yyyy format: " + currentDate.format(formatter1));
        System.out.println("yyyy-MM-dd format: " + currentDate.format(formatter2));
        System.out.println("EEE, MMM dd, yyyy format: " + currentDate.format(formatter3));
    }
}

