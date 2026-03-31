
/*
 * Name: Shabrina Sharmin Student ID: 040927453 Course & Section: CST8132_310 Teacher: Angela
 * Giddings Assignment: Lab 9 Date: April 16, 2019
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class holds {@linkplain JPanel} object for updating a {@linkplain BankAccount} in the the
 * {@linkplain Bank#accounts}
 *
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.3
 */
public class UpdateAccountPanel extends JPanel {
  private String[] optionTypeStrings = {"Withdraw", "Deposit"};
  private JLabel accNoLabel;
  private JLabel optionsLabel;

  private JLabel amountLabel;
  private JTextField accNoTF;
  private JComboBox<String> optionsCB;
  private JTextField amountTF;

  private JButton submitButton;
  private JButton cancelButton;

  private Bank bank;

  /**
   * This is the constructor. This holds the {@linkplain JPanel} to update a account It creates
   * {@linkplain ActionListener} object for {@linkplain JButton} objects and creates
   * {@linkplain KeyListener} object for {@linkplain JTextField} objects
   * 
   * @param b The {@linkplain Bank} object
   */
  public UpdateAccountPanel(Bank b) {
    super(new GridLayout(0, 2, 10, 10));
    this.accNoLabel = new JLabel("Account number");
    this.optionsLabel = new JLabel("Options");
    this.amountLabel = new JLabel("Amount");
    this.accNoTF = new JTextField();
    this.optionsCB = new JComboBox<String>(optionTypeStrings);
    this.amountTF = new JTextField();
    this.submitButton = new JButton("Submit");
    this.cancelButton = new JButton("Cancel");
    this.bank = b;

    // Creating object of ActionListener
    // ComboBoxActionListener cBL = new ComboBoxActionListener();
    SubmitOrCancelButtonActionListener sAL = new SubmitOrCancelButtonActionListener();
    TextFieldsKeyListener tFKL = new TextFieldsKeyListener();
    accNoTF.addKeyListener(tFKL);
    amountTF.addKeyListener(tFKL);
    submitButton.addActionListener(sAL);
    cancelButton.addActionListener(sAL);
    submitButton.setEnabled(false);

    // add things to JPanel
    super.add(accNoLabel);
    super.add(accNoTF);
    super.add(optionsLabel);
    super.add(optionsCB);
    super.add(amountLabel);
    super.add(amountTF);
    super.add(submitButton);
    super.add(cancelButton);

  }

  /**
   * This method resets each menuBar after being selected.
   */
  public void reset() {
    accNoTF.setText("");
    amountTF.setText("");

  }

  /**
   * This class implements a {@linkplain KeyListener} for relevant {@linkplain Component} objects in
   * {@linkplain UpdateAccountPanel}.
   * 
   * @author Shabrina Sharmin
   * @version 1.3
   * @since 1.3
   *
   */
  private class TextFieldsKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {

      if (e.getSource() == accNoTF) {
        if (!BankDataValidations.validateAccountNo(accNoTF.getText())) {
          accNoTF.setForeground(Color.RED);
          accNoTF.setToolTipText("Enter the account number in correct format");
        } else {
          accNoTF.setForeground(Color.BLACK);
          accNoTF.setToolTipText("");
        }

      }

      if (e.getSource() == amountTF) {
        if (!BankDataValidations.validateIsDouble(amountTF.getText())) {
          amountTF.setForeground(Color.RED);
          amountTF.setToolTipText("Enter a valid amount");

        } else {
          amountTF.setToolTipText("");
          amountTF.setForeground(Color.BLACK);

        }
      }

      if (BankDataValidations.validateAccountNo(accNoTF.getText())
          && BankDataValidations.validateIsDouble(amountTF.getText())) {

        submitButton.setEnabled(true);
      } else {
        submitButton.setEnabled(false);
      }

    }

    @Override
    public void keyTyped(KeyEvent e) {}

  }

  /**
   * This class implements a {@linkplain ActionListener} for relevant {@linkplain Component} objects
   * for {@linkplain UpdateAccountPanel}.
   * 
   * @author Shabrina Sharmin
   * @version 1.3
   * @since 1.3
   *
   */
  private class SubmitOrCancelButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == submitButton) {
        try {
          bank.updateAccount(Long.parseLong(accNoTF.getText()),
              optionsCB.getSelectedItem().toString(), Double.parseDouble(amountTF.getText()));
          JOptionPane.showMessageDialog(null, "Transaction successful", "Account transaction",
              JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, ex.getMessage(), "Account transaction",
              JOptionPane.ERROR_MESSAGE);
        }
      }
      if (e.getSource() == cancelButton) {
        reset();
      }

    }

  }
}
