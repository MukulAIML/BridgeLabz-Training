import java.util.Scanner;

public class OddEvenUpToN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a natural number: ");
        int num = scanner.nextInt();

        if (num <= 0) {
            System.out.println("The number " + num + " is not a natural number");
        } else {
            for (int i = 1; i <= num; i++) {
                if (i % 2 == 0) {
                    System.out.println(i + " is an even number");
                } else {
                    System.out.println(i + " is an odd number");
                }
            }
        }

        scanner.close();
    }
}


