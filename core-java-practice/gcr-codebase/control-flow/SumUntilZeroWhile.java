import java.util.Scanner;

public class SumUntilZeroWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double total = 0.0;
        System.out.print("Enter a number (0 to stop): ");
        double val = scanner.nextDouble();

        while (val != 0.0) {
            total += val;
            System.out.print("Enter a number (0 to stop): ");
            val = scanner.nextDouble();
        }

        System.out.println("Total sum is: " + total);

        scanner.close();
    }
}


