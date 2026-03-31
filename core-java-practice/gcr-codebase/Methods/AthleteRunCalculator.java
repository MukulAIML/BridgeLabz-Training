import java.util.Scanner;

public class AthleteRunCalculator {
    public static double calculateRounds(double a, double b, double c) {
        double perimeter = a + b + c;
        return 5000 / perimeter;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter side A (in meters): ");
        double a = sc.nextDouble();
        System.out.print("Enter side B (in meters): ");
        double b = sc.nextDouble();
        System.out.print("Enter side C (in meters): ");
        double c = sc.nextDouble();
        double rounds = calculateRounds(a, b, c);
        System.out.printf("The athlete must complete %.2f rounds to cover 5 km.%n", rounds);
    }
}