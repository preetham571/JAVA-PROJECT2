import java.util.Scanner;

public class ATMModule3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] transactionHistory = new String[5];
        int txCount = 0;

        System.out.println("==========================================");
        System.out.println("    Module 3: Methods & Transaction Log   ");
        System.out.println("==========================================");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Record Transaction");
            System.out.println("2. View History");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (txCount < transactionHistory.length) {
                        System.out.print("Enter transaction description: ");
                        String desc = scanner.nextLine();
                        transactionHistory[txCount] = desc;
                        txCount++;
                        System.out.println("Recorded.");
                    } else {
                        System.out.println("Transaction history full.");
                    }
                    break;
                case 2:
                    printHistory(transactionHistory, txCount);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    public static void printHistory(String[] history, int count) {
        System.out.println("\n--- Recent Transactions ---");
        if (count == 0) {
            System.out.println("No transactions recorded.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + history[i]);
            }
        }
    }
}