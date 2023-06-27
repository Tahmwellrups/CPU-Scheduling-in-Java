import javax.swing.*;

public class UI extends JFrame {
    private JPanel panel1;
    private JPanel panelMain;
    private JTable table1;
    private JComboBox procNo;
    private JButton simulateButton;
    private JTextField textField1;
    private JLabel process;

    public UI ()
    {
        setContentPane(panel1);
        setTitle("CPU Scheduling");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UI();
    }
}
