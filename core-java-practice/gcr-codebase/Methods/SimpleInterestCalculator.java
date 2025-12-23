import java.util.Scanner;

public class SimpleInterestCalculator {
    public static double calculateSI(double principal, double rate, double time) {
        return (principal * rate * time) / 100;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Principal: ");
        double p = sc.nextDouble();
        System.out.print("Enter Rate: ");
        double r = sc.nextDouble();
        System.out.print("Enter Time: ");
        double t = sc.nextDouble();
        double si = calculateSI(p, r, t);
        System.out.printf("The Simple Interest is %.2f for Principal %.2f, Rate %.2f%% and Time %.2f years%n", si, p, r, t);
    }
}