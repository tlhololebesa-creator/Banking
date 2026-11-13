package banking;

public class SavingsAccount extends BankAccount implements Loanable {

    private static final double INTEREST_RATE = 0.05;
    private static final double MINIMUM_BALANCE = 100.00;
    private static final double LOAN_LIMIT = 5000.00;

    public SavingsAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Deposit amount must be greater than zero."
            );
        }

        setBalance(getBalance() + amount);

        System.out.println("Deposit successful.");
    }

    @Override
    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Withdrawal amount must be greater than zero."
            );
        }

        if (getBalance() - amount < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(
                "Withdrawal rejected. Minimum balance is M100.00."
            );
        }

        setBalance(getBalance() - amount);

        System.out.println("Withdrawal successful.");
    }

    @Override
    public double calculateInterest() {

        double interest = getBalance() * INTEREST_RATE;

        setBalance(getBalance() + interest);

        return interest;
    }

    @Override
    public void requestLoan(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Loan amount must be greater than zero."
            );
        }

        if (amount > LOAN_LIMIT) {
            throw new IllegalArgumentException(
                "Loan rejected. Maximum loan is M5000.00."
            );
        }

        setBalance(getBalance() + amount);

        System.out.println("Loan approved.");
    }
}

