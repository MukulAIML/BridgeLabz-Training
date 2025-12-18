import java.util.Scanner;
public class Division{
 public static void main(String[] args){
 Scanner sc = new Scanner(System.in);
 int a = sc.nextInt();
 int b = sc.nextInt();
   int quotient = a/b;
   int reminder = a%b;
   System.out.println("The Quotient is "+quotient+" and Reminder is "+reminder+" of two number "+a+" and "+b);
}
}