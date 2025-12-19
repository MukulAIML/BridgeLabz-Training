import java.util.Scanner;
public class Q8(KMtoMiles){
 public static void main(String[] args){
   Scanner sc = new Scanner(System.in);
   int dist = sc.nextInt();
   double miles = dist * 0.621371;
   System.out.print("The total miles is "+miles+" mile for the given "+dist+" km");
}
}
