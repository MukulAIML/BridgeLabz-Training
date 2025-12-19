import java.util.Scanner;
public class CircleArea{
 public static void main(String[] args){
   Scanner sc = new Scanner(System.in);
   int rad = sc.nextInt();
   int area = (3.14)*rad*rad;
   System.out.print(area);
}
}
