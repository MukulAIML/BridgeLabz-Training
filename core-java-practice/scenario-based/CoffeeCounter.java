import java.util.*;
public class CoffeeCounter{
 public static void main(String[] args){
  Scanner sc = new Scanner(System.in);
  double total = 0;
  double gst = 0.18;
  char more = 'y';
  do{
  double price = 0;
   System.out.println("Coffee Menu: ");
   System.out.println("1. Espresso - ₹120");
   System.out.println("2. Cappuccino - ₹150");
   System.out.println("3. Latte - ₹180");
   System.out.println("4. Black Coffee - ₹100");
  
   System.out.println("Enter your Choice(1-4): ");
   int choice = sc.nextInt();
   switch(choice){
    case 1:
     price = 120;
     break;
    case 2:
     price = 150;
     break;
    case 3:
     price = 180;
     break;
    case 4:
     price = 100;
     break;
    default:
     System.out.println("Invalid Choice!!!");
     continue;
  }
  System.out.println("Enter the quantity: ");
  int quantity = sc.nextInt();
  
  total += quantity*price;
  System.out.println("Do you want more coffee orders(Y/N)?: ");
  more = sc.next().charAt(0);
  }
  while(more == 'Y' || more == 'y');
  double gstamt = total*gst;
  double net = total+gstamt;
  long finalAmt = Math.round(net);
  
  System.out.println("Amount:       "+total);
  System.out.println("GST(18%):     "+gstamt);
  System.out.println("Total Amount: "+finalAmt);
  sc.close();
 }
}
