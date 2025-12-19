import java.util.Scanner;

public class Friends {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter Amar's age: ");
        int amarAge = sc.nextInt();
        System.out.print("Enter Amar's height: ");
        double amarHeight = sc.nextDouble();
        
        System.out.print("Enter Akbar's age: ");
        int akbarAge = sc.nextInt();
        System.out.print("Enter Akbar's height: ");
        double akbarHeight = sc.nextDouble();
        
        System.out.print("Enter Anthony's age: ");
        int anthonyAge = sc.nextInt();
        System.out.print("Enter Anthony's height: ");
        double anthonyHeight = sc.nextDouble();
        
        int youngestAge = amarAge;
        String youngest = "Amar";
        if (akbarAge < youngestAge) {
            youngestAge = akbarAge;
            youngest = "Akbar";
        }
        if (anthonyAge < youngestAge) {
            youngestAge = anthonyAge;
            youngest = "Anthony";
        }
        
        double tallestHeight = amarHeight;
        String tallest = "Amar";
        if (akbarHeight > tallestHeight) {
            tallestHeight = akbarHeight;
            tallest = "Akbar";
        }
        if (anthonyHeight > tallestHeight) {
            tallestHeight = anthonyHeight;
            tallest = "Anthony";
        }
        
        System.out.println("Youngest friend: " + youngest + " (age: " + youngestAge + ")");
        System.out.println("Tallest friend: " + tallest + " (height: " + tallestHeight + ")");
        
        sc.close();
    }
}

