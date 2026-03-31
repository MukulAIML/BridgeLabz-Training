import java.util.Scanner;

public class EmployeeBonusCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        System.out.print("Enter years of service: ");
        int years = scanner.nextInt();

        double bonus = 0.0;
        if (years > 5) {
            bonus = 0.05 * salary;
        }

        System.out.println("Bonus amount: " + bonus);

        scanner.close();
    }
}


