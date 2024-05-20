package View;

import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import Model.TaiKhoanNganHang;

public class ChuyenTienFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    DefaultTableModel defaultTableModel;
    JTable table;
    private ChuyenTien[] chuyenTienTasks;
    private Thread[] threads;
    private TaiKhoanNganHang[] accounts;
    private static final int NUM_ACCOUNTS = 5;
    private static final int INITIAL_BALANCE = 100000;
    private static final double TRANSFER_AMOUNT = 5000;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChuyenTienFrame frame = new ChuyenTienFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ChuyenTienFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 971, 509);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("MINH HỌA CHUYỂN TIỀN");
        lblNewLabel.setForeground(new Color(0, 0, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel.setBounds(263, 21, 414, 71);
        contentPane.add(lblNewLabel);

        JButton btnStart = new JButton("Start");
        btnStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnStart.setBounds(348, 102, 101, 27);
        contentPane.add(btnStart);

        JButton btnStop = new JButton("Stop");
        btnStop.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnStop.setBounds(473, 102, 101, 27);
        contentPane.add(btnStop);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chi tiết chuyển khoản", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(24, 164, 923, 298);
        contentPane.add(panel);
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);

        panel.setLayout(new BorderLayout());
        JScrollPane jScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(jScrollPane, BorderLayout.CENTER);

        defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setColumnIdentifiers(new Object[]{
                "Thread Name", "Số tiền chuyển", "Chuyển từ tài khoản", "Chuyển tới tài khoản", "Tổng tiền trong ngân hàng"
        });

        accounts = new TaiKhoanNganHang[NUM_ACCOUNTS];
        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            accounts[i] = new TaiKhoanNganHang(i + 1, INITIAL_BALANCE);
        }

        btnStart.addActionListener(e -> {
            chuyenTienTasks = new ChuyenTien[NUM_ACCOUNTS];
            threads = new Thread[NUM_ACCOUNTS];
            Random random = new Random();

            for (int i = 0; i < NUM_ACCOUNTS; i++) {
                int fromIndex = random.nextInt(NUM_ACCOUNTS);
                int toIndex;
                do {
                    toIndex = random.nextInt(NUM_ACCOUNTS);
                } while (toIndex == fromIndex);

                chuyenTienTasks[i] = new ChuyenTien(accounts[fromIndex], accounts[toIndex], TRANSFER_AMOUNT, defaultTableModel);
                threads[i] = new Thread(chuyenTienTasks[i]);
                threads[i].start();
            }
        });

        btnStop.addActionListener(e -> {
            for (ChuyenTien task : chuyenTienTasks) {
                if (task != null) {
                    task.stop();
                }
            }
        });
    }
}
