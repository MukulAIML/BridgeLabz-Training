import java.util.Scanner;
public class Q8(doubleOperations){
 public static void main(String[] args){
 Scanner sc = new Scanner(System.in);
 double a = sc.nextInt();
 double b = sc.nextInt();
 double c = sc.nextInt();
   double first = a+b*c;
   double second = a*b+c;
   double third = c+a/b;
   double fourth = a%b+c;
   System.out.println("The results of Double Operations are "+first+","+second+","+third+" and "+fourth);
}
}
