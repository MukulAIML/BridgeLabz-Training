import java.util.Scanner;

public class DigitFrequencyCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] freq = new int[10];
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        while (n > 0) {
            freq[n % 10]++;
            n /= 10;
        }

        for (int i = 0; i < 10; i++) {
            if (freq[i] > 0) {
                System.out.println("Digit " + i + ": " + freq[i] + " times");
            }
        }
    }
}