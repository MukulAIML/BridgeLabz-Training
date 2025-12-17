import java.util.Scanner;
public class Distance{
 public static void main(String[] args){
   Scanner sc = new Scanner(System.in);
   int dist = sc.nextInt();
   double miles = dist * 0.621371;
   System.out.print(miles);
}
}