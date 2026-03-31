public class UnitConverter1 {

    public static double convertYardsToFeet(double yards) { return yards * 3; }
    public static double convertFeetToYards(double feet) { return feet * 0.333333; }
    public static double convertMetersToInches(double meters) { return meters * 39.3701; }
    public static double convertInchesToMeters(double inches) { return inches * 0.0254; }
    public static double convertInchesToCentimeters(double inches) { return inches * 2.54; }

    public static void main(String[] args) {
        System.out.println("10 yards to feet: " + convertYardsToFeet(10));
        System.out.println("30 feet to yards: " + convertFeetToYards(30));
        System.out.println("1 meter to inches: " + convertMetersToInches(1));
        System.out.println("12 inches to meters: " + convertInchesToMeters(12));
        System.out.println("12 inches to cm: " + convertInchesToCentimeters(12));
    }
}