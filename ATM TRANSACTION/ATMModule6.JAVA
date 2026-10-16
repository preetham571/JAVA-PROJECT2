import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class TransactionRecord {
    private String type;
    private double amount;

    public TransactionRecord(String type, double amount) {
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

public class ATMModule6 {
    public static void main(String[] args) {
        List<TransactionRecord> history = new ArrayList<>();
        history.add(new TransactionRecord("Deposit", 2000.00));
        history.add(new TransactionRecord("Withdrawal", 450.00));
        history.add(new TransactionRecord("Deposit", 100.00));
        history.add(new TransactionRecord("Withdrawal", 1200.00));

        System.out.println("==========================================");
        System.out.println("     Module 6: Collections & Streams      ");
        System.out.println("==========================================");

        System.out.println("\n--- All Recorded Transactions ---");
        history.forEach(System.out::println);

        System.out.println("\n--- Filtered: Large Transactions (> $500) ---");
        List<TransactionRecord> largeTransactions = history.stream()
                .filter(t -> t.getAmount() > 500.00)
                .collect(Collectors.toList());

        largeTransactions.forEach(System.out::println);
    }
}