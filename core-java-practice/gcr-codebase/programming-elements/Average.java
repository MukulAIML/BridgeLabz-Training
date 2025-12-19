import java.util.Scanner;
public class Average{
 public static void main(String[] args){
   Scanner sc = new Scanner(System.in);
   System.out.println("Enter the 1st No.: ");
   int num1 = sc.nextInt();
   System.out.println("Enter the 2nd No.: ");
   int num2 = sc.nextInt();
   System.out.println("Enter the 3rd No.");
   int num3 = sc.nextInt();
   double Ans = (num1+num2+num3)/3;
   System.out.print(Ans);
}
}
