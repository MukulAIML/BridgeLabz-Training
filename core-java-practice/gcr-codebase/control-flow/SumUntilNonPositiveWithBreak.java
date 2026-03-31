import java.util.Scanner;

public class SumUntilNonPositiveWithBreak {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double net = 0.0;

        while (true) {
            System.out.print("Enter a number (0 or negative to stop): ");
            double value = scanner.nextDouble();

            if (value <= 0.0) {
                break;
            }

            net += value;
        }

        System.out.println("Total sum is: " + net);

        scanner.close();
    }
}


