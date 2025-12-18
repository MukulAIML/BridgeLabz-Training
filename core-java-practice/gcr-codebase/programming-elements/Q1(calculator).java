import java.util.Scanner;
public class Q17{
 public static void main(String[] args){
 Scanner sc = new Scanner(System.in);
 System.out.println("Enter number1: ");
 int a = sc.nextInt();
 System.out.println("Enter number2: ");
 int b = sc.nextInt();
   int add = a+b;
   int sub = a-b;
   int mul = a*b;
   int div = a/b;
   System.out.println("The addition, subtraction, multiplication, and division value of 2 numbers "+a+" and "+b+" is "+add+","+sub+","+mul+", and "+div);
}
}