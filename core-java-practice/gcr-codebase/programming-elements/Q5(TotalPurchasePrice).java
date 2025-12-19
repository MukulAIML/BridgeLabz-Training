import java.util.Scanner;
public class Q5(TotalPurchasePrice){
 public static void main(String[] args){
 Scanner sc = new Scanner(System.in);
 System.out.println("Enter unit price: ");
 int unit = sc.nextInt();
 System.out.println("Enter quantiit: ");
 int quant = sc.nextInt();
   int net = quant*unit;
   System.out.println(" The total purchase price is INR "+net+" if the quantity "+quant+" and the unit price is INR "+unit);
}
}
