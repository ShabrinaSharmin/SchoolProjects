
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class holds {@linkplain JPanel} object to display existing accounts
 * {@linkplain BankAccount} to the {@linkplain Bank}
 * 
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.3
 *
 */
public class DisplayAccountPanel extends JPanel {
	private JLabel accNoLabel;
	private JTextField accNoTF;
	private JButton submitButton;
	private JButton cancelButton;
	private JTextArea displayTA;
	private Bank bank;

	/**
	 * This is the constructor. This holds the {@linkplain JPanel} to display a
	 * desired account.It creates {@linkplain ActionListener} object for
	 * {@linkplain JButton} objects
	 * 
	 * @param b
	 *            The {@linkplain Bank} object
	 */
	public DisplayAccountPanel(Bank b) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.accNoLabel = new JLabel("Account number");
		this.displayTA = new JTextArea();
		this.accNoTF = new JTextField();
		this.submitButton = new JButton("Submit");
		this.cancelButton = new JButton("Cancel");
		this.bank = b;
		// Action listener
		SubmitOrCancelButtonActionListener sBA = new SubmitOrCancelButtonActionListener();
		this.submitButton.addActionListener(sBA);
		this.cancelButton.addActionListener(sBA);
		displayTA.setVisible(false);
		// add
		super.add(accNoLabel);
		super.add(accNoTF);

		super.add(submitButton);
		this.submitButton.setEnabled(false);
		super.add(cancelButton);
		super.add(displayTA);
		// Anonymous class
		this.accNoTF.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getSource() == accNoTF) {
					if (!BankDataValidations.validateAccountNo(accNoTF.getText())) {
						accNoTF.setForeground(Color.RED);
						accNoTF.setToolTipText("Enter the account number in correct format");
					} else {
						accNoTF.setToolTipText("");
						accNoTF.setForeground(Color.BLACK);
					}

				}
				if (BankDataValidations.validateAccountNo(accNoTF.getText())) {
					submitButton.setEnabled(true);

				} else
					submitButton.setEnabled(false);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {

			}
		});
	}

	/**
	 * This methods resets the menuBars components.
	 */
	public void reset() {
		accNoTF.setText("");
		displayTA.setVisible(false);
	}

	/**
	 * This class implements a {@linkplain ActionListener} for relevant
	 * {@linkplain Component} objects for {@linkplain DisplayAccountPanel}.
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
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == submitButton) {
				try {
					displayTA.setText(bank.displayAccount(Long.parseLong(accNoTF.getText())));
					displayTA.setVisible(true);
				} catch (TransactionIllegalArgumentException ex) {
					displayTA.setVisible(false);
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Account Display", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (event.getSource() == cancelButton) {
				reset();
			}

		}

	}
}
