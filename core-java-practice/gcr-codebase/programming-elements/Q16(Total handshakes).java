import java.util.Scanner;
public class Q24{
 public static void main(String[] args){
 Scanner sc = new Scanner(System.in);
 System.out.println("Enter no. of Students: ");
 int n = sc.nextInt();
   int shakes = (n*(n-1)/2);
   System.out.println("the number of possible handshakes are "+shakes);
}
}