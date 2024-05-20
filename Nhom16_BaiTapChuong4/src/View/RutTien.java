package View;

import Model.BankAccount;

public class RutTien implements Runnable {
    private BankAccount account;
    private double amount;

    public RutTien(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            synchronized (account) {
                double currentBalance = account.getBalance();
                if (currentBalance >= amount) {
                    double newBalance = currentBalance - amount;
                    account.setBalance(newBalance);
                    System.out.println("Withdrew: " + amount + ", New Balance: " + newBalance);
                } else {
                    System.out.println("Insufficient funds to withdraw: " + amount + ", Current Balance: " + currentBalance);
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
