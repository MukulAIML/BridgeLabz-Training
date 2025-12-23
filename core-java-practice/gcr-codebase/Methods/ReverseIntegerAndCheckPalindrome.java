import java.util.Scanner;

public class ReverseIntegerAndCheckPalindrome {
    public static boolean isPalindrome(int number) {
        int original = number, reverse = 0;
        while (number != 0) {
            int digit = number % 10;
            reverse = reverse * 10 + digit;
            number /= 10;
        }
        return original == reverse;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        boolean result = isPalindrome(num);
        System.out.println("Is palindrome: " + result);
    }
}