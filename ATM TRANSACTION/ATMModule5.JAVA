import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMModule5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Path logPath = Paths.get("atm_audit_log.txt");
        List<String> logs = new ArrayList<>();

        System.out.println("==========================================");
        System.out.println("     Module 5: Strings & File Persistence ");
        System.out.println("==========================================");

        logs.add("Session Started: User Authenticated");
        logs.add("Withdrawal: $500.00");

        try {
            Files.write(logPath, logs);
            System.out.println("Audit logs successfully written to file: " + logPath.toAbsolutePath());

            if (Files.exists(logPath)) {
                List<String> readLogs = Files.readAllLines(logPath);
                System.out.println("\n--- Reading Audit Log from File ---");
                for (String line : readLogs) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("File I/O Error: " + e.getMessage());
        }

        scanner.close();
    }
}