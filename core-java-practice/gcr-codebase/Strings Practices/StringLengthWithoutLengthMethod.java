import java.util.Scanner;

public class StringLengthWithoutLengthMethod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.next();

        int length = 0;
        try {
            while (true) {
                text.charAt(length);
                length++;
            }
        } catch (Exception e) {
        }

        System.out.println("Calculated length: " + length);
        System.out.println("Built-in length: " + text.length());
        sc.close();
    }
}
