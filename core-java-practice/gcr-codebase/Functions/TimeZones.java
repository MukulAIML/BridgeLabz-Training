import java.time.ZonedDateTime;
import java.time.ZoneId;

/**
 * Problem 1: Time Zones and ZonedDateTime
 * This program displays the current time in different time zones:
 * - GMT (Greenwich Mean Time)
 * - IST (Indian Standard Time)
 * - PST (Pacific Standard Time)
 */
public class TimeZones {
    
    public static void main(String[] args) {
        System.out.println("Current time in different time zones:\n");
        
        ZonedDateTime gmtTime = ZonedDateTime.now(ZoneId.of("GMT"));
        ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime pstTime = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        
        System.out.println("GMT (Greenwich Mean Time): " + gmtTime);
        System.out.println("IST (Indian Standard Time): " + istTime);
        System.out.println("PST (Pacific Standard Time): " + pstTime);
    }
}

