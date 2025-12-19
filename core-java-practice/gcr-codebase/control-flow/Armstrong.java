import java.util.Scanner;

public class Armstrong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        
        int sum = 0;
        int originalNumber = number;
        
        while (originalNumber != 0) {
            int digit = originalNumber % 10;
            int cube = digit * digit * digit;
            sum = sum + cube;
            originalNumber = originalNumber / 10;
        }
        
        if (number == sum) {
            System.out.println(number + " is an Armstrong number");
        } else {
            System.out.println(number + " is not an Armstrong number");
        }
        
        sc.close();
    }
}

