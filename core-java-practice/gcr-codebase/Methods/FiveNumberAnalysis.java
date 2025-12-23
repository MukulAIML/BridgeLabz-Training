import java.util.Scanner;

public class FiveNumberAnalysis {

    public static boolean isPositive(int n) { return n >= 0; }
    public static boolean isEven(int n) { return n % 2 == 0; }
    public static int compare(int a, int b) {
        return Integer.compare(a, b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            nums[i] = sc.nextInt();
            if (isPositive(nums[i])) {
                System.out.println("Positive and " + (isEven(nums[i]) ? "Even" : "Odd"));
            } else {
                System.out.println("Negative");
            }
        }
        int cmp = compare(nums[0], nums[4]);
        if (cmp == 0) System.out.println("First and last are equal.");
        else if (cmp > 0) System.out.println("First is greater than last.");
        else System.out.println("First is less than last.");
    }
}