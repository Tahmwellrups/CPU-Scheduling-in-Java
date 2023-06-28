import javax.swing.*;

public class UI extends JFrame {
    private JPanel panel1;
    private JPanel panelMain;
    private JTable table1;
    private JComboBox procNo;
    private JButton simulateButton;
    private JTextField textField1;
    private JLabel process;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
   // test
    public UI ()
    {
        setContentPane(panel1);
        setTitle("CPU Scheduling");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,500);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UI();
    }
}
