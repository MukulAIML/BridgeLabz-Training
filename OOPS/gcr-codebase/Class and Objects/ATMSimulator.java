public class ATMSimulator {
    String accountHolder;
    String accountNumber;
    double balance;

    // Constructor to initialize account details
    public ATMSimulator(String accountHolder, String accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Method to deposit money into account
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to withdraw money from account if sufficient balance exists
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    // Method to display current account balance
    public void displayBalance() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + balance);
    }

    // Main method to test the class
    public static void main(String[] args) {
        ATMSimulator account = new ATMSimulator("Bob", "1234567890", 10000);
        account.deposit(5000);
        account.withdraw(3000);
        account.displayBalance();
    }
}