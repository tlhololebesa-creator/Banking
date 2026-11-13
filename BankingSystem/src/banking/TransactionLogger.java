package banking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {

    private static final String FILE_NAME = "transactions.txt";

    public static void logTransaction(
            String accountNumber,
            String transactionType,
            double amount,
            double balance,
            String result) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String dateTime = LocalDateTime.now().format(formatter);

        try (PrintWriter writer = new PrintWriter(
                new FileWriter(FILE_NAME, true))) {

            writer.println(
                    "Date/Time: " + dateTime
                    + " | Account: " + accountNumber
                    + " | Transaction: " + transactionType
                    + " | Amount: M" + String.format("%.2f", amount)
                    + " | Balance: M" + String.format("%.2f", balance)
                    + " | Result: " + result
            );

            writer.flush();

            if (writer.checkError()) {
                System.out.println(
                        "ERROR: Could not write the transaction record."
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "ERROR: Could not save transaction to "
                            + FILE_NAME
            );

            System.out.println("Reason: " + e.getMessage());
        }
    }
}