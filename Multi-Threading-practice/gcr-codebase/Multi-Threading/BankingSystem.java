import java.util.Scanner;
import java.time.LocalTime;

class BankAccount {
    private int balance;
    
    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }
    
    public void withdraw(String customerName, int amount) {
        System.out.println("[" + customerName + "] Attempting to withdraw " + amount + " at " + LocalTime.now());
        
        if (balance >= amount) {
            try {
                Thread.sleep(100); // Simulate processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println("✓ Transaction successful: " + customerName + ", Amount: " + amount + ", Balance: " + balance);
        } else {
            System.out.println("✗ Transaction failed: " + customerName + ", Insufficient funds! Current balance: " + balance);
        }
    }
    
    public int getBalance() {
        return balance;
    }
}

class Transaction implements Runnable {
    private BankAccount account;
    private String customerName;
    private int amount;
    
    public Transaction(BankAccount account, String customerName, int amount) {
        this.account = account;
        this.customerName = customerName;
        this.amount = amount;
    }
    
    @Override
    public void run() {
        account.withdraw(customerName, amount);
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Banking System ===");
        System.out.print("Enter initial account balance: ");
        int initialBalance = scanner.nextInt();
        
        BankAccount account = new BankAccount(initialBalance);
        
        System.out.print("Enter number of transactions: ");
        int numTransactions = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Thread[] threads = new Thread[numTransactions];
        
        for (int i = 0; i < numTransactions; i++) {
            System.out.print("Enter customer name " + (i + 1) + ": ");
            String customerName = scanner.nextLine();
            System.out.print("Enter withdrawal amount for " + customerName + ": ");
            int amount = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            threads[i] = new Thread(new Transaction(account, customerName, amount));
            System.out.println("Thread state before processing: " + threads[i].getState());
        }
        
        // Start all transaction threads
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Wait for all transactions to complete
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        
        System.out.println("\n=== Final Balance: " + account.getBalance() + " ===");
        scanner.close();
    }
}
