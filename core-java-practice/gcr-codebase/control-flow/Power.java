import java.util.Scanner;

public class Power {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number: ");
        int number = sc.nextInt();
        
        System.out.print("Enter power: ");
        int p = sc.nextInt();
        
        if (number <= 0 || p <= 0) {
            System.out.println("Please enter positive integers");
        } else {
            int result = 1;
            for (int i = 1; i <= p; i++) {
                result = result * number;
            }
            System.out.println(number + " raised to the power " + p + " = " + result);
        }
        
        sc.close();
    }
}

