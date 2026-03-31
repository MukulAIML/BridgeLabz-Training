import java.util.Scanner;
public class SI{
 public static void main(String[] args){
   Scanner sc = new Scanner(System.in);
   System.out.println("Enter the Principle: ");
   int P = sc.nextInt();
   System.out.println("Enter the Time Duration: ");
   int T = sc.nextInt();
   System.out.println("Enter the Rate: ");
   int R = sc.nextInt();
   double SI = (P*T*R)/100;
   System.out.print("RS."+SI);
}
}
