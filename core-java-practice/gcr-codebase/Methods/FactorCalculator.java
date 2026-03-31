import java.util.ArrayList;
import java.util.Scanner;

public class FactorCalculator {

    public static int[] getFactors(int number) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) list.add(i);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static int sumOfFactors(int[] factors) {
        int sum = 0;
        for (int i : factors) sum += i;
        return sum;
    }

    public static int productOfFactors(int[] factors) {
        int product = 1;
        for (int i : factors) product *= i;
        return product;
    }

    public static double sumOfSquares(int[] factors) {
        double sum = 0;
        for (int i : factors) sum += Math.pow(i, 2);
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        int[] factors = getFactors(number);
        System.out.print("Factors: ");
        for (int f : factors) System.out.print(f + " ");
        System.out.println("\nSum: " + sumOfFactors(factors));
        System.out.println("Product: " + productOfFactors(factors));
        System.out.printf("Sum of squares: %.2f%n", sumOfSquares(factors));
    }
}