import java.util.Scanner;

public class ArmstrongNumberChecker {
    public static boolean isArmstrong(int num) {
        int temp = num, sum = 0, digits = String.valueOf(num).length();
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, digits);
            temp /= 10;
        }
        return sum == num;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        System.out.println(n + (isArmstrong(n) ? " is " : " is not ") + "an Armstrong number.");
    }
}