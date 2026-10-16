import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Withdrawal failed. Insufficient funds available.");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

public class ATMModule4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount("ACC-9988", 3000.00);

        System.out.println("==========================================");
        System.out.println("     Module 4: OOP & Exception Handling   ");
        System.out.println("==========================================");

        try {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
            System.out.println("Withdrawal successful! Remaining balance: $" + account.getBalance());
        } catch (InsufficientBalanceException e) {
            System.out.println("Custom Exception Caught: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input format.");
        } finally {
            System.out.println("[Transaction attempt finalized.]");
        }

        scanner.close();
    }
}