// Payment Gateway Integration with Default Method
import java.util.Scanner;

interface PaymentProcessor {
    void processPayment(double amount);
    
    default void refund(double amount) {
        System.out.println("Refunding ₹" + amount + " using default refund process");
    }
}

class PayPalProcessor implements PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing ₹" + amount + " via PayPal");
    }
}

class StripeProcessor implements PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing ₹" + amount + " via Stripe");
    }
    
    @Override
    public void refund(double amount) {
        System.out.println("Refunding ₹" + amount + " via Stripe with instant processing");
    }
}

class RazorpayProcessor implements PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing ₹" + amount + " via Razorpay");
    }
}

public class PaymentGatewayIntegration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter payment amount: ₹");
        double amount = scanner.nextDouble();
        
        PaymentProcessor paypal = new PayPalProcessor();
        PaymentProcessor stripe = new StripeProcessor();
        PaymentProcessor razorpay = new RazorpayProcessor();
        
        System.out.println("\nProcessing payments:");
        paypal.processPayment(amount);
        stripe.processPayment(amount);
        razorpay.processPayment(amount);
        
        System.out.println("\nProcessing refunds:");
        paypal.refund(amount);
        stripe.refund(amount);
        razorpay.refund(amount);
        
        scanner.close();
    }
}
