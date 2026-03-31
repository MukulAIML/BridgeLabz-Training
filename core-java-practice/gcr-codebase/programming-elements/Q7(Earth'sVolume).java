import java.util.Scanner;
public class Q7(Earth'sVolume){
 public static void main(String[] args){
   Scanner sc = new Scanner(System.in);
  // int dist = sc.nextInt();
   double volm = (4/3) * 3.14 * Math.pow(6378,3);
   double mile = 6378 * 0.621371;
   double milev = (4/3) * 3.14 * Math.pow(mile,3);
   System.out.print("The volume of earth in cubic kilometers is "+volm+" and cubic miles is "+milev);
}
}
