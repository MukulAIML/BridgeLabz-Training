import java.util.Scanner;
public class Q2(AreaTriangle){
 public static void main(String[] args){
 Scanner sc = new Scanner(System.in);
 System.out.println("Enter base(in cm): ");
 int base = sc.nextInt();
 System.out.println("Enter height(in cm): ");
 int height = sc.nextInt();
   double area = (0.5) * base * height;
   double area_inch= area / (2.54 * 2.54);
   System.out.println("The Area of the triangle in sq in is "+area_inch+" and sq cm is "+area);
}
}
