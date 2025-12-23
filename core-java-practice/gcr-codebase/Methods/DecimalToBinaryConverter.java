import java.util.Scanner;

public class DecimalToBinaryConverter {
    public static String decimalToBinary(int num) {
        return Integer.toBinaryString(num);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter decimal number: ");
        int num = sc.nextInt();
        System.out.println("Binary: " + decimalToBinary(num));
    }
}