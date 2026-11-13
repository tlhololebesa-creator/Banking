package banking;

public class CurrentAccount extends BankAccount implements Loanable {

    private static final double OVERDRAFT_LIMIT = 2000.00;
    private static final double LOAN_LIMIT = 10000.00;

    public CurrentAccount(String accountNumber, String accountHolder, double balance) {
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

        if (getBalance() - amount < -OVERDRAFT_LIMIT) {
            throw new IllegalArgumentException(
                "Withdrawal rejected. Overdraft limit is M2000.00."
            );
        }

        setBalance(getBalance() - amount);

        System.out.println("Withdrawal successful.");
    }

    @Override
    public double calculateInterest() {

        return 0.00;
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
                "Loan rejected. Maximum loan is M10000.00."
            );
        }

        setBalance(getBalance() + amount);

        System.out.println("Loan approved.");
    }
}


