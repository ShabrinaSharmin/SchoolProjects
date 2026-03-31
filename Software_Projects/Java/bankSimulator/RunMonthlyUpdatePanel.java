
/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course & Section: CST8132_310
 * Teacher: Angela Giddings 
 * Assignment: Lab 9
 * Date: April 16, 2019
 */
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class holds {@linkplain JPanel} object for running a monthly update
 * operation on accounts {@linkplain BankAccount} to the {@linkplain Bank}
 *
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.3
 */
public class RunMonthlyUpdatePanel extends JPanel {
	private JButton runMonthlyUpdateButton;
	private Bank bank;

	/**
	 * This is the constructor. This holds the {@linkplain JPanel} to run monthly
	 * update on a account It creates {@linkplain ActionListener} object for
	 * {@linkplain JButton} objects and creates {@linkplain KeyListener} object for
	 * {@linkplain JTextField} objects
	 * 
	 * @param b
	 *            The {@linkplain Bank} object.
	 */
	public RunMonthlyUpdatePanel(Bank b) {
		super(new FlowLayout());
		this.bank = b;
		runMonthlyUpdateButton = new JButton("Run monthly update");

		RunMonthlyUpdateButtonActionListener rMUBL = new RunMonthlyUpdateButtonActionListener();
		runMonthlyUpdateButton.addActionListener(rMUBL);

		// add
		super.add(runMonthlyUpdateButton);

	}

	/**
	 * This class implements a {@linkplain ActionListener} for relevant
	 * {@linkplain Component} objects in {@linkplain RunMonthlyUpdatePanel}.
	 * 
	 * @author Shabrina Sharmin
	 * @version 1.3
	 * @since 1.3
	 *
	 */
	private class RunMonthlyUpdateButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == runMonthlyUpdateButton) {
				bank.monthlyUpdate();
				JOptionPane.showMessageDialog((Component) e.getSource(), "Monthly update successful!",
						"Account monthly update", JOptionPane.INFORMATION_MESSAGE);

			}
		}
	}
}
