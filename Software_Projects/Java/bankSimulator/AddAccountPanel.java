
/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course & Section: CST8132_310
 * Teacher: Angela Giddings 
 * Assignment: Lab 9
 * Date: April 16, 2019
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
 * This class holds {@linkplain JPanel} for adding new {@linkplain BankAccount}
 * to the {@linkplain Bank}
 * 
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.3
 *
 */
public class AddAccountPanel extends JPanel {

	private String[] accountTypeStrings = { "Savings", "Chequing" };

	private JComboBox<String> accountTypeCB;

	private JLabel accTypeLabel;

	private JLabel accNoLabel;

	private JLabel firstNameLabel;

	private JLabel lastNameLabel;

	private JLabel phnNoLabel;

	private JLabel emailAddressLabel;

	private JLabel openingBalanceLabel;

	private JLabel minimumBalanceSLabel;

	private JLabel interestRateSLabel;

	private JLabel feeCLabel;

	private JTextField accNoTF;

	private JTextField firstNameTF;

	private JTextField lastNameTF;

	private JTextField phnNoTF;

	private JTextField emailAddressTF;

	private JTextField openingBalanceTF;

	private JTextField minimumBalanceSTF;

	private JTextField interestRateSTF;

	private JTextField feeCTF;

	private JButton submitButton;

	private JButton cancelButton;

	private Bank bank;

	/**
	 * This is the constructor. This holds the {@linkplain JPanel} for adding a new
	 * account It creates {@linkplain ActionListener} object for
	 * {@linkplain JButton} objects and creates {@linkplain KeyListener} object for
	 * {@linkplain JTextField} objects
	 * 
	 * @param b
	 *            The {@linkplain Bank} object
	 */

	public AddAccountPanel(Bank b) {

		super(new GridLayout(0, 2, 10, 10));
		this.bank = b;
		this.accountTypeCB = new JComboBox<String>(accountTypeStrings);
		this.accTypeLabel = new JLabel("Account type");
		this.accNoLabel = new JLabel("Account number");
		this.firstNameLabel = new JLabel("First name");
		this.lastNameLabel = new JLabel("Last name");
		this.phnNoLabel = new JLabel("Phone number ");
		this.emailAddressLabel = new JLabel("Email address");
		this.openingBalanceLabel = new JLabel("Opening Balance");
		this.minimumBalanceSLabel = new JLabel("Minimum Balance");
		this.interestRateSLabel = new JLabel("Interest rate");
		this.feeCLabel = new JLabel("Fee");

		this.accNoTF = new JTextField();
		this.firstNameTF = new JTextField();
		this.lastNameTF = new JTextField();
		this.phnNoTF = new JTextField();
		this.emailAddressTF = new JTextField();
		this.openingBalanceTF = new JTextField();
		this.minimumBalanceSTF = new JTextField();
		this.interestRateSTF = new JTextField();
		this.feeCTF = new JTextField();

		this.submitButton = new JButton("Submit");
		this.cancelButton = new JButton("Cancel");

		// creating ActionLisener object
		ComboBoxActionListener cBL = new ComboBoxActionListener();
		SubmitOrCancelButtonActionListener sBL = new SubmitOrCancelButtonActionListener();
		KeyListener tAL = new TextFieldsKeyListener();

		// adding ActionLiseners to the respective JtextField members
		accountTypeCB.addActionListener(cBL);
		accNoTF.addKeyListener(tAL);
		phnNoTF.addKeyListener(tAL);
		emailAddressTF.addKeyListener(tAL);
		openingBalanceTF.addKeyListener(tAL);
		minimumBalanceSTF.addKeyListener(tAL);
		interestRateSTF.addKeyListener(tAL);
		submitButton.addActionListener(sBL);
		cancelButton.addActionListener(sBL);

		//
		feeCLabel.setVisible(false);
		feeCTF.setVisible(false);
		submitButton.setEnabled(false);

		// add members to the panel
		super.add(accTypeLabel);
		super.add(accountTypeCB);

		super.add(accNoLabel);
		super.add(accNoTF);

		super.add(firstNameLabel);
		super.add(firstNameTF);

		super.add(lastNameLabel);
		super.add(lastNameTF);

		super.add(phnNoLabel);
		super.add(phnNoTF);

		super.add(emailAddressLabel);
		super.add(emailAddressTF);

		super.add(openingBalanceLabel);
		super.add(openingBalanceTF);

		super.add(minimumBalanceSLabel);
		super.add(minimumBalanceSTF);

		super.add(interestRateSLabel);
		super.add(interestRateSTF);

		super.add(feeCLabel);
		super.add(feeCTF);

		super.add(submitButton);
		super.add(cancelButton);
	}

