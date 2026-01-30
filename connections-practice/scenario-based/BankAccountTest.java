import org.junit.Test;
import static org.junit.Assert.assertEquals;

// Program class representing a bank account
class Program {
    private double balance;

    public Program(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }
}

// UnitTest class with test methods
public class BankAccountTest {

    @Test
    public void testDepositValidAmount() {
        Program account = new Program(100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeAmount() {
        Program account = new Program(100.0);
        account.deposit(-50.0);
    }

    @Test
    public void testWithdrawValidAmount() {
        Program account = new Program(100.0);
        account.withdraw(30.0);
        assertEquals(70.0, account.getBalance(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawInsufficientFunds() {
        Program account = new Program(50.0);
        account.withdraw(100.0);
    }
}
