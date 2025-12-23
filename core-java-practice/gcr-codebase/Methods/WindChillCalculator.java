public class WindChillCalculator {
    public static double calculateWindChill(double temperature, double windSpeed) {
        return 35.74 + 0.6215 * temperature - 35.75 * Math.pow(windSpeed, 0.16)
               + 0.4275 * temperature * Math.pow(windSpeed, 0.16);
    }

    public static void main(String[] args) {
        double temp = 30.0;
        double windSpeed = 10.0;
        double windChill = calculateWindChill(temp, windSpeed);
        System.out.printf("Wind Chill Temperature: %.2f°F%n", windChill);
    }
}