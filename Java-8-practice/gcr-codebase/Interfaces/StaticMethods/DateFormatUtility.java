// Date Format Utility with Static Interface Method
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

interface DateFormatter {
    static String formatDate(LocalDate date, String format) {
        DateTimeFormatter formatter;
        
        switch (format.toUpperCase()) {
            case "DD-MM-YYYY":
                formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                break;
            case "MM/DD/YYYY":
                formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                break;
            case "YYYY-MM-DD":
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                break;
            case "DD MMM YYYY":
                formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
                break;
            default:
                formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        }
        
        return date.format(formatter);
    }
}

public class DateFormatUtility {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter month (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter day: ");
        int day = scanner.nextInt();
        
        LocalDate date = LocalDate.of(year, month, day);
        
        System.out.println("\nFormatted Dates:");
        System.out.println("DD-MM-YYYY: " + DateFormatter.formatDate(date, "DD-MM-YYYY"));
        System.out.println("MM/DD/YYYY: " + DateFormatter.formatDate(date, "MM/DD/YYYY"));
        System.out.println("YYYY-MM-DD: " + DateFormatter.formatDate(date, "YYYY-MM-DD"));
        System.out.println("DD MMM YYYY: " + DateFormatter.formatDate(date, "DD MMM YYYY"));
        
        scanner.close();
    }
}
