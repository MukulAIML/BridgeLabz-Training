import java.util.Scanner;
public class Q15{
 public static void main(String[] args){
 Scanner sc = new Scanner(System.in);
 System.out.println("Enter Perimeter: ");
 int peri = sc.nextInt();
   double side = peri / 4;
   System.out.println("The length of the side is "+side+" whose perimeter is "+peri);
}
}