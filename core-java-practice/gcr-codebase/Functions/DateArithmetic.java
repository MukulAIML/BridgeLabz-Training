import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Problem 2: Date Arithmetic
 * This program takes a date input and:
 * - Adds 7 days, 1 month, and 2 years to it
 * - Then subtracts 3 weeks from the result
 */
public class DateArithmetic {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a date (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();
        
        LocalDate inputDate = LocalDate.parse(dateInput);
        System.out.println("Original date: " + inputDate);
        
        LocalDate afterAddition = inputDate
            .plusDays(7)
            .plusMonths(1)
            .plusYears(2);
        
        System.out.println("After adding 7 days, 1 month, and 2 years: " + afterAddition);
        
        LocalDate finalDate = afterAddition.minusWeeks(3);
        System.out.println("After subtracting 3 weeks: " + finalDate);
        
        scanner.close();
    }
}

