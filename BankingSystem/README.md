 Banking System

 1. Project Description

The Banking System is a Java console-based application that allows users to create and manage bank accounts.

The system supports two types of accounts: Savings Account and Current Account. Users can deposit money, withdraw money, transfer money between accounts, calculate interest, and request loans.

The project uses Object-Oriented Programming (OOP), exception handling, and file handling to manage banking operations.

 2. System Features

The system has the following features:

- Create Accounts: Users can create Savings Accounts or Current Accounts.
- Deposit Money: Allows users to add money to their accounts.
- Withdraw Money: Allows users to take money out of their accounts.
- Transfer Money: Allows users to send money from one account to another.
- Calculate Interest: Calculates interest on Savings Accounts.
- Request Loans: Allows users to request loans within the allowed limits.
- Display Accounts: Shows account information and current balances.
- Transaction Logging: Saves transaction details in a text file.
- Input Validation:Checks user input and prevents invalid transactions.

 3. Types of Bank Accounts

Savings Account
- Earns 5% interest.
- Must maintain a minimum balance of M100.00.
- Allows deposits and withdrawals.
- Allows loans of up to M5,000.00.

 Current Account
- Allows deposits and withdrawals.
- Allows an overdraft of up to M2,000.00.
- Does not earn interest.
- Allows loans of up to M10,000.00.

 4. Java Classes and Interface

The project contains the following classes and interface:

| Class / Interface | Purpose |
|---|---|
| BankAccount | An abstract class that stores common account information and methods. |
| SavingsAccount | Handles savings account operations and interest calculations. |
| CurrentAccount | Handles current account operations and overdraft limits. |
| Loanable | An interface that defines the method for requesting a loan. |
| TransactionLogger | Saves transaction information in a text file. |
| BankingSystemMain | Contains the main method and the banking system menu. |

5. Object-Oriented Programming (OOP) Concepts

The project uses the following OOP concepts:

- Encapsulation: Private variables are used to protect account information. Getter methods are used to access the information.
Inheritance: SavingsAccount and CurrentAccount inherit properties and methods from the BankAccount class.
Abstraction: BankAccount is an abstract class that contains common methods for different account types.
- Polymorphism: A BankAccount reference can be used to store different account types and call their methods.
 Interfaces:the Loanable interface allows both account types to use the loan request method.

 6. Exception Handling and File Handling

The system uses exception handling to deal with errors during banking operations.

For example, it prevents users from depositing negative amounts, withdrawing invalid amounts, or requesting loans above the allowed limits.

File handling is used to save transaction records in a file called `transactions.txt`.

The file stores:
- Date and time of the transaction.
- Account number.
- Transaction type.
- Transaction amount.
- Updated account balance.
- Transaction result (successful or rejected).

 7. How to Run the Program

1. Open the project in a Java IDE, such as Visual Studio Code.
2. Make sure all Java files are in their correct folders.
3. Run the BankingSystemMain.java file.
4. Enter the required account information.
5. Choose an option from the menu to perform a banking operation.
6. Select option 8 to exit the program.

8. Technologies Used

- Programming Language: Java
- IDE: Visual Studio Code
- File Handling:Java File I/O
- Exception Handling:Java Exceptions
- Programming Approach: Object-Oriented Programming (OOP)

9. Transaction Records

The system saves transaction information in a file called transactions.txt.

The file is created automatically when the first transaction is recorded. New transactions are added to the same file without deleting previous records.

10. Conclusion

This project demonstrates how Java and Object-Oriented Programming concepts can be used to create a simple Banking System. It allows users to perform basic banking operations while applying concepts such as inheritance, encapsulation, abstraction, polymorphism, interfaces, exception handling, and file handling.