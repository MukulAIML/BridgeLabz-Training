// Digital Payment Interface
import java.util.Scanner;

interface Payment {
    void pay(double amount);
}

class UPI implements Payment {
    private String upiId;
    
    public UPI(String upiId) {
        this.upiId = upiId;
    }
    
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using UPI ID: " + upiId);
    }
}

class CreditCard implements Payment {
    private String cardNumber;
    
    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Credit Card: " + 
                         cardNumber.substring(cardNumber.length() - 4));
    }
}

class Wallet implements Payment {
    private String walletName;
    private double balance;
    
    public Wallet(String walletName, double balance) {
        this.walletName = walletName;
        this.balance = balance;
    }
    
    public void pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Paid ₹" + amount + " using " + walletName + 
                             " Wallet. Remaining balance: ₹" + balance);
        } else {
            System.out.println("Insufficient balance in " + walletName);
        }
    }
}

public class DigitalPaymentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter payment amount: ₹");
        double amount = scanner.nextDouble();
        
        Payment upi = new UPI("user@paytm");
        Payment creditCard = new CreditCard("1234567812345678");
        Payment wallet = new Wallet("Paytm", 5000);
        
        System.out.println("\nProcessing payments:");
        upi.pay(amount);
        creditCard.pay(amount);
        wallet.pay(amount);
        
        scanner.close();
    }
}
