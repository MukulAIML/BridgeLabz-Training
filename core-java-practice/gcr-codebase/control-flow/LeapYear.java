import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter year: ");
        int year = sc.nextInt();
        
        if (year < 1582) {
            System.out.println("Year must be >= 1582");
        } else {
            if (year % 400 == 0) {
                System.out.println("Year " + year + " is a Leap Year");
            } else if (year % 100 == 0) {
                System.out.println("Year " + year + " is not a Leap Year");
            } else if (year % 4 == 0) {
                System.out.println("Year " + year + " is a Leap Year");
            } else {
                System.out.println("Year " + year + " is not a Leap Year");
            }
        }
        
        sc.close();
    }
}

