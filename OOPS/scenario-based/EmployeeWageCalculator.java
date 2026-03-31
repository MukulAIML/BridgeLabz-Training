public class EmployeeWageCalculator {
    
    public static char getEmployeeStatus() {
        if (Math.random() > 0.5) {
            return 'P';
        } else {
            return 'A';
        }
    }
    
    public static boolean isFullShift() {
        return Math.random() > 0.5;
    }
    
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program");
        
        int ratePerHour = 20;
        int hoursFullDay = 8;
        int hoursHalfDay = 4;
        int maxDays = 20;
        int maxHours = 100;
        
        int NetHours = 0;
        int attendedDays = 0;
        int dayCount = 0;
        
        for (int i = 1; i <= maxDays; i++) {
            dayCount = i;
            
            if (NetHours >= maxHours) {
                break;
            }
            
            char status = getEmployeeStatus();
            
            if (status == 'P') {
                attendedDays = attendedDays + 1;
                if (isFullShift()) {
                    NetHours += hoursFullDay;
                    System.out.println("Day " + dayCount + " present full-time.");
                } else {
                    NetHours += hoursHalfDay;
                    System.out.println("Day " + dayCount + " present part-time.");
                }
            } else if (status == 'A') {
                System.out.println("Day " + dayCount + " absent.");
            }
        }
        
        int salary = ratePerHour * NetHours;
        
        System.out.println("--------------");
        System.out.println("Hourly Wage: " + ratePerHour);
        System.out.println("Number of days worked: " + attendedDays);
        System.out.println("Number of hours worked: " + NetHours);
        System.out.println("Wage: " + salary);
    }
}