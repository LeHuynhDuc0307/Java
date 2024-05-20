package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Model.BankAccount;

public class ChuyenKhoanFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private BankAccount account;
    private JTextField depositAmountField;
    private JTextField withdrawAmountField;
    private JLabel balanceLabel;

    /**
     * Khởi chạy ứng dụng.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChuyenKhoanFrame frame = new ChuyenKhoanFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Tạo khung.
     */
    public ChuyenKhoanFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        account = new BankAccount(100000); // Số dư ban đầu

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(4, 2, 5, 5));

        JLabel lblDeposit = new JLabel("Số tiền gửi:");
        panel.add(lblDeposit);

        depositAmountField = new JTextField();
        panel.add(depositAmountField);
        depositAmountField.setColumns(10);

        JLabel lblWithdraw = new JLabel("Số tiền rút:");
        panel.add(lblWithdraw);

        withdrawAmountField = new JTextField();
        panel.add(withdrawAmountField);
        withdrawAmountField.setColumns(10);

        JButton btnDeposit = new JButton("Gửi tiền");
        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(depositAmountField.getText());
                new Thread(new GuiTien(account, amount)).start();
                updateBalance();
            }
        });
        panel.add(btnDeposit);

        JButton btnWithdraw = new JButton("Rút tiền");
        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(withdrawAmountField.getText());
                if (account.getBalance() >= amount) {
                    new Thread(new RutTien(account, amount)).start();
                    updateBalance();
                } else {
                    JOptionPane.showMessageDialog(null, "Không đủ số dư để rút tiền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(btnWithdraw);

        JLabel lblBalance = new JLabel("Số dư hiện tại:");
        panel.add(lblBalance);

        balanceLabel = new JLabel("100000");
        panel.add(balanceLabel);

        updateBalance();
    }

    private void updateBalance() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                balanceLabel.setText(String.valueOf(account.getBalance()));
            }
        });
    }
}
