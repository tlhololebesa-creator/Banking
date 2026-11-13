// Import Scanner so the user can enter information using the keyboard.
import java.util.Scanner;

// Import the classes needed for our banking system.
import banking.BankAccount;
import banking.SavingsAccount;
import banking.CurrentAccount;
import banking.Loanable;
import banking.TransactionLogger;

public class BankingSystemMain {

    public static void main(String[] args) {

        // Create a Scanner to read information entered by the user.
        Scanner scanner = new Scanner(System.in);

        // ==========================================
        // CREATE THE FIRST BANK ACCOUNT
        // ==========================================

        System.out.println("===== CREATE FIRST BANK ACCOUNT =====");

        // Ask the user to enter the first account number.
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.next().trim();

        // Move to the next line before reading the account holder's name.
        scanner.nextLine();

        // Ask for the first account holder's name.
        System.out.print("Enter account holder name: ");
        String accountHolder = scanner.nextLine();

        // Ask the user to choose the first account type.
        System.out.println("Choose account type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.print("Enter choice: ");
        int accountType = scanner.nextInt();

        // Ask for the first account's opening balance.
        System.out.print("Enter opening balance: ");
        double openingBalance = scanner.nextDouble();

        // Declare a variable that will store the first account.
        BankAccount userAccount;

        // Create a Savings Account if the user chooses option 1.
        if (accountType == 1) {

            userAccount = new SavingsAccount(
                    accountNumber, accountHolder, openingBalance);

        // Create a Current Account if the user chooses option 2.
        } else if (accountType == 2) {

            userAccount = new CurrentAccount(
                    accountNumber, accountHolder, openingBalance);

        // Stop the program if the account type is invalid.
        } else {

            System.out.println("Invalid account type.");
            scanner.close();
            return;
        }

        // Confirm that the first account has been created.
        System.out.println("\nFirst account created successfully!");

        // Display the first account's details.
        userAccount.displayAccountDetails();

        // ==========================================
        // CREATE THE SECOND BANK ACCOUNT
        // ==========================================

        System.out.println("\n===== CREATE SECOND BANK ACCOUNT =====");

        // Ask for the second account number.
        System.out.print("Enter second account number: ");
        String secondAccountNumber = scanner.next().trim();

        // Prevent both accounts from having the same account number.
        if (secondAccountNumber.equalsIgnoreCase(accountNumber)) {

            System.out.println(
                    "Error: The two accounts cannot have the same number.");

            scanner.close();
            return;
        }

        // Move to the next line before reading the second name.
        scanner.nextLine();

        // Ask for the second account holder's name.
        System.out.print("Enter second account holder name: ");
        String secondAccountHolder = scanner.nextLine();

        // Ask the user to choose the second account type.
        System.out.println("Choose second account type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.print("Enter choice: ");
        int secondAccountType = scanner.nextInt();

        // Ask for the second account's opening balance.
        System.out.print("Enter opening balance: ");
        double secondOpeningBalance = scanner.nextDouble();

        // Declare a variable that will store the second account.
        BankAccount secondAccount;

        // Create the second Savings Account if option 1 is selected.
        if (secondAccountType == 1) {

            secondAccount = new SavingsAccount(
                    secondAccountNumber,
                    secondAccountHolder,
                    secondOpeningBalance);

        // Create the second Current Account if option 2 is selected.
        } else if (secondAccountType == 2) {

            secondAccount = new CurrentAccount(
                    secondAccountNumber,
                    secondAccountHolder,
                    secondOpeningBalance);

        // Stop the program if the second account type is invalid.
        } else {

            System.out.println("Invalid account type.");
            scanner.close();
            return;
        }

        // Confirm that the second account has been created.
        System.out.println("\nSecond account created successfully!");

        // Display the second account's details.
        secondAccount.displayAccountDetails();

        // ==========================================
        // MAIN MENU
        // ==========================================

        // Store the user's menu choice.
        // The loop continues until the user chooses option 8.
        int choice = 0;

        while (choice != 8) {

            // Display the banking system menu.
            System.out.println("\n================================");
            System.out.println("          BANKING SYSTEM");
            System.out.println("================================");
            System.out.println("1. Display all accounts");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Transfer money");
            System.out.println("5. Calculate interest");
            System.out.println("6. Request bank loan");
            System.out.println("7. Display account details");
            System.out.println("8. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            try {

                // Read the menu option entered by the user.
                choice = scanner.nextInt();

                // Choose an operation based on the user's selection.
                switch (choice) {

                    // ==========================================
                    // OPTION 1: DISPLAY BOTH ACCOUNTS
                    // ==========================================

                    case 1:

                        // Call a method that displays both accounts.
                        displayAllAccounts(userAccount, secondAccount);
                        break;

                    // ==========================================
                    // OPTION 2: DEPOSIT MONEY
                    // ==========================================

                    case 2:

                        // Ask which account should receive the deposit.
                        System.out.print("Enter account number: ");
                        String depositNumber = scanner.next().trim();

                        // Find the account using its account number.
                        BankAccount depositTarget = findAccount(
                                depositNumber, userAccount, secondAccount);

                        // Stop this operation if the account does not exist.
                        if (depositTarget == null) {
                            System.out.println("Account not found.");
                            break;
                        }

                        // Ask how much money should be deposited.
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();

                        try {

                            // Add the money to the selected account.
                            depositTarget.deposit(depositAmount);

                            // Save the successful deposit in the transaction file.
                            TransactionLogger.logTransaction(
                                    depositTarget.getAccountNumber(),
                                    "DEPOSIT",
                                    depositAmount,
                                    depositTarget.getBalance(),
                                    "Successful");

                            // Display the updated balance.
                            System.out.println("Deposit successful!");
                            System.out.printf(
                                    "New balance: M%.2f%n",
                                    depositTarget.getBalance());

                        } catch (IllegalArgumentException e) {

                            // Display the reason if the deposit is rejected.
                            System.out.println(e.getMessage());

                            // Record the rejected deposit.
                            TransactionLogger.logTransaction(
                                    depositTarget.getAccountNumber(),
                                    "DEPOSIT",
                                    depositAmount,
                                    depositTarget.getBalance(),
                                    "Rejected - " + e.getMessage());
                        }

                        break;

                    // ==========================================
                    // OPTION 3: WITHDRAW MONEY
                    // ==========================================

                    case 3:

                        // Ask which account should be used.
                        System.out.print("Enter account number: ");
                        String withdrawNumber = scanner.next().trim();

                        // Find the account from the two available accounts.
                        BankAccount withdrawTarget = findAccount(
                                withdrawNumber, userAccount, secondAccount);

                        // Stop if the account number is not found.
                        if (withdrawTarget == null) {
                            System.out.println("Account not found.");
                            break;
                        }

                        // Ask how much money should be withdrawn.
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();

                        try {

                            // Remove money according to the account's rules.
                            withdrawTarget.withdraw(withdrawAmount);

                            // Record the successful withdrawal.
                            TransactionLogger.logTransaction(
                                    withdrawTarget.getAccountNumber(),
                                    "WITHDRAWAL",
                                    withdrawAmount,
                                    withdrawTarget.getBalance(),
                                    "Successful");

                            // Display the new balance.
                            System.out.println("Withdrawal successful!");
                            System.out.printf(
                                    "New balance: M%.2f%n",
                                    withdrawTarget.getBalance());

                        } catch (IllegalArgumentException e) {

                            // Display the reason if the withdrawal fails.
                            System.out.println(e.getMessage());

                            // Record the rejected withdrawal.
                            TransactionLogger.logTransaction(
                                    withdrawTarget.getAccountNumber(),
                                    "WITHDRAWAL",
                                    withdrawAmount,
                                    withdrawTarget.getBalance(),
                                    "Rejected - " + e.getMessage());
                        }

                        break;

                    // ==========================================
                    // OPTION 4: TRANSFER MONEY
                    // ==========================================

                    case 4:

                        System.out.println("\n===== TRANSFER MONEY =====");

                        // Ask for the account sending the money.
                        System.out.print("Enter source account number: ");
                        String sourceNumber = scanner.next().trim();

                        // Find the source account.
                        BankAccount sourceAccount = findAccount(
                                sourceNumber, userAccount, secondAccount);

                        // Stop if the source account does not exist.
                        if (sourceAccount == null) {
                            System.out.println("Source account not found.");
                            break;
                        }

                        // Ask for the account receiving the money.
                        System.out.print("Enter destination account number: ");
                        String destinationNumber = scanner.next().trim();

                        // Find the destination account.
                        BankAccount destinationAccount = findAccount(
                                destinationNumber, userAccount, secondAccount);

                        // Stop if the destination account does not exist.
                        if (destinationAccount == null) {
                            System.out.println("Destination account not found.");
                            break;
                        }

                        // Prevent transferring money to the same account.
                        if (sourceAccount == destinationAccount) {
                            System.out.println(
                                    "You cannot transfer money to the same account.");
                            break;
                        }

                        // Ask for the amount to transfer.
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();

                        try {

                            // Reject zero or negative transfer amounts.
                            if (transferAmount <= 0) {
                                throw new IllegalArgumentException(
                                        "Transfer amount must be greater than zero.");
                            }

                            // First, withdraw money from the source account.
                            sourceAccount.withdraw(transferAmount);

                            try {

                                // Then, deposit the money into the destination account.
                                destinationAccount.deposit(transferAmount);

                            } catch (IllegalArgumentException e) {

                                // Return the money if the destination deposit fails.
                                sourceAccount.deposit(transferAmount);

                                // Pass the error to the outer catch block.
                                throw e;
                            }

                            // Record the successful transfer from the source account.
                            TransactionLogger.logTransaction(
                                    sourceAccount.getAccountNumber(),
                                    "TRANSFER SENT",
                                    transferAmount,
                                    sourceAccount.getBalance(),
                                    "Successful - sent to account "
                                            + destinationAccount.getAccountNumber());

                            // Record the successful transfer into the destination account.
                            TransactionLogger.logTransaction(
                                    destinationAccount.getAccountNumber(),
                                    "TRANSFER RECEIVED",
                                    transferAmount,
                                    destinationAccount.getBalance(),
                                    "Successful - received from account "
                                            + sourceAccount.getAccountNumber());

                            // Display the results of the transfer.
                            System.out.println("\nTransfer successful!");

                            System.out.printf(
                                    "Source account balance: M%.2f%n",
                                    sourceAccount.getBalance());

                            System.out.printf(
                                    "Destination account balance: M%.2f%n",
                                    destinationAccount.getBalance());

                        } catch (IllegalArgumentException e) {

                            // Explain why the transfer failed.
                            System.out.println(
                                    "Transfer failed: " + e.getMessage());

                            // Record the rejected transfer attempt.
                            TransactionLogger.logTransaction(
                                    sourceAccount.getAccountNumber(),
                                    "TRANSFER SENT",
                                    transferAmount,
                                    sourceAccount.getBalance(),
                                    "Rejected - " + e.getMessage());
                        }

                        break;

                    // ==========================================
                    // OPTION 5: CALCULATE INTEREST
                    // ==========================================

                    case 5:

                        // Ask which account should have its interest calculated.
                        System.out.print("Enter account number: ");
                        String interestNumber = scanner.next().trim();

                        // Find the selected account.
                        BankAccount interestTarget = findAccount(
                                interestNumber, userAccount, secondAccount);

                        // Stop if the account does not exist.
                        if (interestTarget == null) {
                            System.out.println("Account not found.");
                            break;
                        }

                        // Calculate interest using the selected account's method.
                        double interest = interestTarget.calculateInterest();

                        // Record the interest calculation and updated balance.
                        TransactionLogger.logTransaction(
                                interestTarget.getAccountNumber(),
                                "INTEREST",
                                interest,
                                interestTarget.getBalance(),
                                "Interest calculated");

                        // Display the interest and new balance.
                        System.out.printf("Interest: M%.2f%n", interest);
                        System.out.printf(
                                "Current balance: M%.2f%n",
                                interestTarget.getBalance());

                        break;

                    // ==========================================
                    // OPTION 6: REQUEST A BANK LOAN
                    // ==========================================

                    case 6:

                        // Ask which account is requesting the loan.
                        System.out.print("Enter account number: ");
                        String loanNumber = scanner.next().trim();

                        // Find the account.
                        BankAccount loanTarget = findAccount(
                                loanNumber, userAccount, secondAccount);

                        // Stop if the account does not exist.
                        if (loanTarget == null) {
                            System.out.println("Account not found.");
                            break;
                        }

                        // Check whether the account supports loans.
                        if (!(loanTarget instanceof Loanable)) {
                            System.out.println(
                                    "This account does not support loans.");
                            break;
                        }

                        // Ask how much money the user wants to borrow.
                        System.out.print("Enter loan amount: ");
                        double loanAmount = scanner.nextDouble();

                        try {

                            // Convert the account to the Loanable interface type.
                            Loanable loanableAccount = (Loanable) loanTarget;

                            // Request the loan using the account's loan rules.
                            loanableAccount.requestLoan(loanAmount);

                            // Record the approved loan.
                            TransactionLogger.logTransaction(
                                    loanTarget.getAccountNumber(),
                                    "LOAN",
                                    loanAmount,
                                    loanTarget.getBalance(),
                                    "Loan approved");

                            // Display the updated balance.
                            System.out.printf(
                                    "New balance: M%.2f%n",
                                    loanTarget.getBalance());

                        } catch (IllegalArgumentException e) {

                            // Display the reason if the loan is rejected.
                            System.out.println(e.getMessage());

                            // Record the rejected loan.
                            TransactionLogger.logTransaction(
                                    loanTarget.getAccountNumber(),
                                    "LOAN",
                                    loanAmount,
                                    loanTarget.getBalance(),
                                    "Rejected - " + e.getMessage());
                        }

                        break;

                    // ==========================================
                    // OPTION 7: DISPLAY ACCOUNT DETAILS
                    // ==========================================

                    case 7:

                        // Ask which account the user wants to view.
                        System.out.print("Enter account number: ");
                        String detailsNumber = scanner.next().trim();

                        // Find the selected account.
                        BankAccount detailsTarget = findAccount(
                                detailsNumber, userAccount, secondAccount);

                        // Stop if the account does not exist.
                        if (detailsTarget == null) {
                            System.out.println("Account not found.");
                            break;
                        }

                        // Display the selected account's details.
                        System.out.println("\n===== ACCOUNT DETAILS =====");
                        detailsTarget.displayAccountDetails();

                        // Record that the account details were viewed.
                        TransactionLogger.logTransaction(
                                detailsTarget.getAccountNumber(),
                                "ACCOUNT DETAILS",
                                0.00,
                                detailsTarget.getBalance(),
                                "Account details displayed");

                        break;

                    // ==========================================
                    // OPTION 8: EXIT THE PROGRAM
                    // ==========================================

                    case 8:

                        // Display a message before ending the program.
                        System.out.println(
                                "\nThank you for using the Banking System.");
                        break;

                    // ==========================================
                    // HANDLE AN INVALID MENU OPTION
                    // ==========================================

                    default:

                        // Tell the user to select a valid menu option.
                        System.out.println(
                                "Invalid choice. Please choose from 1 to 8.");
                }

            } catch (java.util.InputMismatchException e) {

                // Handle incorrect input types, such as letters instead of numbers.
                System.out.println(
                        "Invalid input. Please enter the correct type of value.");

                // Remove the invalid input so the program can continue.
                scanner.nextLine();

            } catch (Exception e) {

                // Display other errors that occur during an operation.
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        // Close the Scanner when the program finishes.
        scanner.close();
    }

    // ==========================================
    // METHOD TO DISPLAY BOTH ACCOUNTS
    // ==========================================

    // This method receives both accounts and displays their details.
    private static void displayAllAccounts(
            BankAccount userAccount,
            BankAccount secondAccount) {

        // Display a heading for the account list.
        System.out.println("\n========== ALL ACCOUNTS ==========");

        // Display the first account.
        System.out.println("\nFIRST ACCOUNT:");
        userAccount.displayAccountDetails();

        // Display the second account.
        System.out.println("\nSECOND ACCOUNT:");
        secondAccount.displayAccountDetails();

        // Display the closing line.
        System.out.println("==================================");
    }

    // ==========================================
    // METHOD TO FIND AN ACCOUNT
    // ==========================================

    // This method checks whether the account number belongs to either account.
    private static BankAccount findAccount(
            String accountNumber,
            BankAccount userAccount,
            BankAccount secondAccount) {

        // Return null if no account number was provided.
        if (accountNumber == null) {
            return null;
        }

        // Remove extra spaces from the entered account number.
        accountNumber = accountNumber.trim();

        // Check whether the number matches the first account.
        if (userAccount != null
                && accountNumber.equalsIgnoreCase(
                        userAccount.getAccountNumber().trim())) {

            return userAccount;
        }

        // Check whether the number matches the second account.
        if (secondAccount != null
                && accountNumber.equalsIgnoreCase(
                        secondAccount.getAccountNumber().trim())) {

            return secondAccount;
        }

        // Return null if neither account matches.
        return null;
    }
}