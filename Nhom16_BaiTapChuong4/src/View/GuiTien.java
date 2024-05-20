package View;

import Model.BankAccount;

public class GuiTien implements Runnable {
    private BankAccount account;
    private double amount;

    public GuiTien(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            synchronized (account) {
                double newBalance = account.getBalance() + amount;
                account.setBalance(newBalance);
                System.out.println("Deposited: " + amount + ", New Balance: " + newBalance);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
