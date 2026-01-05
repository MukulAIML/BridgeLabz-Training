import java.util.*;

public class BankingSystem {
    private Map<String, Double> accounts = new HashMap<>();
    private Queue<String> withdrawalQueue = new LinkedList<>();

    public void createAccount(String accountNumber, double initialBalance) {
        accounts.put(accountNumber, initialBalance);
        System.out.println("Account " + accountNumber + " created with balance: $" + initialBalance);
    }

    public void deposit(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, accounts.get(accountNumber) + amount);
            System.out.println("Deposited $" + amount + " to " + accountNumber);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void requestWithdrawal(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            withdrawalQueue.add(accountNumber);
            System.out.println("Withdrawal request queued for " + accountNumber);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void processWithdrawals() {
        System.out.println("\n=== Processing Withdrawals ===");
        if (withdrawalQueue.isEmpty()) {
            System.out.println("No withdrawals to process.");
            return;
        }
        while (!withdrawalQueue.isEmpty()) {
            String account = withdrawalQueue.poll();
            System.out.println("Processing withdrawal for " + account + 
                             " (Balance: $" + accounts.get(account) + ")");
        }
    }

    public void displayAccountsSortedByBalance() {
        System.out.println("\n=== Accounts Sorted by Balance ===");
        TreeMap<Double, List<String>> sortedAccounts = new TreeMap<>(Collections.reverseOrder());
        
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            double balance = entry.getValue();
            if (!sortedAccounts.containsKey(balance)) {
                sortedAccounts.put(balance, new ArrayList<>());
            }
            sortedAccounts.get(balance).add(entry.getKey());
        }
        
        for (Map.Entry<Double, List<String>> entry : sortedAccounts.entrySet()) {
            for (String account : entry.getValue()) {
                System.out.println(account + ": $" + entry.getKey());
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankingSystem bank = new BankingSystem();
        
        System.out.print("Enter number of accounts to create: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Account number: ");
            String accNum = sc.nextLine();
            System.out.print("Initial balance: $");
            double balance = sc.nextDouble();
            sc.nextLine();
            bank.createAccount(accNum, balance);
        }
        
        System.out.println("\n=== Menu ===");
        System.out.println("1. Deposit");
        System.out.println("2. Request Withdrawal");
        System.out.println("3. Process All Withdrawals");
        System.out.println("4. Display Accounts Sorted by Balance");
        System.out.print("Choose option: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Account number: ");
                String accDep = sc.nextLine();
                System.out.print("Amount: $");
                double amt = sc.nextDouble();
                bank.deposit(accDep, amt);
                break;
            case 2:
                System.out.print("Account number: ");
                String accWith = sc.nextLine();
                bank.requestWithdrawal(accWith);
                break;
            case 3:
                bank.processWithdrawals();
                break;
            case 4:
                bank.displayAccountsSortedByBalance();
                break;
        }
        
        sc.close();
    }
}