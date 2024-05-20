package Model;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return balance;
    }
}
