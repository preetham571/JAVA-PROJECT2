 import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class TransactionItem {
    private String type;
    private double amount;

    public TransactionItem(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return type + ": $" + amount;
    }
}

class BankAccount {
    private int pin;
    private double balance;

    public BankAccount(int pin, double balance) {
        this.pin = pin;
        this.balance = balance;
    }

    public boolean authenticate(int enteredPin) {
        return this.pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient funds available.");
        }
        balance -= amount;
    }
}

public class ATMSimulatorSystem {

    private static final Path LOG_PATH = Paths.get("atm_session_log.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(1234, 5000.00);
        List<TransactionItem> history = new ArrayList<>();

        System.out.println("==========================================");
        System.out.println("         ATM TRANSACTION SIMULATOR        ");
        System.out.println("==========================================");

        System.out.print("Enter 4-digit PIN: ");
        int pin = scanner.nextInt();

        if (!account.authenticate(pin)) {
            System.out.println("Authentication Failed. Exiting.");
            scanner.close();
            return;
        }

        System.out.println("Authentication Successful.");
        boolean running = true;

        while (running) {
            printMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double dep = scanner.nextDouble();
                    if (dep > 0) {
                        account.deposit(dep);
                        history.add(new TransactionItem("Deposit", dep));
                        System.out.println("Successfully deposited $" + dep);
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double wit = scanner.nextDouble();
                    try {
                        if (wit <= 0) {
                            System.out.println("Invalid amount.");
                            break;
                        }
                        account.withdraw(wit);
                        history.add(new TransactionItem("Withdrawal", wit));
                        System.out.println("Successfully withdrew $" + wit);
                    } catch (InsufficientBalanceException e) {
                        System.out.println("Error: " + e.getMessage());
                    } finally {
                        System.out.println("[Transaction processed]");
                    }
                    break;
                case 4:
                    System.out.println("\n--- All Transactions ---");
                    if (history.isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        history.forEach(System.out::println);
                    }
                    break;
                case 5:
                    System.out.print("Enter minimum amount to filter: ");
                    double threshold = scanner.nextDouble();
                    List<TransactionItem> filtered = history.stream()
                            .filter(t -> t.getAmount() >= threshold)
                            .collect(Collectors.toList());
                    System.out.println("\n--- Filtered Transactions ---");
                    if (filtered.isEmpty()) {
                        System.out.println("No transactions found.");
                    } else {
                        filtered.forEach(System.out::println);
                    }
                    break;
                case 6:
                    try {
                        List<String> lines = history.stream()
                                .map(TransactionItem::toString)
                                .collect(Collectors.toList());
                        lines.add(0, "Final Balance: $" + account.getBalance());
                        Files.write(LOG_PATH, lines);
                        System.out.println("Session saved to file.");
                    } catch (IOException e) {
                        System.out.println("File write error: " + e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        if (Files.exists(LOG_PATH)) {
                            List<String> lines = Files.readAllLines(LOG_PATH);
                            System.out.println("\n--- Loaded Log File ---");
                            lines.forEach(System.out::println);
                        } else {
                            System.out.println("No log file found.");
                        }
                    } catch (IOException e) {
                        System.out.println("File read error: " + e.getMessage());
                    }
                    break;
                case 8:
                    System.out.println("Exiting ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- ATM MENU ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View Transaction History");
        System.out.println("5. Filter Transactions (Streams)");
        System.out.println("6. Save Session to File");
        System.out.println("7. Load Session from File");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
    }
}