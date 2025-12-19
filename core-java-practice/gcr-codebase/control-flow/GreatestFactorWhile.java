import java.util.Scanner;

public class GreatestFactorWhile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        
        int greatestFactor = 1;
        int counter = n - 1;
        
        while (counter >= 1) {
            if (n % counter == 0) {
                greatestFactor = counter;
                break;
            }
            counter--;
        }
        
        System.out.println("Greatest factor of " + n + " (beside itself): " + greatestFactor);
        
        sc.close();
    }
}

