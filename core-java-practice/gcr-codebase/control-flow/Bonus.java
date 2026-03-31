import java.util.Scanner;

public class Bonus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();
        
        System.out.print("Enter years of service: ");
        int year = sc.nextInt();
        
        double bonus = 0.0;
        if (year > 5) {
            bonus = 0.05 * salary;
        }
        
        System.out.println("Bonus amount: " + bonus);
        
        sc.close();
    }
}

