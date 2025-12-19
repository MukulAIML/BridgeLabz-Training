import java.util.Scanner;

public class DivisibleByFive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int num = scanner.nextInt();

        boolean isDivisible = (num % 5 == 0);

        System.out.println("Is the number " + num + " divisible by 5? " + isDivisible);

        scanner.close();
    }
}


