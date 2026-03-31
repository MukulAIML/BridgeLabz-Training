public class DayOfWeekCalculator {
    public static String getDayOfWeek(int d, int m, int y) {
        int y0 = y - (14 - m) / 12;
        int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
        int m0 = m + 12 * ((14 - m) / 12) - 2;
        int d0 = (d + x + (31 * m0) / 12) % 7;
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[d0];
    }

    public static void main(String[] args) {
        int d = 19, m = 6, y = 2025;
        System.out.println("Day of week: " + getDayOfWeek(d, m, y));
    }
}