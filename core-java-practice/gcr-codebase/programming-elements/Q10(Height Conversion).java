import java.util.Scanner;
public class Height{
 public static void main(String[] args){
   Scanner sc = new Scanner(System.in);
   int height = sc.nextInt();
   double inch_h = height * 2.54;
   double foot_h = inch_h/12;
   System.out.print("Your Height in cm is "+height+" while in feet is "+foot_h+ " and inches is "+inch_h);
}
}