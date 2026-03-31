import java.util.*;
public class BMI{
 public static void main(String[] args){
  Scanner sc = new Scanner(System.in);
  System.out.println("Enter the weigth (in kgs.): ");
  int w = sc.nextInt();
  System.out.println("Enter the height (in cms.): ");
  int h = sc.nextInt();
  double BMI = w/((h/100)*w);
  if(BMI<18.5){
   System.out.println("The person is Underweight!");
  }else if(BMI>=25){
   System.out.println("The person is Overweight!");
  }else{
   System.out.println("The person is Normal.");
   }
   sc.close();
 }
}
