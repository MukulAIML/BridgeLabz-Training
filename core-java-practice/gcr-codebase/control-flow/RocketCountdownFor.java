import java.util.Scanner;

public class RocketCountdownFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter starting countdown value: ");
        int start = scanner.nextInt();

        for (int counter = start; counter >= 1; counter--) {
            System.out.println(counter);
        }

        System.out.println("Launch!");

        scanner.close();
    }
}


