package View;

import Model.TaiKhoanNganHang;

import javax.swing.table.DefaultTableModel;

public class ChuyenTien implements Runnable {
    private TaiKhoanNganHang fromAccount;
    private TaiKhoanNganHang toAccount;
    private double amount;
    private DefaultTableModel tableModel;
    private volatile boolean running;

    public ChuyenTien(TaiKhoanNganHang fromAccount, TaiKhoanNganHang toAccount, double amount, DefaultTableModel tableModel) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.tableModel = tableModel;
        this.running = true;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            synchronized (fromAccount) {
                if (fromAccount.withdraw(amount)) {
                    synchronized (toAccount) {
                        toAccount.deposit(amount);
                    }

                    double totalBalance = fromAccount.getBalance() + toAccount.getBalance();

                    tableModel.addRow(new Object[]{
                            Thread.currentThread().getName(),
                            amount,
                            fromAccount.getAccountNumber(),
                            toAccount.getAccountNumber(),
                            totalBalance
                    });
                } else {
                    tableModel.addRow(new Object[]{
                            Thread.currentThread().getName(),
                            amount,
                            fromAccount.getAccountNumber(),
                            toAccount.getAccountNumber(),
                            "Insufficient funds"
                    });
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