	/**
	 * This method sets all text fields to empty value.
	 */
	public void reset() {
		accNoTF.setText("");
		firstNameTF.setText("");
		lastNameTF.setText("");
		phnNoTF.setText("");
		emailAddressTF.setText("");
		openingBalanceTF.setText("");
		minimumBalanceSTF.setText("");
		interestRateSTF.setText("");
		feeCTF.setText("");
	}

	/**
	 * This class implements a {@linkplain ActionListener} for relevant
	 * {@linkplain Component} objects in {@linkplain AddAccountPanel}.
	 * 
	 * @author Shabrina Sharmin
	 * @version 1.3
	 * @since 1.3
	 *
	 */
	private class ComboBoxActionListener implements ActionListener {

		@Override
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent event) {
			if (accountTypeCB.getSelectedItem().toString().equals(accountTypeStrings[0])) { // Savings
				// setting true to the labels that has specific functionality for savingsAccount
				minimumBalanceSLabel.setVisible(true);
				minimumBalanceSTF.setVisible(true);
				interestRateSLabel.setVisible(true);
				interestRateSTF.setVisible(true);
				// //setting true to the labels that has specific functionality for
				// ChequingAccount as user choose savingsAccount
				feeCLabel.setVisible(false);
				feeCTF.setVisible(false);

			}

			if (accountTypeCB.getSelectedItem().toString().equals(accountTypeStrings[1])) { // Chequing
				// setting true to the labels that has specific functionality for
				// chequingAccount
				feeCLabel.setVisible(true);
				feeCTF.setVisible(true);

				// setting false to the labels that has specific functionality for
				// savingsAccount as user choose CheckingAccount
				minimumBalanceSLabel.setVisible(false);
				minimumBalanceSTF.setVisible(false);
				interestRateSLabel.setVisible(false);
				interestRateSTF.setVisible(false);

			}

		}

	}

	/**
	 * This class implements a {@linkplain KeyListener} for relevant
	 * {@linkplain Component} objects in {@linkplain AddAccountPanel}.
	 * 
	 * @author Shabrina Sharmin
	 * @version 1.3
	 * @since 1.3
	 *
	 */
	private class TextFieldsKeyListener implements KeyListener {

		@Override
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
		 */
		public void keyPressed(KeyEvent e) {

		}

		@Override
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
		 */
		// Validation of all textfields and submit button
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
			if (e.getSource() == phnNoTF) {
				System.out.println("errdggdf");
				if (!BankDataValidations.validateIsLong(phnNoTF.getText())) {
					phnNoTF.setForeground(Color.RED);
					phnNoTF.setToolTipText("Enter the phone number in correct format");
				} else {
					phnNoTF.setToolTipText("");
					phnNoTF.setForeground(Color.BLACK);
				}

			}

			if (e.getSource() == emailAddressTF) {
				if (!BankDataValidations.validateEmail(emailAddressTF.getText())) {
					emailAddressTF.setForeground(Color.RED);
					emailAddressTF.setToolTipText("Enter the email address in correct format");
				} else {
					emailAddressTF.setForeground(Color.BLACK);
					emailAddressTF.setToolTipText("");
				}
			}

			if (e.getSource() == openingBalanceTF) {
				if (!BankDataValidations.validateIsDouble(openingBalanceTF.getText())) {
					openingBalanceTF.setForeground(Color.RED);
					openingBalanceTF.setToolTipText("Enter a valid ammount");
				} else {
					openingBalanceTF.setForeground(Color.BLACK);
					openingBalanceTF.setToolTipText("");
				}
			}

			if (e.getSource() == minimumBalanceSTF) {
				if (!BankDataValidations.validateIsDouble(minimumBalanceSTF.getText())) {
					minimumBalanceSTF.setForeground(Color.RED);
					minimumBalanceSTF.setToolTipText("Enter a valid ammount");
				} else {
					minimumBalanceSTF.setForeground(Color.BLACK);
					minimumBalanceSTF.setToolTipText("");
				}

			}
			if (e.getSource() == interestRateSTF) {
				if (!BankDataValidations.validateInterestRate(interestRateSTF.getText())) {
					interestRateSTF.setForeground(Color.RED);
					interestRateSTF.setToolTipText("Enter a valid ammount(0.1 to 0.9)");
				} else {
					interestRateSTF.setForeground(Color.BLACK);
					interestRateSTF.setToolTipText("");

				}

			}
			if (e.getSource() == feeCTF) {
				if (!BankDataValidations.validateIsDouble(feeCTF.getText())) {
					feeCTF.setForeground(Color.RED);
					feeCTF.setToolTipText("Enter a valid ammount(0.1 to 0.9)");
				} else {
					feeCTF.setForeground(Color.BLACK);
					feeCTF.setToolTipText("");
				}

			}

			if (accountTypeCB.getSelectedItem().toString().equals(accountTypeStrings[0])) {
				if (BankDataValidations.validateAccountNo(accNoTF.getText())
						&& BankDataValidations.validateIsLong(phnNoTF.getText())
						&& BankDataValidations.validateEmail(emailAddressTF.getText())
						&& BankDataValidations.validateIsDouble(openingBalanceTF.getText())
						&& BankDataValidations.validateIsDouble(minimumBalanceSTF.getText())
						&& BankDataValidations.validateInterestRate(interestRateSTF.getText())

				) {
					submitButton.setEnabled(true);
				} else {
					submitButton.setEnabled(false);
				}
			}

			if (accountTypeCB.getSelectedItem().toString().equals(accountTypeStrings[1])) {
				if (BankDataValidations.validateAccountNo(accNoTF.getText())
						&& BankDataValidations.validateIsLong(phnNoTF.getText())
						&& BankDataValidations.validateEmail(emailAddressTF.getText())
						&& BankDataValidations.validateIsDouble(openingBalanceTF.getText())
						&& BankDataValidations.validateIsDouble(feeCTF.getText())) {
					submitButton.setEnabled(true);
				} else
					submitButton.setEnabled(false);
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * This class implements a {@linkplain ActionListener} for relevant
	 * {@linkplain Component} objects for {@linkplain AddAccountPanel}.
	 * 
	 * @author Shabrina Sharmin
	 * @version 1.3
	 * @since 1.3
	 *
	 */
	private class SubmitOrCancelButtonActionListener implements ActionListener {

		@Override
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			boolean result = false;
			if (e.getSource() == submitButton) {
				if (accountTypeCB.getSelectedItem().toString().equals(accountTypeStrings[0])) {
					result = bank.addSavingsAccount(firstNameTF.getText(), lastNameTF.getText(),
							Long.parseLong(accNoTF.getText()), Long.parseLong(phnNoTF.getText()),
							emailAddressTF.getText(), Double.parseDouble(openingBalanceTF.getText()),
							Double.parseDouble(interestRateSTF.getText()),
							Double.parseDouble(minimumBalanceSTF.getText()));

				}
				if (accountTypeCB.getSelectedItem().toString().equals(accountTypeStrings[1])) {
					result = bank.addChequingAccount(firstNameTF.getText(), lastNameTF.getText(),
							Long.parseLong(accNoTF.getText()), Long.parseLong(phnNoTF.getText()),
							emailAddressTF.getText(), Double.parseDouble(openingBalanceTF.getText()),
							Double.parseDouble(feeCTF.getText()));

				}
				if (result) {
					JOptionPane.showMessageDialog(null, "Account successfully added!", "Account addition",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Account could not be added", "Account addition",
							JOptionPane.ERROR_MESSAGE);
				}

			}
			if (e.getSource() == cancelButton) {
				reset();
			}

		}

	}

}
