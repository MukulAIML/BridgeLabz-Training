import java.util.Scanner;
public class Q6(DiscountFee){
 public static void main(String[] args){
   Scanner sc = new Scanner(System.in);
   System.out.print("Enter fee: ");
   int fee = sc.nextInt();
   System.out.print("Enter discount %: ");
   int disc = sc.nextInt();
   double disct = (fee*disc)/100;
   double dfee = fee-disct;
   System.out.print("The discount amount is INR "+disct+" and final discounted fee is INR "+dfee);
}
}
