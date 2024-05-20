package Model;

public class TaiKhoanNganHang {
    private double balance;
    private int accountNumber;

    public TaiKhoanNganHang(int accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public synchronized double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
