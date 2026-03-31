
/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course & Section: CST8132_310
 * Teacher: Angela Giddings 
 * Assignment: Lab 9
 * Date: April 16, 2019
 */
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This class holds {@linkplain JPanel} object to display all existing accounts
 * {@linkplain BankAccount} to the {@linkplain Bank}
 * 
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.3
 *
 */
public class PrintAllAccountsPanel extends JPanel {
	private JButton printButton;
	private JTextArea displayAccountsTA;
	private Bank bank;

	/**
	 * This is the constructor. This holds the {@linkplain JPanel} to print all
	 * existing accounts It creates {@linkplain ActionListener} object for
	 * {@linkplain JButton} objects
	 * 
	 * @param b
	 *            The {@linkplain Bank} object
	 */
	public PrintAllAccountsPanel(Bank b) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.bank = b;
		printButton = new JButton("Print all Accounts");
		displayAccountsTA = new JTextArea();
		displayAccountsTA.setEditable(false);
		SubmitButtonActionListener sBL = new SubmitButtonActionListener();
		printButton.addActionListener(sBL);
		printButton.setSize(300, 150);
		displayAccountsTA.setSize(1030, 1100);
		displayAccountsTA.setVisible(false);
		// add
		super.add(printButton);
		super.add(displayAccountsTA);

	}

	/**
	 * This methods resets the menuBars each time after gets selected
	 */
	public void reset() {
		displayAccountsTA.setText("");
		displayAccountsTA.setVisible(false);

	}

	/**
	 * This class implements a {@linkplain ActionListener} for relevant
	 * {@linkplain Component} objects in {@linkplain PrintAllAccountsPanel}.
	 * 
	 * @author Shabrina Sharmin
	 * @version 1.3
	 * @since 1.3
	 *
	 */
	private class SubmitButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == printButton) {
				String toDisplay = bank.printAccountDetails();
				if (toDisplay.length() != 0) {
					displayAccountsTA.setText(toDisplay);
					displayAccountsTA.setVisible(true);
				} else {
					displayAccountsTA.setVisible(false);
				}
			}
		}
	}
}
