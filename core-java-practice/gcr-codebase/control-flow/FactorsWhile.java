import java.util.Scanner;

public class FactorsWhile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        
        if (n <= 0) {
            System.out.println("Please enter a positive integer");
        } else {
            System.out.println("Factors of " + n + ":");
            int counter = 1;
            while (counter < n) {
                if (n % counter == 0) {
                    System.out.println(counter);
                }
                counter++;
            }
        }
        
        sc.close();
    }
}

