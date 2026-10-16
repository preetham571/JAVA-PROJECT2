import java.util.Scanner;

public class ATMModule1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int CORRECT_PIN = 1234;
        final double INITIAL_BALANCE = 5000.00;

        System.out.println("==========================================");
        System.out.println("         ATM TRANSACTION SIMULATOR        ");
        System.out.println("         Module 1: Authentication         ");
        System.out.println("==========================================");

        System.out.print("Enter 4-digit PIN: ");
        int enteredPin = scanner.nextInt();

        if (enteredPin == CORRECT_PIN) {
            System.out.println("Authentication Successful.");
            System.out.println("Initial Account Balance: $" + INITIAL_BALANCE);
        } else {
            System.out.println("Authentication Failed. Incorrect PIN.");
        }

        scanner.close();
    }
}