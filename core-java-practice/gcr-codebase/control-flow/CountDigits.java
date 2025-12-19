import java.util.Scanner;

public class CountDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        
        int count = 0;
        int temp = number;
        
        while (temp != 0) {
            temp = temp / 10;
            count++;
        }
        
        System.out.println("Number of digits in " + number + " is " + count);
        
        sc.close();
    }
}

