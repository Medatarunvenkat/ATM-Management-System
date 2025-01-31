import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ATM
{
    private JFrame frame;
    private JTextField textFieldAmount;
    private JTextField textFieldBalance;
    private double balance=1000.00; // minimum balance

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try 
            {
                ATM window = new ATM();
                window.frame.setVisible(true);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        });
    }

    public ATM() {
        initialize();
    }

    private void initialize() 
    {
        frame=new JFrame();
        frame.setTitle("ATM");
        frame.setBounds(200, 200, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Title Label
        JLabel lblTitle = new JLabel("ATM Management System");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lblTitle.setForeground(Color.blue);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(150, 20, 300, 30);
        frame.getContentPane().add(lblTitle);

        // Amount Label and Text Field
        createLabel("Enter Amount:", 50, 80);
        textFieldAmount = createTextField(300, 80);

        // Balance Label and Text Field
        createLabel("Balance:", 50, 140);
        textFieldBalance = createTextField(300, 140);
        textFieldBalance.setText(String.format("%.2f", balance));
        textFieldBalance.setEditable(true);

        // Buttons
        JButton btnDeposit = createButton("Deposit", 50, 220);
        JButton btnWithdraw = createButton("Withdraw", 180, 220);
        JButton btnBalance = createButton("Check Balance", 310, 220);
        JButton btnClear = createButton("Clear", 440, 220);

        // Action Listeners
        btnDeposit.addActionListener(e -> depositAmount());
        btnWithdraw.addActionListener(e -> withdrawAmount());
        btnBalance.addActionListener(e -> checkBalance());
        btnClear.addActionListener(e -> clearFields());
    }

    private void depositAmount() 
    {
        try 
        {
            double amount = Double.parseDouble(textFieldAmount.getText());
            if (amount<=0) 
            {
                showError("Enter a valid positive amount.");
                return;
            }
            balance+=amount;
            showMessage("Amount deposited successfully!");
            textFieldBalance.setText(String.format("%.2f", balance));
        } 
        catch (NumberFormatException e) 
        {
            showError("Please enter a valid number.");
        }
    }

    private void withdrawAmount() 
    {
        try 
        {
            double amount=Double.parseDouble(textFieldAmount.getText());
            if (amount<=0) 
            {
                showError("Enter a valid positive amount.");
                return;
            }
            if (amount>balance) 
            {
                showError("Insufficient balance.");
                return;
            }
            balance -= amount;
            showMessage("Amount withdrawn successfully!");
            textFieldBalance.setText(String.format("%.2f", balance));
        } 
        catch (NumberFormatException e) 
        {
            showError("Please enter a valid number.");
        }
    }

    private void checkBalance() 
    {
        showMessage("Current Balance: " + String.format("%.2f", balance));
    }

    private void clearFields() 
    {
        textFieldAmount.setText("");
        textFieldBalance.setText(String.format("%.2f", balance));
    }

    private void showError(String message) 
    {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message) 
    {
        JOptionPane.showMessageDialog(frame, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void createLabel(String text, int x, int y) 
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label.setBounds(x, y, 150, 30);
        frame.getContentPane().add(label);
    }

    private JTextField createTextField(int x, int y) 
    {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 200, 30);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        return textField;
    }

    private JButton createButton(String text, int x, int y) 
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Times New Roman", Font.BOLD, 16));
        button.setBounds(x, y, 120, 40);
        frame.getContentPane().add(button);
        return button;
    }
}
