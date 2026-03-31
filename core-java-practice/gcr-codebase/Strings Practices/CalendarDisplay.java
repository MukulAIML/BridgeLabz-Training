import java.util.Scanner;

/**
 * Program to display a calendar for a given month and year
 */
public class CalendarDisplay {
    
    private static final String[] MONTH_NAMES = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    
    private static final int[] DAYS_IN_MONTH = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };
    
    private static final String[] DAY_NAMES = {
        "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };
    
    /**
     * Gets the name of the month
     * @param month Month number (1-12)
     * @return Month name
     */
    public static String getMonthName(int month) {
        if (month >= 1 && month <= 12) {
            return MONTH_NAMES[month - 1];
        }
        return "Invalid Month";
    }
    
    /**
     * Checks if a year is a leap year
     * @param year Year to check
     * @return true if leap year, false otherwise
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
    /**
     * Gets the number of days in a month
     * @param month Month number (1-12)
     * @param year Year
     * @return Number of days in the month
     */
    public static int getDaysInMonth(int month, int year) {
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        if (month >= 1 && month <= 12) {
            return DAYS_IN_MONTH[month - 1];
        }
        return 0;
    }
    
    /**
     * Gets the first day of the month using Gregorian calendar algorithm
     * @param day Day (1)
     * @param month Month (1-12)
     * @param year Year
     * @return Day of week (0=Sunday, 1=Monday, ..., 6=Saturday)
     */
    public static int getFirstDayOfMonth(int day, int month, int year) {
        int y0 = year - (14 - month) / 12;
        int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
        int m0 = month + 12 * ((14 - month) / 12) - 2;
        int d0 = (day + x + 31 * m0 / 12) % 7;
        return d0;
    }
    
    /**
     * Displays the calendar for the given month and year
     * @param month Month number (1-12)
     * @param year Year
     */
    public static void displayCalendar(int month, int year) {
        String monthName = getMonthName(month);
        int daysInMonth = getDaysInMonth(month, year);
        int firstDay = getFirstDayOfMonth(1, month, year);
        
        System.out.println("\n========================================");
        System.out.println("         " + monthName + " " + year);
        System.out.println("========================================");
        
        for (int i = 0; i < DAY_NAMES.length; i++) {
            System.out.printf("%4s", DAY_NAMES[i]);
        }
        System.out.println();
        
        for (int i = 0; i < firstDay; i++) {
            System.out.printf("%4s", " ");
        }
        
        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%4d", day);
            if ((firstDay + day) % 7 == 0) {
                System.out.println();
            }
        }
        
        System.out.println("\n========================================\n");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Enter month (1-12): ");
            int month = sc.nextInt();
            
            System.out.print("Enter year: ");
            int year = sc.nextInt();
            
            if (month < 1 || month > 12) {
                System.out.println("Error: Month must be between 1 and 12.");
                return;
            }
            
            if (year < 1) {
                System.out.println("Error: Year must be a positive number.");
                return;
            }
            
            displayCalendar(month, year);
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter valid numbers.");
        } finally {
            sc.close();
        }
    }
}

