import java.util.Scanner;

public class LargestTwoDigitsDynamic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        int maxDigits = 10;
        int[] digits = new int[maxDigits];
        int index = 0;

        while (number > 0) {
            if (index == maxDigits) {
                maxDigits *= 2;
                int[] temp = new int[maxDigits];
                for (int i = 0; i < digits.length; i++)
                    temp[i] = digits[i];
                digits = temp;
            }
            digits[index++] = number % 10;
            number /= 10;
        }

        int first = 0, second = 0;
        for (int i = 0; i < index; i++) {
            if (digits[i] > first) {
                second = first;
                first = digits[i];
            } else if (digits[i] > second && digits[i] != first) {
                second = digits[i];
            }
        }

        System.out.println("Largest: " + first);
        System.out.println("Second Largest: " + second);
    }
}