import java.util.Scanner;
public class Q7(IntOperations){
 public static void main(String[] args){
 Scanner sc = new Scanner(System.in);
 int a = sc.nextInt();
 int b = sc.nextInt();
 int c = sc.nextInt();
   int first = a+b*c;
   int second = a*b+b;
   int third = c+a/b;
   int fourth = a%b+c;
   System.out.println("The results of Int Operations are "+first+","+second+","+third+" and "+fourth);
}
}
